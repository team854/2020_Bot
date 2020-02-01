package frc.robot.subsystems;

import com.torontocodingcollective.sensors.encoder.TEncoder;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.test.CanDriveTestCommand;

// This subsystem is for testing each motor, one by one
public class CanDriveTestSubsystem extends TSubsystem {

    private TCanSpeedController left1 = new TCanSpeedController(
                                                RobotMap.LEFT_DRIVE_CAN_SPEED_CONTROLLER_TYPE, 
                                                RobotMap.LEFT_DRIVE_CAN_SPEED_CONTROLLER_ADDRESS,
                                                RobotMap.LEFT_DRIVE_CAN_MOTOR_ISINVERTED
                                            );
    private TCanSpeedController left2 = new TCanSpeedController(
                                                RobotMap.LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_TYPE,
                                                RobotMap.LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_ADDRESS,
                                                RobotMap.LEFT_DRIVE_CAN_MOTOR_ISINVERTED
                                            );
    private TCanSpeedController left3 = new TCanSpeedController(
                                                RobotMap.LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_TYPE,
                                                RobotMap.LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS,
                                                RobotMap.LEFT_DRIVE_CAN_MOTOR_ISINVERTED
                                            );
    private TCanSpeedController right1 = new TCanSpeedController(
                                                RobotMap.RIGHT_DRIVE_CAN_SPEED_CONTROLLER_TYPE,
                                                RobotMap.RIGHT_DRIVE_CAN_SPEED_CONTROLLER_ADDRESS,
                                                RobotMap.RIGHT_DRIVE_CAN_MOTOR_ISINVERTED
                                            );
    private TCanSpeedController right2 = new TCanSpeedController(
                                                RobotMap.RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_TYPE,
                                                RobotMap.RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_ADDRESS,
                                                RobotMap.RIGHT_DRIVE_CAN_MOTOR_ISINVERTED
                                            );
    private TCanSpeedController right3 = new TCanSpeedController(
                                                RobotMap.RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_TYPE,
                                                RobotMap.RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS,
                                                RobotMap.RIGHT_DRIVE_CAN_MOTOR_ISINVERTED
                                            );
    // Will this work??
    private TEncoder leftEncoder = left1.getEncoder();
    private TEncoder rightEncoder = right1.getEncoder();

    public double getLeft1Speed() {
        return left1.get();
    }

    public void setLeft1Speed(double speed) {
        left1.set(speed);
    }

    public double getLeft2Speed() {
        return left2.get();
    }

    public void setLeft2Speed(double speed) {
        left2.set(speed);
    }

    public double getLeft3Speed() {
        return left3.get();
    }

    public void setLeft3Speed(double speed) {
        left3.set(speed);
    }

    public double getRight1Speed() {
        return right1.get();
    }

    public void setRight1Speed(double speed) {
        right1.set(speed);
    }

    public double getRight2Speed() {
        return right2.get();
    }

    public void setRight2Speed(double speed) {
        right2.set(speed);
    }

    public double getRight3Speed() {
        return right3.get();
    }

    public void setRight3Speed(double speed) {
        right3.set(speed);
    }

    public double getLeftEncoderRate() {
        return leftEncoder.getRate();
    }

    public double getRightEncoderRate() {
        return rightEncoder.getRate();
    }

    @Override
    public void init() {
    }

    // Initialize the default command for the Chassis subsystem.
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new CanDriveTestCommand());
    }

    @Override
    public void updatePeriodic() {
        SmartDashboard.putNumber("Left Encoder Rate", getLeftEncoderRate());
        SmartDashboard.putNumber("Right Encoder Rate", getRightEncoderRate());
    }

}