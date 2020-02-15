package frc.robot.commands.ball;

import com.torontocodingcollective.commands.TSafeCommand;
import com.torontocodingcollective.TConst;
import frc.robot.Robot;

public class IntakeCommand extends TSafeCommand {

    private static final String COMMAND_NAME = IntakeCommand.class.getSimpleName();
    private boolean deployState;
    private boolean intakeState;

    public IntakeCommand(boolean deployState, boolean intakeState) {
        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);
        requires(Robot.ballSubsystem);
        this.deployState = deployState;
        this.intakeState = intakeState;
    }

    @Override
    protected String getCommandName() { return COMMAND_NAME; }

    @Override
    protected String getParmDesc() { 
        return super.getParmDesc(); 
    }
    
    @Override
    protected void initialize() {
        // TODO: Fix this
        //Robot.oi.setIntakeDeploy(deployState);
        Robot.oi.overrideIntakeBall(intakeState);
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        // TODO: Do we have to enable motors here?

    }

}