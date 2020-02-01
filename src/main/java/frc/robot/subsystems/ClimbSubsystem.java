package frc.robot.subsystems;

import com.torontocodingcollective.speedcontroller.TPwmSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;
import frc.robot.commands.climb.DefaultClimbCommand;
import frc.robot.RobotMap;

public class ClimbSubsystem extends TSubsystem {

    private TPwmSpeedController deploy =    new TPwmSpeedController(
                                                    RobotMap.CLIMB_DEPLOY_PWM_SPEED_CONTROLLER_TYPE,
                                                    RobotMap.CLIMB_DEPLOY_PWM_SPEED_CONTROLLER_ADDRESS,
                                                    RobotMap.CLIMB_DEPLOY_PWM_MOTOR_ISINVERTED
                                                );
    private TPwmSpeedController winch =     new TPwmSpeedController(
                                                    RobotMap.CLIMB_WINCH_PWM_SPEED_CONTROLLER_TYPE,
                                                    RobotMap.CLIMB_WINCH_PWM_SPEED_CONTROLLER_ADDRESS,
                                                    RobotMap.CLIMB_WINCH_PWM_MOTOR_ISINVERTED
                                                );

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