package frc.robot.commands.ball;

import com.torontocodingcollective.commands.TSafeCommand;
import com.torontocodingcollective.TConst;
import frc.robot.Robot;

public class IntakeCommand extends TSafeCommand {

    private static final String COMMAND_NAME = IntakeCommand.class.getSimpleName();

    public IntakeCommand(double timeout) {
        super(timeout, Robot.oi);
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
    protected void execute() {
        Robot.ballSubsystem.setIntakeState(true);  // Arm will also push down
    }

    @Override
    protected boolean isFinished() {
        return super.isFinished();
    }

    @Override
    protected void end() {
        Robot.ballSubsystem.setIntakeState(false);
    }

}