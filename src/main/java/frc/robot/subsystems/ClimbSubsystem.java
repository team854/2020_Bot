package frc.robot.subsystems;

import com.torontocodingcollective.speedcontroller.TPwmSpeedController;
import com.torontocodingcollective.speedcontroller.TPwmSpeedController.TPwmSpeedControllerType;
import com.torontocodingcollective.subsystem.TSubsystem;
import frc.robot.commands.climb.DefaultClimbCommand;

public class ClimbSubsystem extends TSubsystem {

    private TPwmSpeedController deploy =    new TPwmSpeedController(TPwmSpeedControllerType.SPARK, 0, false);
    private TPwmSpeedController winch =     new TPwmSpeedController(TPwmSpeedControllerType.SPARK, 0, false);

    @Override
    public void init() {}

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultClimbCommand());
    }

    public void setDeploySpeed(double speed) {
        deploy.set(speed);
    }

    public double getDeploySpeed() {
        return deploy.get();
    }

    public void setWinchSpeed(double speed) {
        winch.set(speed);
    }

    public double getWinchSpeed() {
        return winch.get();
    }

    @Override
    public void updatePeriodic() {
        // TODO: Put stuff here??
    }
}