package frc.robot.commands;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.drive.TDriveTimeCommand;
import com.torontocodingcollective.commands.gyroDrive.TDriveOnHeadingDistanceCommand;
import com.torontocodingcollective.commands.gyroDrive.TRotateToHeadingCommand;
import frc.robot.commands.ball.OutakeCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.ball.IntakeCommand;
import frc.robot.oi.AutoSelector;
import frc.robot.commands.auto.AutoDelay;
import com.torontocodingcollective.speedcontroller.TSpeeds;
import com.torontocodingcollective.commands.gyroDrive.TDriveBackwardsOnHeadingCommand;
import com.torontocodingcollective.commands.gyroDrive.TDriveBackwardsOnHeadingDistanceCommand;
import frc.robot.subsystems.BallSubsystem.OutakeState;

/**
 * AutonomousCommand
 * <p>
 * This class extends the CommandGroup class which allows for a string of
 * commands to be chained together to create complex auto patterns.
 */
public class AutonomousCommand extends CommandGroup {

    public static final char LEFT   = 'L';
    public static final char RIGHT  = 'R';
    public static final char CENTER = 'C';

    /**
     * Autonomous Command
     * <p>
     * Construct an Autonomous Command to perform the auto portion of the robot
     * game. This command will be built when the constructor is called and each
     * element of the command will execute in order.
     * <p>
     * When a parallel command is started, it will act at the same time as all other
     * parallel commands and the next serial command. Parallel commands can end
     * before the serial command, however, when the serial command is complete, all
     * parallel commands will be interrupted at that time if they have not already
     * finished.
     * <p>
     * Since the commands are all constructed at the same instant (when this
     * constructor is called), the commands should not read sensor information in
     * the constructor. All commands should read any relevant sensor information
     * (speed, heading, position) in the init() method of the command. The init()
     * method will be run when the command starts and so can get the robot
     * information at the start of the command, the constructor will be run
     * immediately when the Auto CommandGroup is constructed, and will not have the
     * sensor information relevant to when the command is run.
     */
    public AutonomousCommand() {
        // getting info
        String robotStartPosition = AutoSelector.getRobotStartPosition();
        String pattern            = AutoSelector.getPattern();
        double delayTime          = AutoSelector.getDelayTime();

        // Print out the user selection and Game config for debug later
        System.out.println("Auto Command Configuration");
        System.out.println("--------------------------");
        System.out.println("Robot Position : " + robotStartPosition);
        System.out.println("Pattern        : " + pattern);
        System.out.println("Delay Time     : " + delayTime);

        // Delay before pattern
        if (delayTime > 0) {
            addSequential(new AutoDelay(delayTime));
        }

        switch (pattern) {
            case AutoSelector.PATTERN_NOTHING:
                break;
            case AutoSelector.PATTERN_SCORE:
                switch (robotStartPosition) {
                    case AutoSelector.ROBOT_RIGHT:
                        // Move to goal
                        addSequential(new TDriveBackwardsOnHeadingDistanceCommand(84.75, 180, -0.5, 10, true,
                                                Robot.oi, Robot.driveSubsystem));  // 84.75 inches before
                        addSequential(new AutoDelay(0.1));
                        // Push a bit to counteract bounce
                        addSequential(new TDriveBackwardsOnHeadingCommand(180, -0.20, 1, Robot.oi, Robot.driveSubsystem));
                        // Score while pushing against the outake
                        addParallel(new TDriveBackwardsOnHeadingCommand(180, -0.20, 2, Robot.oi, Robot.driveSubsystem));
                        addSequential(new OutakeCommand(OutakeState.TOP_OUTAKE, 2));
                        // Drive back
                        //addSequential(new TDriveOnHeadingDistanceCommand(95, 0, 0.5, 10, true, Robot.oi, Robot.driveSubsystem));
                        break;
                    case AutoSelector.ROBOT_RIGHT_EDGE:
                        break;
                    case AutoSelector.ROBOT_CENTER:
                        break;
                }
                break;
            case AutoSelector.PATTERN_PICKUP_SCORE:
                switch (robotStartPosition) {
                    case AutoSelector.ROBOT_RIGHT_EDGE:
                        addParallel(new IntakeCommand(5));
                        // Pickup two balls
                        addSequential(new TDriveOnHeadingDistanceCommand(162, 0, 0.5, 5, true,
                                            Robot.oi, Robot.driveSubsystem));
                        addSequential(new AutoDelay(0.1));
                        // Drive backwards at an angle
                        addSequential(new TDriveOnHeadingDistanceCommand(250, 340, -0.5, 5, true,
                                            Robot.oi, Robot.driveSubsystem));
                        addSequential(new AutoDelay(0.1));
                        // Align to score
                        addSequential(new TDriveOnHeadingDistanceCommand(50, 0, -0.5, 5, true,
                                            Robot.oi, Robot.driveSubsystem));
                        addSequential(new AutoDelay(0.1));
                        // Score
                        addSequential(new OutakeCommand(OutakeState.TOP_OUTAKE, 2));
                        moveAcross();
                        break;
                }
                break;
            case AutoSelector.PATTERN_PICKUP_OPP_CP:
                switch (robotStartPosition) {
                    case AutoSelector.ROBOT_LEFT:
                    
                }
            case AutoSelector.PATTERN_MOVE:
                addSequential(new TDriveOnHeadingDistanceCommand(30, 0, 0.5, 5, true, Robot.oi, Robot.driveSubsystem));
                break;
            case AutoSelector.PATTERN_FEED:
                //addSequential(new AutoDelay(0));
                addSequential(new OutakeCommand(OutakeState.BOTTOM_OUTAKE, 4));
                addSequential(new TDriveOnHeadingDistanceCommand(30, 0, 0.5, 5, true, Robot.oi, Robot.driveSubsystem));
                break;
        }
    }

    private void moveAcross() {
        // Move across the field, still behind the auto starting line
        // Assumes the robot is at the score area
         // Move back
        addSequential(new TDriveOnHeadingDistanceCommand(42.375, 0, 0.5, 5, true,
                            Robot.oi, Robot.driveSubsystem));
        addSequential(new AutoDelay(0.1));
        // Turn right
        addSequential(new TRotateToHeadingCommand(90, Robot.oi, Robot.driveSubsystem));
        addSequential(new AutoDelay(0.1));
        // Move across
        addSequential(new TDriveOnHeadingDistanceCommand(120, 90, 0.5, 5, true, 
                            Robot.oi, Robot.driveSubsystem));
    }
}
