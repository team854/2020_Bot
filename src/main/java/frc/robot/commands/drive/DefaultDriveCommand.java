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

        // Drive according to the type of drive selected in the
        // operator input.
        TStickPosition leftStickPosition = oi.getDriveStickPosition(TStick.LEFT);
        TStickPosition rightStickPosition = oi.getDriveStickPosition(TStick.RIGHT);

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

        if ((motorSpeeds.left > 0 && desiredMotorSpeeds.left > 0 && motorSpeeds.left >= desiredMotorSpeeds.left)
                || (motorSpeeds.left < 0 && desiredMotorSpeeds.left < 0 && motorSpeeds.left <= desiredMotorSpeeds.left)
            && (motorSpeeds.right > 0 && desiredMotorSpeeds.right > 0 && motorSpeeds.right >= desiredMotorSpeeds.right)
                || (motorSpeeds.right < 0 && desiredMotorSpeeds.right < 0 && motorSpeeds.right <= desiredMotorSpeeds.right)) {
            // The motor speeds have reached or exceeded the original desired speeds - curve is complete
            motorSpeeds = desiredMotorSpeeds;
        } else {
            // y = mx + b accel. curve, where x is time
            // The curve is scaled based on the difference between the current speed and desired speed...
            // ... when the joystick was originally moved
            curTime = timeSinceInitialized();
            motorSpeeds.left    *= ((curTime - startTime) * 2 + 0) * ((desiredMotorSpeeds.left - prevDesiredMotorSpeeds.left) / RobotConst.MOTOR_SPEED_PERCENT);
            motorSpeeds.right   *= ((curTime - startTime) * 2 + 0) * ((desiredMotorSpeeds.right - prevDesiredMotorSpeeds.right) / RobotConst.MOTOR_SPEED_PERCENT);
        }

        driveSubsystem.setSpeed(motorSpeeds);
    }

    @Override
    protected boolean isFinished() {
        // The default command does not end
        return false;
    }
}
