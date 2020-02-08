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

    private TToggle         compressorToggle    = new TToggle(driverController, TStick.LEFT);
    private TToggle         speedPidToggle      = new TToggle(driverController, TStick.RIGHT);

    private DriveSelector   driveSelector       = new DriveSelector();

    // BallCommand stuff
    private TToggle         intakeDeploy        = new TToggle(operatorController, TButton.RIGHT_BUMPER);

    // Overrides
    private boolean outakeOverrided     = false;
    private boolean outakeOverrideState = false;
    private boolean intakeBallOverrided     = false;
    private boolean intakeBallOverrideState = false;

    @Override
    public boolean getCancelCommand() {
        return driverController.getButton(TButton.BACK);
    }

    public boolean getCompressorEnabled() {
        return compressorToggle.get();
    }

    @Override
    public TStickPosition getDriveStickPosition(TStick stick) {
        return driverController.getStickPosition(stick);
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

    public boolean getTurboOn() {
        return driverController.getButton(TButton.LEFT_BUMPER);
    }

    public void init() {
        compressorToggle.set(true);
        speedPidToggle.set(false);
    }

    public void setSpeedPidEnabled(boolean state) {
        speedPidToggle.set(state);
    }

    public boolean getIntakeDeploy() {
        // True is deployed, or should be
        return intakeDeploy.get();
    }

    public void setIntakeDeploy(boolean state) {
        intakeDeploy.set(state);
    }

    public boolean getIntakeBall() {
        // True means intaking
        if (intakeBallOverrided) {
            return intakeBallOverrideState;
        }
        return operatorController.getButton(TTrigger.RIGHT);
    }

    public void overrideIntakeBall(boolean state) {
        intakeBallOverrided = true;
        intakeBallOverrideState = state;
    }

    public void unOverrideIntakeBall() {
        intakeBallOverrided = false;
    }

    public boolean getOutake() {
        // True means outake
        if (outakeOverrided) {
            return outakeOverrideState;
        }
        return operatorController.getButton(TTrigger.LEFT);
    }

    public void overrideOutake(boolean state) {
        outakeOverrided = true;
        outakeOverrideState = state;
    }

    public void unOverrideOutake() {
        outakeOverrided = false;
    }

    public boolean getHookUp() {
        return driverController.getButton(TTrigger.RIGHT);
    }

    public boolean getHookDown() {
        return driverController.getButton(TButton.RIGHT_BUMPER);
    }

    public boolean getWinchDown() {
        return driverController.getButton(TTrigger.LEFT);
    }

    public boolean getCPSpinTimes() {
        return operatorController.getButton(TButton.Y);
    }

    public boolean getCPColorSpin() {
        return operatorController.getButton(TButton.B);
    }

    public boolean getCanDriveTest() {
        return driverController.getButton(TButton.X) || operatorController.getButton(TButton.X);
    }

    @Override
    public void updatePeriodic() {

        // Update all Toggles
        compressorToggle.updatePeriodic();
        speedPidToggle.updatePeriodic();
        driverRumble.updatePeriodic();
        operatorRumble.updatePeriodic();
        intakeDeploy.updatePeriodic();

        // Update all SmartDashboard values
        SmartDashboard.putBoolean("Speed PID Toggle", getSpeedPidEnabled());
        SmartDashboard.putBoolean("Compressor Toggle", getCompressorEnabled());
        SmartDashboard.putString("Driver Controller", driverController.toString());
    }
}
