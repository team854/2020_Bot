package frc.robot.commands.controlpanel;
import frc.robot.RobotConst;
import frc.robot.subsystems.ControlPanelSubsystem;
import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.util.Color;


public class DefaultControlPanelCommand extends TSafeCommand {

    private static final    String  COMMAND_NAME =   DefaultControlPanelCommand.class.getSimpleName();

    // State vars
    private boolean spinRoutine     = false;    // Are we trying to spin a number of times right now?
    private boolean colorRoutine    = false;
    private Color   curColor;
    private Color   prevColor;
    private int     numColors       = -1;       // No colours have been counted yet
    private Color   gameColor;                  // Color from the field (FMS)
    private Color   targetColor;                // Actual color we want to go to, based on where our sensor is

    private enum State {  // It's a state machine
        NOTHING,
        MANUAL,
        SPIN_TIMES_START,       // Starting the routine described below
        SPIN_TIMES,             // Currently spinning the CP a number of times
        FIND_COLOR_START,       // Start of the routine for finding a specific color
        FINDING_COLOR,          // Currently in that routine
        FOUND_COLOR_INIT,       // Turns off motor and sets timer, then goes to FOUND_COLOR
        FOUND_COLOR,            // It thinks it found the color, now that has to be checked
        FINDING_COLOR_REVERSE,  // Going backwards slowly to find the color after spinning too far
        FINDING_COLOR_FORWARD   // Going forwards slowly if the reverse method goes too far - idk about this one
    }
    private State   state       = State.NOTHING;
    private double  startTime   = 0;
    // If FINDING_COLOR_REVERSE just happened, or not. Used to determine whether FINDING_COLOR_FORWARD
    // should be used instead.
    // XXX: Can this be removed gracefully?
    private boolean reversing   = false;
             

