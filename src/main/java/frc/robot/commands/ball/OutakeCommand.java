package frc.robot.commands.ball;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;
import frc.robot.Robot;
import frc.robot.subsystems.BallSubsystem.OutakeState;

public class OutakeCommand extends TSafeCommand {

    private static final    String  COMMAND_NAME    = OutakeCommand.class.getSimpleName();
    private OutakeState state;

    public OutakeCommand(OutakeState state, double timeout) {
        super(timeout, Robot.oi);
        this.state = state;
        requires(Robot.ballSubsystem);
    }

    @Override
    protected String getCommandName() { return COMMAND_NAME; }

    @Override
    protected String getParmDesc() { 
        return super.getParmDesc(); 
    }
    
    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
        Robot.ballSubsystem.setOutakeState(state);
    }

    @Override
    protected boolean isFinished() {
        return super.isFinished();
    }

    @Override
    protected void end() {
        Robot.ballSubsystem.setOutakeState(OutakeState.NO_OUTAKE);
    }
}