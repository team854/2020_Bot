package frc.robot;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.speedcontroller.TCanSpeedController.TCanSpeedControllerType;
import com.torontocodingcollective.speedcontroller.TPwmSpeedController.TPwmSpeedControllerType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * <p>
 * This map is intended to define the wiring only. Robot constants should be put
 * in {@link RobotConst}
 */
public class RobotMap {

    // ******************************************
    // Speed Controllers and encoders
    // CAN addresses - three motors per side - NOT USED THIS YEAR
    // ******************************************
    public static final int                     LEFT_DRIVE_CAN_SPEED_CONTROLLER_ADDRESS;
    public static final TCanSpeedControllerType LEFT_DRIVE_CAN_SPEED_CONTROLLER_TYPE;
    public static final int                     LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_ADDRESS;
    public static final TCanSpeedControllerType LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_TYPE;
    public static final int                     LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS;
    public static final TCanSpeedControllerType LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_TYPE;
    public static final boolean                 LEFT_DRIVE_CAN_MOTOR_ISINVERTED;

    public static final int                     RIGHT_DRIVE_CAN_SPEED_CONTROLLER_ADDRESS;
    public static final TCanSpeedControllerType RIGHT_DRIVE_CAN_SPEED_CONTROLLER_TYPE;
    public static final int                     RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_ADDRESS;
    public static final TCanSpeedControllerType RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_TYPE;
    public static final int                     RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS;
    public static final TCanSpeedControllerType RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_TYPE;
    public static final boolean                 RIGHT_DRIVE_CAN_MOTOR_ISINVERTED;

    public static final boolean                 LEFT_DRIVE_CAN_ENCODER_ISINVERTED;
    public static final boolean                 RIGHT_DRIVE_CAN_ENCODER_ISINVERTED;

    // ******************************************
    // PWM addresses - three motors per side
    // ******************************************
    public static final int                     LEFT_DRIVE_PWM_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType LEFT_DRIVE_PWM_SPEED_CONTROLLER_TYPE;
    public static final int                     LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_TYPE;
    public static final int                     LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS;
    public static final TPwmSpeedControllerType LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_TYPE;
    public static final boolean                 LEFT_DRIVE_PWM_MOTOR_ISINVERTED;

    public static final int                     RIGHT_DRIVE_PWM_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType RIGHT_DRIVE_PWM_SPEED_CONTROLLER_TYPE;
    public static final int                     RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_TYPE;
    public static final int                     RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS;
    public static final TPwmSpeedControllerType RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_TYPE;
    public static final boolean                 RIGHT_DRIVE_PWM_MOTOR_ISINVERTED;

    public static final int                     LEFT_DRIVE_DIO_ENCODER_PORT1;
    public static final boolean                 LEFT_DRIVE_DIO_ENCODER_ISINVERTED;
    public static final int                     RIGHT_DRIVE_DIO_ENCODER_PORT1;
    public static final boolean                 RIGHT_DRIVE_DIO_ENCODER_ISINVERTED;

    public static final int                     CONTROL_PANEL_PWM_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType CONTROL_PANEL_PWM_SPEED_CONTROLLER_TYPE;
    
    // PWM for BallSubsystem (intake)
    public static final int                     INTAKE_WHEELS_PWM_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType INTAKE_WHEELS_PWM_SPEED_CONTROLLER_TYPE;
    public static final int                     INTAKE_DEPLOY_PWM_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType INTAKE_DEPLOY_PWM_SPEED_CONTROLLER_TYPE;
    public static final int                     INTAKE_CORDS_PWM_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType INTAKE_CORDS_PWM_SPEED_CONTROLLER_TYPE;
    public static final int                     OUTAKE_PWM_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType OUTAKE_PWM_SPEED_CONTROLLER_TYPE;

