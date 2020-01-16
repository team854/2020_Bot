package frc.robot.subsystems;

import com.torontocodingcollective.speedcontroller.TPwmSpeedController;
import com.torontocodingcollective.speedcontroller.TPwmSpeedController.TPwmSpeedControllerType;
import com.torontocodingcollective.subsystem.TSubsystem;
import frc.robot.commands.ball.DefaultBallCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BallSubsystem extends TSubsystem {

    // All the speed controllers are fake, and assumed to be using PWM
    // The real values should be put in RobotMap.java afterward

    private TPwmSpeedController intakeWheels =  new TPwmSpeedController(TPwmSpeedControllerType.SPARK, 0, false);
    private TPwmSpeedController intakeDeploy =  new TPwmSpeedController(TPwmSpeedControllerType.SPARK, 0, false);
    private TPwmSpeedController intakeCords  =  new TPwmSpeedController(TPwmSpeedControllerType.SPARK, 0, false);
    private TPwmSpeedController outake =        new TPwmSpeedController(TPwmSpeedControllerType.SPARK, 0, false);

    @Override
    public void init() {}

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultBallCommand());
    }

    /*public void setIntakeState(boolean state) {
        // True means start intaking, False means stop
        // TODO: All motor speeds are dummy values - they will need to be adjusted
        //       Some should be made negative, for some 1 is too fast

        if (state) {
            setOutakeSpeed(-1);  // Outake motors spin backwards while intaking
            setIntakeDeploySpeed(1);
            setIntakeWheelsSpeed(1);
            setIntakeCordsSpeed(1);
        } else {
            setIntakeDeploySpeed(-1);
            setIntakeWheelsSpeed(0);
            setIntakeCordsSpeed(0);
        }
    }*/

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