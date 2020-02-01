package frc.robot.subsystems;

import com.torontocodingcollective.speedcontroller.TPwmSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ball.DefaultBallCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BallSubsystem extends TSubsystem {

    // All the speed controllers are fake, and assumed to be using PWM
    // The real values should be put in RobotMap.java afterward
    private TPwmSpeedController intakeWheels = new TPwmSpeedController(
                                                        RobotMap.INTAKE_WHEELS_PWM_SPEED_CONTROLLER_TYPE,
                                                        RobotMap.INTAKE_WHEELS_PWM_SPEED_CONTROLLER_ADDRESS,
                                                        RobotMap.INTAKE_WHEELS_PWM_MOTOR_ISINVERTED
                                                    );
    private TPwmSpeedController intakeDeploy = new TPwmSpeedController(
                                                        RobotMap.INTAKE_DEPLOY_PWM_SPEED_CONTROLLER_TYPE,
                                                        RobotMap.INTAKE_DEPLOY_PWM_SPEED_CONTROLLER_ADDRESS,
                                                        RobotMap.INTAKE_DEPLOY_PWM_MOTOR_ISINVERTED
                                                    );
    private TPwmSpeedController intakeCords  = new TPwmSpeedController(
                                                        RobotMap.INTAKE_CORDS_PWM_SPEED_CONTROLLER_TYPE,
                                                        RobotMap.INTAKE_CORDS_PWM_SPEED_CONTROLLER_ADDRESS,
                                                        RobotMap.INTAKE_CORDS_PWM_MOTOR_ISINVERTED
                                                    );
    private TPwmSpeedController outake       = new TPwmSpeedController(
                                                        RobotMap.OUTAKE_PWM_SPEED_CONTROLLER_TYPE,
                                                        RobotMap.OUTAKE_PWM_SPEED_CONTROLLER_ADDRESS,
                                                        RobotMap.OUTAKE_PWM_MOTOR_ISINVERTED
                                                    );

    @Override
    public void init() {}

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultBallCommand());
    }

    public void setIntakeWheelsSpeed(double speed) {
        intakeWheels.set(speed);
    }

    public double getIntakeWheelsSpeed() {
        return intakeWheels.get();
    }

    public void setIntakeDeploySpeed(double speed) {
        intakeDeploy.set(speed);
    }

    public double getIntakeDeploySpeed() {
        return intakeDeploy.get();
    }

    public void setIntakeCordsSpeed(double speed) {
        intakeCords.set(speed);
    }

    public double getIntakeCordsSpeed() {
        return intakeCords.get();
    }

    public void setOutakeSpeed(double speed) {
        outake.set(speed);
    }

    public double getOutakeSpeed() {
        return outake.get();
    }

    @Override
    public void updatePeriodic() {
        // TODO: Put stuff here??
        //SmartDashboard
        SmartDashboard.putNumber("Cord Speed", getIntakeCordsSpeed());
    }

}