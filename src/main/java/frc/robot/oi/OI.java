package frc.robot.oi;

import com.torontocodingcollective.oi.TButton;
import com.torontocodingcollective.oi.TGameController;
import com.torontocodingcollective.oi.TGameController_Logitech;
import com.torontocodingcollective.oi.TOi;
import com.torontocodingcollective.oi.TRumbleManager;
import com.torontocodingcollective.oi.TStick;
import com.torontocodingcollective.oi.TStickPosition;
import com.torontocodingcollective.oi.TToggle;
import com.torontocodingcollective.oi.TTrigger;
import frc.robot.subsystems.CameraSubsystem.Camera;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

/**
 * Driver Controller (inherited from TOi)
 *
 * Sticks: Right Stick = Drive Stick Left Stick = Drive Stick Right Stick Press
 * = Toggle PIDs Left Stick Press = Toggle Compressor
 *
 * Buttons: Start Button = Reset Encoders and Gyro Back Button = Cancel any
 * Command
 *
 * Bumpers/Triggers: Left Bumper = Turbo shift
 *
 * POV: Any Angle = Rotate to the Pressed Angle
 *
 */
public class OI extends TOi {

    private TGameController driverController    = new TGameController_Logitech(0);
    private TRumbleManager  driverRumble        = new TRumbleManager("Driver", driverController);

    private TGameController operatorController  = new TGameController_Logitech(1);
    private TRumbleManager  operatorRumble      = new TRumbleManager("Operator", operatorController);

    private TToggle         speedPidToggle      = new TToggle(driverController, TStick.RIGHT);

    private DriveSelector   driveSelector       = new DriveSelector();

    private TToggle cameraToggle = new TToggle(operatorController, TButton.A);

    public Camera getCamera() {
    	if (cameraToggle.get()) {
    		return Camera.FRONT;
    	}
    	return Camera.REAR;
    }

    @Override
    public boolean getCancelCommand() {
        return driverController.getButton(TButton.BACK);
    }

    @Override
    public TStickPosition getDriveStickPosition(TStick stick) {
        return driverController.getStickPosition(stick);
    }

    public TStickPosition getDriverDriveStickPosition(TStick stick) {
        return driverController.getStickPosition(stick);
    }

    public TStickPosition getOperatorDriveStickPosition(TStick stick) {
        return operatorController.getStickPosition(stick);
    }

    public boolean isDriverActive() {
        return driverController.isStickActive(TStick.LEFT) || driverController.isStickActive(TStick.RIGHT);
    }

    @Override
    public boolean getReset() {
        return driverController.getButton(TButton.START);
    }

    @Override
    public int getRotateToHeading() {
        return driverController.getPOV();
    }

    /**
     * Get the selected drive type
     *
     * @return {@link DriveControlType} selected on the SmartDashboard. The default
     *         drive type is {@link DriveControlType#ARCADE}
     */
    public DriveControlType getSelectedDriveType() {
        return driveSelector.getDriveControlType();
    }

    /**
     * Get the selected single stick side
     *
     * @return {@link TStick} selected on the SmartDashboard. The default single
     *         stick drive is {@link TStick#RIGHT}
     */
    public TStick getSelectedSingleStickSide() {
        return driveSelector.getSingleStickSide();
    }

    @Override
    public boolean getSpeedPidEnabled() {
        return speedPidToggle.get();
    }

    /*public boolean getTurboOn() {
        return driverController.getButton(TButton.LEFT_BUMPER);
    }*/

    public void init() {
        //speedPidToggle.set(false);

        // True is front
        cameraToggle.set(true);
    }

    public void setSpeedPidEnabled(boolean state) {
        speedPidToggle.set(state);
    }

    public boolean getIntakeDeployUp() {
        return driverController.getButton(TButton.LEFT_BUMPER);
    }

    public boolean getIntakeDeployDown() {
        return driverController.getButton(TButton.RIGHT_BUMPER);
    }

    public boolean getIntakeBall() {
        return driverController.getButton(TTrigger.RIGHT);
    }

    public boolean getOutake() {
        return driverController.getButton(TTrigger.LEFT);
    }

    public boolean getBottomOutake() {
        return driverController.getButton(TButton.Y);
    }

    public boolean getHookUp() {
        return operatorController.getButton(TButton.RIGHT_BUMPER);
    }

    public boolean getHookDown() {
        return operatorController.getButton(TTrigger.RIGHT);
    }

    public boolean getWinchDown() {
        return operatorController.getButton(TTrigger.LEFT);
    }

    public boolean getWinchUp() {
        return operatorController.getButton(TButton.LEFT_BUMPER);
    }

    public boolean getCPSpinTimes() {
        return operatorController.getButton(TButton.Y);
    }

    public boolean getCPColorSpin() {
        return operatorController.getButton(TButton.B);
    }

    public boolean getCPManualSpin() {
        return operatorController.getButton(TButton.X);
    }

    @Override
    public void updatePeriodic() {

        // Update all Toggles
        speedPidToggle.updatePeriodic();
        driverRumble.updatePeriodic();
        operatorRumble.updatePeriodic();
        cameraToggle.updatePeriodic();

        // Update all SmartDashboard values
        SmartDashboard.putBoolean("Speed PID Toggle", getSpeedPidEnabled());
        SmartDashboard.putString("Driver Controller", driverController.toString());
    }
}
