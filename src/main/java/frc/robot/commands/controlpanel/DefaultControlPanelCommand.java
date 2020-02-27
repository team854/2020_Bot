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
        // TODO: Motor values are dummy - should rotate less than 60 rpm

        if (Robot.oi.getCPManualSpin()) {
            Robot.controlPanelSubsystem.setMotorSpeed(0.5);
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
            Robot.controlPanelSubsystem.setMotorSpeed(1);
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
            Robot.controlPanelSubsystem.setMotorSpeed(1);
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
        }
    }

    @Override
    protected boolean isFinished() {
        // Default Commands should never finish(end).
        return false;
    }

    @Override
    protected void end() {}

}