    public static final int                     CLIMB_DEPLOY_PWM_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType CLIMB_DEPLOY_PWM_SPEED_CONTROLLER_TYPE;
    public static final int                     CLIMB_WINCH_PWM_SPEED_CONTROLLER_ADDRESS;
    public static final TPwmSpeedControllerType CLIMB_WINCH_PWM_SPEED_CONTROLLER_TYPE;

    // ******************************************
    // Gyro Ports
    // ******************************************
    public static final int                     GYRO_PORT;
    public static final boolean                 GYRO_ISINVERTED;

    // ******************************************
    // Ultrasonic Ports
    // ******************************************
    public static final int                     ULTRASONIC_ANALOG_PORT;

    // ******************************************
    // Pneumatics Ports
    // ******************************************
    public static final int                     SHIFTER_PNEUMATIC_PORT = 0;

    // Initializers if this code will be deployed to more than one
    // robot with different mappings
    static {

        switch (RobotConst.robot) {

        case RobotConst.PROD_ROBOT:
            // CAN Constants - NOT USED THIS YEAR
            // Talon and Victor connected through the CAN Bus
            LEFT_DRIVE_CAN_SPEED_CONTROLLER_ADDRESS           = 10;
            LEFT_DRIVE_CAN_SPEED_CONTROLLER_TYPE              = TCanSpeedControllerType.TALON_SRX;
            LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_ADDRESS  = 11;
            LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_TYPE     = LEFT_DRIVE_CAN_SPEED_CONTROLLER_TYPE;
            LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS = 0;
            LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_TYPE   = LEFT_DRIVE_CAN_SPEED_CONTROLLER_TYPE;
            LEFT_DRIVE_CAN_MOTOR_ISINVERTED                   = TConst.INVERTED;
            LEFT_DRIVE_CAN_ENCODER_ISINVERTED                 = TConst.INVERTED;

            RIGHT_DRIVE_CAN_SPEED_CONTROLLER_ADDRESS          = 20;
            RIGHT_DRIVE_CAN_SPEED_CONTROLLER_TYPE             = TCanSpeedControllerType.TALON_SRX;
            RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_ADDRESS = 21;
            RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_TYPE    = RIGHT_DRIVE_CAN_SPEED_CONTROLLER_TYPE;
            RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS = 0;
            RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_TYPE  = RIGHT_DRIVE_CAN_SPEED_CONTROLLER_TYPE;
            RIGHT_DRIVE_CAN_MOTOR_ISINVERTED                  = TConst.NOT_INVERTED;
            RIGHT_DRIVE_CAN_ENCODER_ISINVERTED                = TConst.NOT_INVERTED;

            // PWM Constants
            LEFT_DRIVE_PWM_SPEED_CONTROLLER_ADDRESS           = 0;
            LEFT_DRIVE_PWM_SPEED_CONTROLLER_TYPE              = TPwmSpeedControllerType.SPARK;
            LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_ADDRESS  = 1;
            LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_TYPE     = LEFT_DRIVE_PWM_SPEED_CONTROLLER_TYPE;
            LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS = 0;
            LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_TYPE   = LEFT_DRIVE_PWM_SPEED_CONTROLLER_TYPE;
            LEFT_DRIVE_PWM_MOTOR_ISINVERTED                   = TConst.INVERTED;
            LEFT_DRIVE_DIO_ENCODER_PORT1                      = 0;
            LEFT_DRIVE_DIO_ENCODER_ISINVERTED                 = TConst.INVERTED;

            RIGHT_DRIVE_PWM_SPEED_CONTROLLER_ADDRESS          = 2;
            RIGHT_DRIVE_PWM_SPEED_CONTROLLER_TYPE             = TPwmSpeedControllerType.SPARK;
            RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_ADDRESS = 3;
            RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_TYPE    = RIGHT_DRIVE_PWM_SPEED_CONTROLLER_TYPE;
            RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS = 0;
            RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_TYPE  = RIGHT_DRIVE_PWM_SPEED_CONTROLLER_TYPE;
            RIGHT_DRIVE_PWM_MOTOR_ISINVERTED                  = TConst.NOT_INVERTED;
            RIGHT_DRIVE_DIO_ENCODER_PORT1                     = 2;
            RIGHT_DRIVE_DIO_ENCODER_ISINVERTED                = TConst.NOT_INVERTED;

            CONTROL_PANEL_PWM_SPEED_CONTROLLER_ADDRESS  = 0;
            CONTROL_PANEL_PWM_SPEED_CONTROLLER_TYPE     = TPwmSpeedControllerType.TALON_SR;

            INTAKE_WHEELS_PWM_SPEED_CONTROLLER_ADDRESS  = 0;
            INTAKE_WHEELS_PWM_SPEED_CONTROLLER_TYPE     = TPwmSpeedControllerType.TALON_SR;
            INTAKE_DEPLOY_PWM_SPEED_CONTROLLER_ADDRESS  = 0;
            INTAKE_DEPLOY_PWM_SPEED_CONTROLLER_TYPE     = TPwmSpeedControllerType.TALON_SR;
            INTAKE_CORDS_PWM_SPEED_CONTROLLER_ADDRESS   = 0;
            INTAKE_CORDS_PWM_SPEED_CONTROLLER_TYPE      = TPwmSpeedControllerType.TALON_SR;
            OUTAKE_PWM_SPEED_CONTROLLER_ADDRESS         = 0;
            OUTAKE_PWM_SPEED_CONTROLLER_TYPE            = TPwmSpeedControllerType.TALON_SR;

            CLIMB_DEPLOY_PWM_SPEED_CONTROLLER_ADDRESS   = 0;
            CLIMB_DEPLOY_PWM_SPEED_CONTROLLER_TYPE      = TPwmSpeedControllerType.TALON_SR;
            CLIMB_WINCH_PWM_SPEED_CONTROLLER_ADDRESS    = 0;
            CLIMB_WINCH_PWM_SPEED_CONTROLLER_TYPE       = TPwmSpeedControllerType.TALON_SR;

            GYRO_PORT       = 0;
            GYRO_ISINVERTED = TConst.NOT_INVERTED;

            ULTRASONIC_ANALOG_PORT                            = 0;
            break;

        case RobotConst.TEST_ROBOT:  // NOT USED
        default:
            // PWM Constants
            // Talon and Victors connected through Pwm
            LEFT_DRIVE_PWM_SPEED_CONTROLLER_ADDRESS           = 0;
            LEFT_DRIVE_PWM_SPEED_CONTROLLER_TYPE              = TPwmSpeedControllerType.SPARK;
            LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_ADDRESS  = 1;
            LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_TYPE     = LEFT_DRIVE_PWM_SPEED_CONTROLLER_TYPE;
            LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS = 0;
            LEFT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_TYPE   = LEFT_DRIVE_PWM_SPEED_CONTROLLER_TYPE;
            LEFT_DRIVE_PWM_MOTOR_ISINVERTED                   = TConst.NOT_INVERTED;
            LEFT_DRIVE_DIO_ENCODER_PORT1                      = 0;
            LEFT_DRIVE_DIO_ENCODER_ISINVERTED                 = TConst.INVERTED;

            RIGHT_DRIVE_PWM_SPEED_CONTROLLER_ADDRESS          = 2;
            RIGHT_DRIVE_PWM_SPEED_CONTROLLER_TYPE             = TPwmSpeedControllerType.TALON_SR;
            RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_ADDRESS = 3;
            RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_TYPE    = RIGHT_DRIVE_PWM_SPEED_CONTROLLER_TYPE;
            RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS = 0;
            RIGHT_DRIVE_PWM_FOLLOWER_SPEED_CONTROLLER_2_TYPE  = RIGHT_DRIVE_PWM_SPEED_CONTROLLER_TYPE;
            RIGHT_DRIVE_PWM_MOTOR_ISINVERTED                  = TConst.INVERTED;
            RIGHT_DRIVE_DIO_ENCODER_PORT1                     = 2;
            RIGHT_DRIVE_DIO_ENCODER_ISINVERTED                = TConst.NOT_INVERTED;

            // Not used
            GYRO_PORT       = 0;
            GYRO_ISINVERTED = TConst.NOT_INVERTED;

            // CAN Constants
            // CAN Constants are unused
            LEFT_DRIVE_CAN_SPEED_CONTROLLER_ADDRESS           = 0;
            LEFT_DRIVE_CAN_SPEED_CONTROLLER_TYPE              = TCanSpeedControllerType.TALON_SRX;
            LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_ADDRESS  = 1;
            LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_TYPE     = LEFT_DRIVE_CAN_SPEED_CONTROLLER_TYPE;
            LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS = 0;
            LEFT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_TYPE   = LEFT_DRIVE_CAN_SPEED_CONTROLLER_TYPE;
            LEFT_DRIVE_CAN_MOTOR_ISINVERTED                   = TConst.NOT_INVERTED;
            LEFT_DRIVE_CAN_ENCODER_ISINVERTED                 = TConst.INVERTED;

            RIGHT_DRIVE_CAN_SPEED_CONTROLLER_ADDRESS          = 2;
            RIGHT_DRIVE_CAN_SPEED_CONTROLLER_TYPE             = TCanSpeedControllerType.TALON_SRX;
            RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_ADDRESS = 3;
            RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_TYPE    = RIGHT_DRIVE_CAN_SPEED_CONTROLLER_TYPE;
            RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_ADDRESS = 0;
            RIGHT_DRIVE_CAN_FOLLOWER_SPEED_CONTROLLER_2_TYPE  = RIGHT_DRIVE_CAN_SPEED_CONTROLLER_TYPE;
            RIGHT_DRIVE_CAN_MOTOR_ISINVERTED                  = TConst.INVERTED;
            RIGHT_DRIVE_CAN_ENCODER_ISINVERTED                = TConst.NOT_INVERTED;

            ULTRASONIC_ANALOG_PORT                            = 0;

            CONTROL_PANEL_PWM_SPEED_CONTROLLER_ADDRESS  = 0;
            CONTROL_PANEL_PWM_SPEED_CONTROLLER_TYPE     = TPwmSpeedControllerType.TALON_SR;

            INTAKE_WHEELS_PWM_SPEED_CONTROLLER_ADDRESS  = 0;
            INTAKE_WHEELS_PWM_SPEED_CONTROLLER_TYPE     = TPwmSpeedControllerType.TALON_SR;
            INTAKE_DEPLOY_PWM_SPEED_CONTROLLER_ADDRESS  = 0;
            INTAKE_DEPLOY_PWM_SPEED_CONTROLLER_TYPE     = TPwmSpeedControllerType.TALON_SR;
            INTAKE_CORDS_PWM_SPEED_CONTROLLER_ADDRESS   = 0;
            INTAKE_CORDS_PWM_SPEED_CONTROLLER_TYPE      = TPwmSpeedControllerType.TALON_SR;
            OUTAKE_PWM_SPEED_CONTROLLER_ADDRESS         = 0;
            OUTAKE_PWM_SPEED_CONTROLLER_TYPE            = TPwmSpeedControllerType.TALON_SR;

            CLIMB_DEPLOY_PWM_SPEED_CONTROLLER_ADDRESS   = 0;
            CLIMB_DEPLOY_PWM_SPEED_CONTROLLER_TYPE      = TPwmSpeedControllerType.TALON_SR;
            CLIMB_WINCH_PWM_SPEED_CONTROLLER_ADDRESS    = 0;
            CLIMB_WINCH_PWM_SPEED_CONTROLLER_TYPE       = TPwmSpeedControllerType.TALON_SR;

            break;
        }
    }
}
