package frc.robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector {

    public static SendableChooser<String> robotStartPosition;

    public static final String  ROBOT_ON_TARGET     = "Robot Facing Goal";
    public static final String  ROBOT_RIGHT_EDGE    = "Robot Right Edge Facing Trench";
    public static final String  ROBOT_CENTER        = "Robot Center";  // Against wall

    public static SendableChooser<String> pattern;

    public static final String  PATTERN_SCORE           = "Score";
    public static final String  PATTERN_PICKUP_SCORE    = "Pickup then score";
    public static final String  PATTERN_NOTHING         = "Do nothing";

    static {

        // Robot Position Options
        robotStartPosition = new SendableChooser<String>();
        robotStartPosition.setDefaultOption(ROBOT_CENTER, ROBOT_CENTER);
        robotStartPosition.addOption(ROBOT_ON_TARGET, ROBOT_ON_TARGET);
        robotStartPosition.addOption(ROBOT_RIGHT_EDGE, ROBOT_RIGHT_EDGE);

        SmartDashboard.putData("Robot Start", robotStartPosition);

        // Robot Pattern Options
        pattern = new SendableChooser<String>();
        pattern.setDefaultOption(PATTERN_SCORE, PATTERN_SCORE);
        pattern.addOption(PATTERN_PICKUP_SCORE, PATTERN_PICKUP_SCORE);
        pattern.addOption(PATTERN_NOTHING, PATTERN_NOTHING);

        SmartDashboard.putData("Auto Pattern", pattern);
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

    public static void init() {}
}
