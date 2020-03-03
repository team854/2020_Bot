package frc.robot.commands.ball;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;
import frc.robot.Robot;
import frc.robot.subsystems.BallSubsystem.OutakeState;

public class DefaultBallCommand extends TSafeCommand {

    private static final    String  COMMAND_NAME =   DefaultBallCommand.class.getSimpleName();
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
            Robot.ballSubsystem.setOutakeState(OutakeState.TOP_OUTAKE);
        } else if (Robot.oi.getBottomOutake()) {
            Robot.ballSubsystem.setOutakeState(OutakeState.BOTTOM_OUTAKE);
        } else if (Robot.oi.getIntakeBall()) {
            Robot.ballSubsystem.setIntakeState(true);
        } else {
            Robot.ballSubsystem.setIntakeState(false);
            Robot.ballSubsystem.setOutakeState(OutakeState.NO_OUTAKE);
        }

        // Intake arm down happens if intaking as well, in setIntakeState()
        if (Robot.oi.getIntakeDeployUp()) {
            Robot.ballSubsystem.setIntakeDeploySpeed(-1);
        } else if (Robot.oi.getIntakeDeployDown()) {
            Robot.ballSubsystem.setIntakeDeploySpeed(1);
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