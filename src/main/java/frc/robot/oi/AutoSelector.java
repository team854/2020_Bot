package frc.robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector {

    public static SendableChooser<String> robotStartPosition;

    public static final String  ROBOT_RIGHT         = "Robot Outake Facing Our Goal";
    public static final String  ROBOT_RIGHT_EDGE    = "Robot Right Edge Facing Trench";
    public static final String  ROBOT_CENTER        = "Robot Center";  // Against wall
    public static final String  ROBOT_LEFT          = "Robot Outake facing Opp dropoff";


    public static SendableChooser<String> pattern;

    public static final String  PATTERN_SCORE           = "Score";
    public static final String  PATTERN_PICKUP_SCORE    = "Pickup then score";
    public static final String  PATTERN_PICKUP_OPP_CP   = "Pickup oppositions CP Ball"; // new auto to code
    public static final String  PATTERN_NOTHING         = "Do nothing";

    public static SendableChooser<String> delayTime;
    
    public static final String            DELAY_TIME_0        = "0";
    public static final String            DELAY_TIME_1        = "1";
    public static final String            DELAY_TIME_2        = "2";
    public static final String            DELAY_TIME_3        = "3";
    public static final String            DELAY_TIME_4        = "4";
    public static final String            DELAY_TIME_5        = "5";


    static {

        // Robot Position Options
        robotStartPosition = new SendableChooser<String>();
        robotStartPosition.setDefaultOption(ROBOT_CENTER, ROBOT_CENTER);
        robotStartPosition.addOption(ROBOT_LEFT, ROBOT_LEFT);
        robotStartPosition.addOption(ROBOT_RIGHT_EDGE, ROBOT_RIGHT_EDGE);
        robotStartPosition.addOption(ROBOT_RIGHT, ROBOT_RIGHT);

        SmartDashboard.putData("Robot Start", robotStartPosition);

        // Robot Pattern Options
        pattern = new SendableChooser<String>();
        pattern.setDefaultOption(PATTERN_SCORE, PATTERN_SCORE);
        pattern.addOption(PATTERN_PICKUP_SCORE, PATTERN_PICKUP_SCORE);
        pattern.addOption(PATTERN_NOTHING, PATTERN_NOTHING);
        pattern.addOption(PATTERN_PICKUP_OPP_CP, PATTERN_PICKUP_OPP_CP);

        SmartDashboard.putData("Auto Pattern", pattern);

        // Delay time at beginning of match
        delayTime = new SendableChooser<String>();
        delayTime.setDefaultOption(DELAY_TIME_0, DELAY_TIME_0);
        delayTime.addOption(DELAY_TIME_1, DELAY_TIME_1);
        delayTime.addOption(DELAY_TIME_2, DELAY_TIME_2);
        delayTime.addOption(DELAY_TIME_3, DELAY_TIME_3);
        delayTime.addOption(DELAY_TIME_4, DELAY_TIME_4);
        delayTime.addOption(DELAY_TIME_5, DELAY_TIME_5);

        SmartDashboard.putData("Delay Time", delayTime);

        SmartDashboard.putString("Debug", "");

    }

    /**
     * Get the auto pattern.
     *
     * @return "Straight" or "Box"
     */
    public static String getPattern() {

        String selectedPattern = pattern.getSelected();

        if (selectedPattern == null) {
            return PATTERN_NOTHING;
        }

        return selectedPattern;
    }

    /**
     * Get the robot starting position on the field.
     *
     * @return 'L' for left, 'R' for right or 'C' for center
     */
    public static String getRobotStartPosition() {

        String selectedStartPosition = robotStartPosition.getSelected();

        if (selectedStartPosition == null) {
            return ROBOT_CENTER;
        }

        return selectedStartPosition;
    }

    /**
     * Get the delay before moving
     * 
     * @return double delay time
     */
    public static double getDelayTime() {

        if (delayTime.getSelected() == null) {
            System.out.println("Delay Time not chosen - overriding to " + DELAY_TIME_0);
            return 0;
        }
        return Double.valueOf(delayTime.getSelected());
    }


    public static void init() {}
}
