package frc.robot.commands.climb;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;
import frc.robot.Robot;

public class DefaultClimbCommand extends TSafeCommand {

    private static final    String  COMMAND_NAME =   DefaultClimbCommand.class.getSimpleName();

    public DefaultClimbCommand() {
        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);
        requires(Robot.climbSubsystem);
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
        // TODO: Motor speeds are dummy, MUST be changed
        
        if (Robot.oi.getWinchDown()) {
            // Winch and bring up hook
            Robot.climbSubsystem.setWinchSpeed(1);
            Robot.climbSubsystem.setDeploySpeed(-1);
        } else if (Robot.oi.getHookUp()) {
            Robot.climbSubsystem.setDeploySpeed(1);
        } else if (Robot.oi.getHookDown()) {
            Robot.climbSubsystem.setDeploySpeed(-1);
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