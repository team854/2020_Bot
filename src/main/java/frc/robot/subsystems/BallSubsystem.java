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

    public enum OutakeState {TOP_OUTAKE, BOTTOM_OUTAKE, NO_OUTAKE};

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

    public void setIntakeState(boolean state) {
        // True means start intaking, False means stop

        if (state) {
            setOutakeState(OutakeState.NO_OUTAKE);  // Can't intake and outake at the same time
            setOutakeSpeed(0.33);  // Outake motors spin backwards while intaking
            setIntakeWheelsSpeed(-1);
            setIntakeCordsSpeed(0.33);
            //setIntakeDeploySpeed(1);  // Push arm down when intaking
        } else {
            setIntakeWheelsSpeed(0);
            setIntakeCordsSpeed(0);
            setOutakeSpeed(0);
            setIntakeDeploySpeed(0);
        }
    }

    public void setOutakeState(OutakeState state) {
        if (state == OutakeState.TOP_OUTAKE) {
            setIntakeState(false);  // Can't intake and outake at the same time
            setOutakeSpeed(-1);
            setIntakeCordsSpeed(1);
        } else if (state == OutakeState.BOTTOM_OUTAKE) {
            setIntakeState(false);
            setOutakeSpeed(0);
            setIntakeCordsSpeed(-0.90);
            setIntakeWheelsSpeed(1);
        } else if (state == OutakeState.NO_OUTAKE) {
            setOutakeSpeed(0);
            setIntakeCordsSpeed(0);
        }
    }

    @Override
    public void updatePeriodic() {
        // TODO: Put stuff here??
        SmartDashboard.putNumber("Cord Speed", getIntakeCordsSpeed());
    }

}