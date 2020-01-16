package frc.robot.subsystems;

import com.torontocodingcollective.sensors.encoder.TDioQuadEncoder;
import com.torontocodingcollective.sensors.gyro.TAnalogGyro;
import com.torontocodingcollective.sensors.ultrasonic.TUltrasonicSensor;
import com.torontocodingcollective.speedcontroller.TPwmSpeedController;
import com.torontocodingcollective.subsystem.TGyroDriveSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConst;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DefaultDriveCommand;

/**
 * Chassis Subsystem
 * <p>
 * This class is describes all of the components in a differential (left/right)
 * drive subsystem.
 */
public class PwmDriveSubsystem extends TGyroDriveSubsystem {

    TUltrasonicSensor distanceSensor = new TUltrasonicSensor(RobotMap.ULTRASONIC_ANALOG_PORT);

    public PwmDriveSubsystem() {

        super(
                // Left Speed Controller
                new TPwmSpeedController(
                        RobotMap.LEFT_DRIVE_PWM_SPEED_CONTROLLER_TYPE,
                        RobotMap.LEFT_DRIVE_PWM_SPEED_CONTROLLER_ADDRESS,
                        RobotMap.LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_TYPE,
                        RobotMap.LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_ADDRESS,
                        RobotMap.LEFT_DRIVE_PWM_MOTOR_ISINVERTED),

                // Right Speed Controller
                new TPwmSpeedController(
                        RobotMap.RIGHT_DRIVE_PWM_SPEED_CONTROLLER_TYPE,
                        RobotMap.RIGHT_DRIVE_PWM_SPEED_CONTROLLER_ADDRESS,
                        RobotMap.RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_TYPE,
                        RobotMap.RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_ADDRESS,
                        RobotMap.RIGHT_DRIVE_PWM_MOTOR_ISINVERTED),

                // Left Encoder
                new TDioQuadEncoder(
                        RobotMap.LEFT_DRIVE_DIO_ENCODER_PORT1,
                        RobotMap.LEFT_DRIVE_DIO_ENCODER_PORT1 + 1,
                        RobotMap.LEFT_DRIVE_DIO_ENCODER_ISINVERTED),
                // Right Encoder
                new TDioQuadEncoder(
                        RobotMap.RIGHT_DRIVE_DIO_ENCODER_PORT1,
                        RobotMap.RIGHT_DRIVE_DIO_ENCODER_PORT1 + 1,
                        RobotMap.RIGHT_DRIVE_DIO_ENCODER_ISINVERTED),

                // Encoder counts per inch
                RobotConst.ENCODER_COUNTS_PER_INCH,
                // Speed PID Kp, Ki
                RobotConst.DRIVE_SPEED_PID_KP,
                RobotConst.DRIVE_SPEED_PID_KI,
                // Max Encoder Speed
                RobotConst.MAX_LOW_GEAR_SPEED,

                // Gyro used for this subsystem
                new TAnalogGyro(RobotMap.GYRO_PORT, RobotMap.GYRO_ISINVERTED),

                // Gyro PID Constants
                RobotConst.DRIVE_GYRO_PID_KP,
                RobotConst.DRIVE_GYRO_PID_KI,
                RobotConst.DRIVE_MAX_ROTATION_OUTPUT);
    }

    @Override
    public void init() {
        distanceSensor.calibrate(
                RobotConst.ULTRASONIC_VOLTAGE_20IN,
                RobotConst.ULTRASONIC_VOLTAGE_40IN,
                RobotConst.ULTRASONIC_VOLTAGE_80IN);
    }

    // Initialize the default command for the Chassis subsystem.
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultDriveCommand());
    }

    @Override
    public void updatePeriodic() {
        super.updatePeriodic();

        SmartDashboard.putNumber("Ultrasonic Voltage", distanceSensor.getRawVoltage());
        SmartDashboard.putNumber("Ultrasonic Distance", distanceSensor.getDistance());
    }

}