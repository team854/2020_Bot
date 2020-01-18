package frc.robot.commands.controlpanel;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.subsystems.ControlPanelSubsystem;
import edu.wpi.first.wpilibj.util.Color;


public class DefaultControlPanelCommand extends TSafeCommand {

    private static final    String  COMMAND_NAME =   DefaultControlPanelCommand.class.getSimpleName();

    // State vars
    private Color   startColor;
    private boolean spinRoutine     = false;    // Are we trying to spin a number of times right now?
    private boolean colourRoutine   = false;
    private Color   curColor;
    private Color   prevColor;
    private int     numColors       = -1;       // No colours have been counted yet
    private Color   gameColor;                  // Color from the field (FMS)
    private Color   targetColor;                // Actual color we want to go to

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
        // TODO: Write loops for spinning and detecting colour that keep track of state
        // TODO: Motor values are dummy - should rotate less than 60 rpm

        // Start spin routine
        if (Robot.oi.getCPSpinTimes() && spinRoutine == false && colourRoutine == false) {
            spinRoutine = true;
            startColor  = Robot.controlPanelSubsystem.getColorSensorColor();
            prevColor   = startColor;
            numColors   = 0;  // First color
            Robot.controlPanelSubsystem.setMotorSpeed(1);
        }

        // Spin routine is happening
        if (spinRoutine == true) {     
            // Check if we're done
            if (numColors == RobotConst.CP_SECTIONS) {
                Robot.controlPanelSubsystem.setMotorSpeed(0);
                spinRoutine = false;
                return;
            }

            curColor = Robot.controlPanelSubsystem.getColorSensorColor();

            // Check for new color, but not the same block as the start
            if (curColor != prevColor) {
                numColors++;
                prevColor = curColor;
            } else {
                return;
            }
        }

        // Start color routine - check that there is actually data
        if (Robot.oi.getCPColorSpin() && spinRoutine == false && colourRoutine == false 
            && Robot.controlPanelSubsystem.getSpecifiedTargetColor() != ControlPanelSubsystem.UNKNOWN_TARGET) {
                // set targetColor based on gameColor
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