    public DefaultControlPanelCommand() {
        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);
        requires(Robot.controlPanelSubsystem);
    }

    @Override
    protected String getCommandName() { return COMMAND_NAME; }

    @Override
    protected String getParmDesc() { 
        return super.getParmDesc(); 
    }
    
    @Override
    protected void initialize() {
        // Do nothing
    }

    @Override
    protected void execute() {

        // Pressing the manual button should override anything else that's happening
        if (Robot.oi.getCPManualSpin()) {
            state = State.MANUAL;
        }

        switch (state) {
            case NOTHING:  // Most used state
                Robot.controlPanelSubsystem.setMotorSpeed(0);
                // Check buttons
                if (Robot.oi.getCPSpinTimes()) {
                    state = State.SPIN_TIMES_START;
                } else if (Robot.oi.getCPColorSpin()) {
                    state = State.FIND_COLOR_START;
                }
                break;
            case MANUAL:
                if (Robot.oi.getCPManualSpin()) {
                    Robot.controlPanelSubsystem.setMotorSpeed(RobotConst.CP_SPIN_SPEED);
                } else {  // Button no longer being pressed
                    state = State.NOTHING;
                }
                break;
            case SPIN_TIMES_START:
                prevColor   = Robot.controlPanelSubsystem.getColorSensorColor();
                numColors   = 0;  // First color
                Robot.controlPanelSubsystem.setMotorSpeed(RobotConst.CP_SPIN_SPEED);
                state = State.SPIN_TIMES;
                break;
            case SPIN_TIMES:
                if (numColors >= RobotConst.CP_SECTIONS) {  // Done spinning
                    state = State.NOTHING;
                } else {
                    curColor = Robot.controlPanelSubsystem.getColorSensorColor();
                    // Check for a new color
                    if (curColor != prevColor) {
                        numColors++;
                        prevColor = curColor;
                    }
                }
                break;
            case FIND_COLOR_START:
                if (Robot.controlPanelSubsystem.getSpecifiedTargetColor() != ControlPanelSubsystem.UNKNOWN_TARGET) {
                    // Set targetColor based on gameColor
                    gameColor = Robot.controlPanelSubsystem.getSpecifiedTargetColor();
                    if (gameColor == ControlPanelSubsystem.BLUE_TARGET) {
                        targetColor = ControlPanelSubsystem.RED_TARGET;
                    } else if (gameColor == ControlPanelSubsystem.GREEN_TARGET) {
                        targetColor = ControlPanelSubsystem.YELLOW_TARGET;
                    } else if (gameColor == ControlPanelSubsystem.RED_TARGET) {
                        targetColor = ControlPanelSubsystem.BLUE_TARGET;
                    } else if (gameColor == ControlPanelSubsystem.YELLOW_TARGET) {
                        targetColor = ControlPanelSubsystem.GREEN_TARGET;
                    }
                    Robot.controlPanelSubsystem.setMotorSpeed(RobotConst.CP_SPIN_SPEED);
                    state = State.FINDING_COLOR;
                } else {
                    // No colors from the FMS yet
                    state = State.NOTHING;
                }
                break;
            case FINDING_COLOR:
                if (targetColor == Robot.controlPanelSubsystem.getColorSensorColor()) {
                    state = State.FOUND_COLOR_INIT;
                }
                break;
            case FOUND_COLOR_INIT:
                Robot.controlPanelSubsystem.setMotorSpeed(0);
                startTime = timeSinceInitialized();
                state = State.FOUND_COLOR;
                break;
            case FOUND_COLOR:
                // Double check, sometimes the motor spins past the color after finding it
                // This waits for 1.5 secs so the motor can stop
                if (targetColor == Robot.controlPanelSubsystem.getColorSensorColor() && timeSinceInitialized()-startTime >= 1.5) {
                    state = State.NOTHING;  // The color's been found
                } else if (targetColor != Robot.controlPanelSubsystem.getColorSensorColor() && timeSinceInitialized()-startTime >= 1.5){
                    // Go back/forward and find it again
                    if (reversing) {
                        state = State.FINDING_COLOR_FORWARD;
                    } else {
                        state = State.FINDING_COLOR_REVERSE;
                    }
                }
                break;
            case FINDING_COLOR_REVERSE:
                reversing = true;
                Robot.controlPanelSubsystem.setMotorSpeed(-RobotConst.CP_SLOW_SPIN_SPEED);
                if (targetColor == Robot.controlPanelSubsystem.getColorSensorColor()) {
                    state = State.FOUND_COLOR_INIT;
                }
                break;
            case FINDING_COLOR_FORWARD:
                reversing = false;
                Robot.controlPanelSubsystem.setMotorSpeed(RobotConst.CP_SLOW_SPIN_SPEED);
                if (targetColor == Robot.controlPanelSubsystem.getColorSensorColor()) {
                    state = State.FOUND_COLOR_INIT;
                }
                break;
        }

        /*if (Robot.oi.getCPManualSpin()) {
            Robot.controlPanelSubsystem.setMotorSpeed(RobotConst.CP_SPIN_SPEED);
            // Cancel any running jobs
            spinRoutine = false;
            colorRoutine = false;
            return;
        }

        // Start spin routine
        if (Robot.oi.getCPSpinTimes() && spinRoutine == false && colorRoutine == false) {
            spinRoutine = true;
            prevColor   = Robot.controlPanelSubsystem.getColorSensorColor();
            numColors   = 0;  // First color
            Robot.controlPanelSubsystem.setMotorSpeed(RobotConst.CP_SPIN_SPEED);
            return;
        }

        // Spin routine is happening
        if (spinRoutine == true) {     
            // Check if we're done
            if (numColors >= RobotConst.CP_SECTIONS) {
                Robot.controlPanelSubsystem.setMotorSpeed(0);
                spinRoutine = false;
                return;
            }
            curColor = Robot.controlPanelSubsystem.getColorSensorColor();
            // Check for a new color
            if (curColor != prevColor) {
                numColors++;
                prevColor = curColor;
            } else {
                return;
            }
        }

        // Start color routine - check that there is actually data from the FMS
        if (Robot.oi.getCPColorSpin() && spinRoutine == false && colorRoutine == false 
            && Robot.controlPanelSubsystem.getSpecifiedTargetColor() != ControlPanelSubsystem.UNKNOWN_TARGET) {
            
            colorRoutine = true;
            // Set targetColor based on gameColor
            gameColor = Robot.controlPanelSubsystem.getSpecifiedTargetColor();
            if (gameColor == ControlPanelSubsystem.BLUE_TARGET) {
                targetColor = ControlPanelSubsystem.RED_TARGET;
            } else if (gameColor == ControlPanelSubsystem.GREEN_TARGET) {
                targetColor = ControlPanelSubsystem.YELLOW_TARGET;
            } else if (gameColor == ControlPanelSubsystem.RED_TARGET) {
                targetColor = ControlPanelSubsystem.BLUE_TARGET;
            } else if (gameColor == ControlPanelSubsystem.YELLOW_TARGET) {
                targetColor = ControlPanelSubsystem.GREEN_TARGET;
            }
            Robot.controlPanelSubsystem.setMotorSpeed(RobotConst.CP_SPIN_SPEED);
            return;
        }
        
        // Color routine is done
        if (colorRoutine == true && targetColor == Robot.controlPanelSubsystem.getColorSensorColor()) {
            Robot.controlPanelSubsystem.setMotorSpeed(0);
            colorRoutine = false;
            return;
        }

        // If nothing is happening
        if (colorRoutine == false && spinRoutine == false) {
            Robot.controlPanelSubsystem.setMotorSpeed(0);
            return;
        }*/
    }

    @Override
    protected boolean isFinished() {
        // Default Commands should never finish(end).
        return false;
    }

    @Override
    protected void end() {}

}