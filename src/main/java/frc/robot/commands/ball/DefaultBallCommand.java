package frc.robot.commands.ball;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;
import frc.robot.Robot;

public class DefaultBallCommand extends TSafeCommand {

    private static final    String  COMMAND_NAME =   DefaultBallCommand.class.getSimpleName();
    private                 double  startTime   =    0;
    private                 boolean isIntakeDeployDown = false;

    private enum OutakeState {TOP_OUTAKE, BOTTOM_OUTAKE, NO_OUTAKE};

    public DefaultBallCommand() {
        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);
        requires(Robot.ballSubsystem);
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

        /*if (Robot.oi.getIntakeDeployDown()) {
            if (isIntakeDeployDown) {
                if (timeSinceInitialized()-startTime >= 1) {
                    Robot.ballSubsystem.setIntakeDeploySpeed(0);
                }
            } else {
                isIntakeDeployDown = true;
                startTime = timeSinceInitialized();
                Robot.ballSubsystem.setIntakeDeploySpeed(0.4);
            }
        } else if (Robot.oi.getIntakeDeployUp()) {
            if (!isIntakeDeployDown) {
                if (timeSinceInitialized()-startTime >= 1) {
                    Robot.ballSubsystem.setIntakeDeploySpeed(0);
                }
            } else {
                isIntakeDeployDown = false;
                startTime = timeSinceInitialized();
                Robot.ballSubsystem.setIntakeDeploySpeed(-0.4);
            }
        }*/

        // Internal intake or outake motors
        if (Robot.oi.getOutake()) {  // Outake takes priority if both are pressed
            setOutakeState(OutakeState.TOP_OUTAKE);
        } else if (Robot.oi.getBottomOutake()) {
            setOutakeState(OutakeState.BOTTOM_OUTAKE);
        } else if (Robot.oi.getIntakeBall()) {
            setIntakeState(true);
        } else {
            setIntakeState(false);
            setOutakeState(OutakeState.NO_OUTAKE);
        }

        // Intake arm down happens if intaking as well, in setIntakeState()
        if (Robot.oi.getIntakeDeployUp()) {
            Robot.ballSubsystem.setIntakeDeploySpeed(-1);
        } else if (Robot.oi.getIntakeDeployDown()) {
            Robot.ballSubsystem.setIntakeDeploySpeed(1);
        }
    }

    protected void setIntakeState(boolean state) {
        // True means start intaking, False means stop

        if (state) {
            setOutakeState(OutakeState.NO_OUTAKE);  // Can't intake and outake at the same time
            Robot.ballSubsystem.setOutakeSpeed(0.33);  // Outake motors spin backwards while intaking
            Robot.ballSubsystem.setIntakeWheelsSpeed(-1);
            Robot.ballSubsystem.setIntakeCordsSpeed(0.33);
            Robot.ballSubsystem.setIntakeDeploySpeed(1);  // Push arm down when intaking
        } else {
            Robot.ballSubsystem.setIntakeWheelsSpeed(0);
            Robot.ballSubsystem.setIntakeCordsSpeed(0);
            Robot.ballSubsystem.setOutakeSpeed(0);
            Robot.ballSubsystem.setIntakeDeploySpeed(0);
        }
    }

    protected void setOutakeState(OutakeState state) {
        if (state == OutakeState.TOP_OUTAKE) {
            setIntakeState(false);  // Can't intake and outake at the same time
            Robot.ballSubsystem.setOutakeSpeed(-1);
            Robot.ballSubsystem.setIntakeCordsSpeed(1);
        } else if (state == OutakeState.BOTTOM_OUTAKE) {
            setIntakeState(false);
            Robot.ballSubsystem.setOutakeSpeed(0);
            Robot.ballSubsystem.setIntakeCordsSpeed(-0.90);
            Robot.ballSubsystem.setIntakeWheelsSpeed(1);
        } else if (state == OutakeState.NO_OUTAKE) {
            Robot.ballSubsystem.setOutakeSpeed(0);
            Robot.ballSubsystem.setIntakeCordsSpeed(0);
        }
    }

    @Override
    protected boolean isFinished() {
        // Default Commands should never finish(end).
        return false;
    }

    @Override
    protected void end() {

    }
}