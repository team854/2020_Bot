package frc.robot.commands.ball;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;
import frc.robot.Robot;

public class DefaultBallCommand extends TSafeCommand {

    private static final    String  COMMAND_NAME =   DefaultBallCommand.class.getSimpleName();
    private                 boolean intakeState =    false;
    private                 boolean outakeState =    false;
    private                 double  startTime   =    0;
    private                 boolean isIntakeDeployDown = false;

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
        // Deploy
        // TODO: All motor speeds are dummy values - they will need to be adjusted

        if (Robot.oi.getIntakeDeployDown()) {
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
        }
        // Internal intake or outake motors
        if (Robot.oi.getOutake()) {  // Outake take priority if both are pressed
            setOutakeState(true);
        } else if (Robot.oi.getIntakeBall()) {
            setIntakeState(true);
        } else {
            setIntakeState(false);
            setOutakeState(false);
        }
    }

    protected void setIntakeState(boolean state) {
        // True means start intaking, False means stop
        // TODO: All motor speeds are dummy values - they will need to be adjusted
        //       Some should be made negative, for some 1 is too fast

        intakeState = state;

        if (state) {
            setOutakeState(false);  // Can't intake and outake at the same time
            Robot.ballSubsystem.setOutakeSpeed(-1);  // Outake motors spin backwards while intaking
            Robot.ballSubsystem.setIntakeWheelsSpeed(1);
            Robot.ballSubsystem.setIntakeCordsSpeed(1);
        } else {
            Robot.ballSubsystem.setIntakeWheelsSpeed(0);
            Robot.ballSubsystem.setIntakeCordsSpeed(0);
            Robot.ballSubsystem.setOutakeSpeed(0);
        }
    }

    protected boolean getIntakeState() {
        return intakeState;
    }

    protected void setOutakeState(boolean state) {
        // True means start outaking, False means stop
        // TODO: All motor speeds are dummy values - they will need to be adjusted
        //       Some should be made negative, for some 1 is too fast

        outakeState = state;

        if (state) {
            setIntakeState(false);  // Can't intake and outake at the same time
            Robot.ballSubsystem.setOutakeSpeed(1);
            Robot.ballSubsystem.setIntakeCordsSpeed(1);
        } else {
            Robot.ballSubsystem.setOutakeSpeed(0);
            Robot.ballSubsystem.setIntakeCordsSpeed(0);
        }
    }

    protected boolean getOutakeState() {
        return outakeState;
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