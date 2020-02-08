package frc.robot.commands.ball;

import com.torontocodingcollective.commands.TSafeCommand;
import com.torontocodingcollective.TConst;
import frc.robot.Robot;

public class IntakeDeployCommand extends TSafeCommand {

    private static final String COMMAND_NAME = IntakeDeployCommand.class.getSimpleName();

    public IntakeDeployCommand() {
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
        
    }

    @Override
    protected void execute() {}

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {

    }

}