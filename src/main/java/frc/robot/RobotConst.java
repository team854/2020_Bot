package frc.robot;

public class RobotConst {

    public static final String  TEST_ROBOT                    = "TestRobot";
    public static final String  PROD_ROBOT                    = "ProdRobot";

    // *********************************************************
    // Drive Constants
    // *********************************************************
    // Forward for the elevator is counter-clockwise when looking
    // from the back of the robot towards the front
    public static final double  MAX_LOW_GEAR_SPEED;

    public static final double  DRIVE_GYRO_PID_KP;
    public static final double  DRIVE_GYRO_PID_KI;
    public static final double  DRIVE_MAX_ROTATION_OUTPUT     = 0.50;

    public static final double  DRIVE_SPEED_PID_KP;
    public static final double  DRIVE_SPEED_PID_KI;

    public static final double  ENCODER_COUNTS_PER_INCH;

    // *********************************************************
    // For Ultrasonic Calibration
    // *********************************************************
    public static final double  ULTRASONIC_VOLTAGE_20IN;
    public static final double  ULTRASONIC_VOLTAGE_40IN;
    public static final double  ULTRASONIC_VOLTAGE_80IN;

    /** Distance behind the front bumper */
    public static final double  ULTRASONIC_RECESS;

    // Robot length in inches
    public static final double  ROBOT_LENGTH = 35.25;

    // Confidence threshold for color detection
    public static final double  COLOR_CONFIDENCE = 0.90;

    // Motor speed %
    public static final double  MOTOR_SPEED_PERCENT = .95;

    // Speed accel. curve y = mx + b variables
    public static final double ACCEL_CURVE_M    = 1.5;
    public static final double ACCEL_CURVE_B    = 0;
    public static final double DEACCEL_CURVE_M  = 1;
    public static final double DEACCEL_CURVE_B  = 0;

    public static final double OPERATOR_DRIVE_SPEED_PERCENT = 0.60;
    public static final double OPERATOR_TURN_SPEED_PERCENT  = 0.95;
    
    public static enum Direction {
        FORWARD, BACKWARD
    };
    
    // Number of color sections to rotate through on the CP
    public static final int CP_SECTIONS             = 35;  // Two more sections than 3 perfect rotations, with each red seciton counting as two
    public static final double CP_SPIN_SPEED        = 0.33;
    public static final double CP_SLOW_SPIN_SPEED   = 0.25;

    // The TorontoCodingCollective framework was developed to run on different
    // robots through the use of multiple mappings and constants.
    public static final String robot = PROD_ROBOT;

    static {

        switch (robot) {

        case PROD_ROBOT:

            // The low gear speed should be set just below the
            // maximum loaded speed of the robot
            MAX_LOW_GEAR_SPEED = 19000; // Encoder counts/sec
            //MAX_HIGH_GEAR_SPEED = 900.0;

            // Typically set the integral gain at 1/20 of the
            // proportional gain.  The gain can often be increased
            // above this value, but typically gives good
            // stability and acceptable performance
            DRIVE_GYRO_PID_KP = 0.05;
            DRIVE_GYRO_PID_KI = 0.005;

            DRIVE_SPEED_PID_KP = 0.2;
            //DRIVE_SPEED_PID_KI = DRIVE_SPEED_PID_KP / 20.0;
            DRIVE_SPEED_PID_KI = 0.0000;

            ENCODER_COUNTS_PER_INCH = 105;

            ULTRASONIC_VOLTAGE_20IN       = 0.18;
            ULTRASONIC_VOLTAGE_40IN       = 0.38;
            ULTRASONIC_VOLTAGE_80IN       = 0.76;
            ULTRASONIC_RECESS             = 10.0;

            break;

        case TEST_ROBOT:
        default:

            // The low gear speed should be set just below the
            // maximum loaded speed of the robot
            MAX_LOW_GEAR_SPEED = 2900.0; // Encoder counts/sec
            //MAX_HIGH_GEAR_SPEED = 900.0;

            // Typically set the integral gain at 1/20 of the
            // proportional gain.  The gain can often be increased
            // above this value, but typically gives good
            // stability and acceptable performance
            DRIVE_GYRO_PID_KP = .03;
            DRIVE_GYRO_PID_KI = DRIVE_GYRO_PID_KP / 20.0;

            DRIVE_SPEED_PID_KP = 0.0045;
            //DRIVE_SPEED_PID_KI = DRIVE_SPEED_PID_KP / 20.0;
            DRIVE_SPEED_PID_KI = 0.001;

            ENCODER_COUNTS_PER_INCH = 28.52;

            ULTRASONIC_VOLTAGE_20IN       = 0.45;
            ULTRASONIC_VOLTAGE_40IN       = 0.96;
            ULTRASONIC_VOLTAGE_80IN       = 1.85;
            ULTRASONIC_RECESS             = 8.0;

            break;
        }

    }
}
