package frc.robot.commands.drive;

import com.torontocodingcollective.commands.TDefaultDriveCommand;
import com.torontocodingcollective.commands.TDifferentialDrive;
import com.torontocodingcollective.oi.TStick;
import com.torontocodingcollective.oi.TStickPosition;
import com.torontocodingcollective.speedcontroller.TSpeeds;

import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.oi.OI;
import frc.robot.subsystems.CanDriveSubsystem;
import frc.robot.subsystems.PwmDriveSubsystem;

/**
 * Default drive command for a drive base
 */
public class DefaultDriveCommand extends TDefaultDriveCommand {

    private static final String COMMAND_NAME =
            DefaultDriveCommand.class.getSimpleName();

    OI                oi                = Robot.oi;
    PwmDriveSubsystem driveSubsystem    = Robot.driveSubsystem;

    TDifferentialDrive differentialDrive = new TDifferentialDrive();

    double  startTime       = 0;  // For accel. curve
    TSpeeds prevDesiredMotorSpeeds = new TSpeeds();
    boolean operatorControlling = false;

    public DefaultDriveCommand() {
        // The drive logic will be handled by the TDefaultDriveCommand
        // which also contains the requires(driveSubsystem) statement
        super(Robot.oi, Robot.driveSubsystem);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    protected String getParmDesc() {
        return super.getParmDesc();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {

        // Print the command parameters if this is the current
        // called command (it was not sub-classed)
        if (getCommandName().equals(COMMAND_NAME)) {
            logMessage(getParmDesc() + " starting");
        }

        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        // Check the driver controller buttons
        super.execute();

        // Check whether Driver is moving or not, otherwise the Operator can control
        TStickPosition leftStickPosition = oi.getOperatorDriveStickPosition(TStick.LEFT);
        TStickPosition rightStickPosition = oi.getOperatorDriveStickPosition(TStick.RIGHT);
        operatorControlling = true;
        if (oi.isDriverActive()) {
            leftStickPosition = oi.getDriverDriveStickPosition(TStick.LEFT);
            rightStickPosition = oi.getDriverDriveStickPosition(TStick.RIGHT);
            operatorControlling = false;
        }

        TStick singleStickSide = oi.getSelectedSingleStickSide();

        TSpeeds motorSpeeds = new TSpeeds();
        TSpeeds desiredMotorSpeeds;

        double curTime;

        switch (oi.getSelectedDriveType()) {

        case SINGLE_STICK:
            TStickPosition singleStickPosition = rightStickPosition;
            if (singleStickSide == TStick.LEFT) {
                singleStickPosition = leftStickPosition;
            }
            desiredMotorSpeeds = differentialDrive.arcadeDrive(singleStickPosition);
            break;

        case TANK:
            desiredMotorSpeeds = differentialDrive.tankDrive(leftStickPosition, rightStickPosition);
            break;

        case ARCADE:
        default:
            desiredMotorSpeeds = differentialDrive.arcadeDrive(leftStickPosition, rightStickPosition);
            break;
        }

        desiredMotorSpeeds.left *= RobotConst.MOTOR_SPEED_PERCENT;
        desiredMotorSpeeds.right *= RobotConst.MOTOR_SPEED_PERCENT;

        if (desiredMotorSpeeds.left != prevDesiredMotorSpeeds.left || desiredMotorSpeeds.right != prevDesiredMotorSpeeds.right) {
            // The joystick movement has changed - the timer for the curve needs to restart
            startTime = timeSinceInitialized();
            prevDesiredMotorSpeeds = desiredMotorSpeeds;
        }

        motorSpeeds = desiredMotorSpeeds;
        if (!operatorControlling && !((motorSpeeds.left > 0 && desiredMotorSpeeds.left > 0 && motorSpeeds.left >= desiredMotorSpeeds.left)
                || (motorSpeeds.left < 0 && desiredMotorSpeeds.left < 0 && motorSpeeds.left <= desiredMotorSpeeds.left)
            && (motorSpeeds.right > 0 && desiredMotorSpeeds.right > 0 && motorSpeeds.right >= desiredMotorSpeeds.right)
                || (motorSpeeds.right < 0 && desiredMotorSpeeds.right < 0 && motorSpeeds.right <= desiredMotorSpeeds.right))) {
            // y = mx + b accel. curve, where x is time
            // The curve is scaled based on the difference between the current speed and desired speed...
            // ... when the joystick was originally moved
            curTime = timeSinceInitialized();
            double leftAccel    = ((curTime - startTime) * RobotConst.ACCEL_CURVE_M + RobotConst.ACCEL_CURVE_B) / (Math.abs((desiredMotorSpeeds.left  - prevDesiredMotorSpeeds.left))  / RobotConst.MOTOR_SPEED_PERCENT);
            double leftDeaccel  = ((curTime - startTime) * -RobotConst.ACCEL_CURVE_M + RobotConst.ACCEL_CURVE_B + 1) / (Math.abs((desiredMotorSpeeds.left  - prevDesiredMotorSpeeds.left))  / RobotConst.MOTOR_SPEED_PERCENT);
            double rightAccel   = ((curTime - startTime) * RobotConst.ACCEL_CURVE_M + RobotConst.ACCEL_CURVE_B) / (Math.abs((desiredMotorSpeeds.right - prevDesiredMotorSpeeds.right)) / RobotConst.MOTOR_SPEED_PERCENT);
            double rightDeaccel = ((curTime - startTime) * -RobotConst.ACCEL_CURVE_M + RobotConst.ACCEL_CURVE_B + 1) / (Math.abs((desiredMotorSpeeds.right - prevDesiredMotorSpeeds.right)) / RobotConst.MOTOR_SPEED_PERCENT);
            // Left
            if (desiredMotorSpeeds.left - prevDesiredMotorSpeeds.left > 0) {
                motorSpeeds.left    *= leftAccel;
            } else {
                // The bot is slowing down, multiply by the inverse curve instead
                motorSpeeds.left    *= leftDeaccel;
            }
            // Right
            if (desiredMotorSpeeds.right - prevDesiredMotorSpeeds.right > 0) {
                motorSpeeds.right   *= rightAccel;
            } else {
                motorSpeeds.right   *= rightDeaccel;
            }
        }

        // Slow down operator
        if (operatorControlling) {
            motorSpeeds.left    *= RobotConst.OPERATOR_SPEED_PERCENT;
            motorSpeeds.right   *= RobotConst.OPERATOR_SPEED_PERCENT;
        }

        driveSubsystem.setSpeed(motorSpeeds);
    }

    @Override
    protected boolean isFinished() {
        // The default command does not end
        return false;
    }
}
