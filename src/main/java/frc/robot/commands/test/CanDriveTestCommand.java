package frc.robot.commands.test;

import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import com.torontocodingcollective.TConst;

public class CanDriveTestCommand extends TSafeCommand {

    private static final    String  COMMAND_NAME =   CanDriveTestCommand.class.getSimpleName();
    private boolean finished = false;
    public CanDriveTestCommand() {
        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);
        requires(Robot.canDriveTestSubsystem);
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
        if (!Robot.oi.getCanDriveTest()) {
            return;
        }

        if (finished) {
            // Start again
            finished = false;
        }

        // Run each motor for 2 seconds, individually

        double motorSpeed = 0.25;
        Robot.canDriveTestSubsystem.setLeft1Speed(motorSpeed);
        Timer.delay(2);
        Robot.canDriveTestSubsystem.setLeft1Speed(0);
        Robot.canDriveTestSubsystem.setLeft2Speed(motorSpeed);
        Timer.delay(2);
        Robot.canDriveTestSubsystem.setLeft2Speed(0);
        Robot.canDriveTestSubsystem.setLeft3Speed(motorSpeed);
        Timer.delay(2);
        Robot.canDriveTestSubsystem.setLeft3Speed(0);
        Robot.canDriveTestSubsystem.setRight1Speed(motorSpeed);
        Timer.delay(2);
        Robot.canDriveTestSubsystem.setRight1Speed(0);
        Robot.canDriveTestSubsystem.setRight2Speed(motorSpeed);
        Timer.delay(2);
        Robot.canDriveTestSubsystem.setRight2Speed(0);
        Robot.canDriveTestSubsystem.setRight3Speed(motorSpeed);
        Timer.delay(2);
        Robot.canDriveTestSubsystem.setRight3Speed(0);
        finished = true;
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    protected void end() {}

}