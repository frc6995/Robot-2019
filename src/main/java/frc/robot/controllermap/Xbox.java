package frc.robot.controllermap;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * The Xbox class takes the 3 different control implimentations that the xbox
 * requires to use all of its functions and combines it into one easy to use
 * class. This class combines: {@link edu.wpi.first.wpilibj.XboxController},
 * {@link edu.wpi.first.wpilibj.buttons.JoystickButton}, and
 * {@link edu.wpi.first.wpilibj.buttons.POVButton}
 * 
 * @author Elijah Sauder
 */

public class Xbox {
    private XboxController xbox;
    private JoystickButton a;
    private JoystickButton b;
    private JoystickButton x;
    private JoystickButton y;
    private JoystickButton left_stick;
    private JoystickButton right_stick;
    private JoystickButton left_bumper;
    private JoystickButton right_bumper;
    private JoystickButton start;
    private JoystickButton back;

    private POVButton dpad_center;
    private POVButton dpad_up;
    private POVButton dpad_down;
    private POVButton dpad_left;
    private POVButton dpad_right;
    private POVButton dpad_up_left;
    private POVButton dpad_up_right;
    private POVButton dpad_down_left;
    private POVButton dpad_down_right;

    private enum POV {
        UP, DOWN, LEFT, RIGHT, UP_RIGHT, UP_LEFT, DOWN_LEFT, DOWN_RIGHT, CENTER;
    }

    private int DPAD(POV pov) {
        switch (pov) {
        case UP:
            return 0;
        case UP_RIGHT:
            return 45;
        case RIGHT:
            return 90;
        case DOWN_RIGHT:
            return 135;
        case DOWN:
            return 180;
        case DOWN_LEFT:
            return 225;
        case LEFT:
            return 270;
        case UP_LEFT:
            return 315;
        case CENTER:
            return -1;
        default:
            return -1;
        }
    }

    // BUTTONS
    private final int BUTTON_A = 1;
    private final int BUTTON_B = 2;
    private final int BUTTON_X = 3;
    private final int BUTTON_Y = 4;
    private final int BUTTON_LEFT_BUMPER = 5;
    private final int BUTTON_RIGHT_BUMPER = 6;
    private final int BUTTON_START = 7;
    private final int BUTTON_BACK = 8;
    private final int BUTTON_LEFT_STICK = 9;
    private final int BUTTON_RIGHT_STICK = 10;

    // AXIS
    private final int AXIS_LEFT_STICK_X = 0;
    private final int AXIS_LEFT_STICK_Y = 1;
    private final int AXIS_RIGHT_STICK_X = 4;
    private final int AXIS_RIGHT_STICK_Y = 5;
    private final int AXIS_LEFT_TRIGGER = 2;
    private final int AXIS_RIGHT_TRIGGER = 3;

    // DPAD
    private final int DPAD_UP = DPAD(POV.UP);
    private final int DPAD_LEFT = DPAD(POV.LEFT);
    private final int DPAD_DOWN = DPAD(POV.DOWN);
    private final int DPAD_RIGHT = DPAD(POV.RIGHT);
    private final int DPAD_UP_RIGHT = DPAD(POV.UP_RIGHT);
    private final int DPAD_UP_LEFT = DPAD(POV.UP_LEFT);
    private final int DPAD_DOWN_RIGHT = DPAD(POV.DOWN_RIGHT);
    private final int DPAD_DOWN_LEFT = DPAD(POV.DOWN_RIGHT);
    private final int DPAD_CENTER = DPAD(POV.CENTER);

    /**
     * Construct instance of the Xbox class.
     * 
     * @param port : the port on the driver station that the Xbox is plugged into.
     */
    public Xbox(int port) {
        xbox = new XboxController(port);
        a = new JoystickButton(xbox, BUTTON_A);
        b = new JoystickButton(xbox, BUTTON_B);
        x = new JoystickButton(xbox, BUTTON_X);
        y = new JoystickButton(xbox, BUTTON_Y);
        left_stick = new JoystickButton(xbox, BUTTON_LEFT_STICK);
        right_stick = new JoystickButton(xbox, BUTTON_RIGHT_STICK);
        left_bumper = new JoystickButton(xbox, BUTTON_LEFT_BUMPER);
        right_bumper = new JoystickButton(xbox, BUTTON_RIGHT_BUMPER);
        start = new JoystickButton(xbox, BUTTON_START);
        back = new JoystickButton(xbox, BUTTON_BACK);
        dpad_up = new POVButton(xbox, DPAD_UP);
        dpad_down = new POVButton(xbox, DPAD_DOWN);
        dpad_left = new POVButton(xbox, DPAD_LEFT);
        dpad_right = new POVButton(xbox, DPAD_RIGHT);
        dpad_up_left = new POVButton(xbox, DPAD_UP_LEFT);
        dpad_up_right = new POVButton(xbox, DPAD_UP_RIGHT);
        dpad_down_left = new POVButton(xbox, DPAD_DOWN_LEFT);
        dpad_down_right = new POVButton(xbox, DPAD_DOWN_RIGHT);
        dpad_center = new POVButton(xbox, DPAD_CENTER);
    }

    public void setRumble(double strength) {
        xbox.setRumble(RumbleType.kRightRumble, strength);
    }

    /**
     * AXIS
     */
    /**
     * Returns the X axis value from the Xbox's left stick.
     * 
     * @return double
     */
    public double left_stick_x() {
        return xbox.getRawAxis(AXIS_LEFT_STICK_X);
    }

    /**
     * Returns the Y axis value from the Xbox's left stick.
     * 
     * @return double
     */
    public double left_stick_y() {
        return xbox.getRawAxis(AXIS_LEFT_STICK_Y);
    }

    /**
     * Returns the X axis value from the Xbox's right stick.
     * 
     * @return double
     */
    public double right_stick_x() {
        return xbox.getRawAxis(AXIS_RIGHT_STICK_X);
    }

    /**
     * Returns the Y axis value from the Xbox's right stick.
     * 
     * @return double
     */
    public double right_stick_y() {
        return xbox.getRawAxis(AXIS_RIGHT_STICK_Y);
    }

    /**
     * Returns the value from the Xbox's right trigger.
     * 
     * @return double
     */
    public double right_trigger() {
        return xbox.getRawAxis(AXIS_RIGHT_TRIGGER);
    }

    /**
     * Returns the value from the Xbox's left trigger.
     * 
     * @return double
     */
    public double left_trigger() {
        return xbox.getRawAxis(AXIS_LEFT_TRIGGER);
    }

    /**
     * BUTTONS
     */
    /**
     * Returns the value of the Xbox A button
     * 
     * @return boolean
     */
    public boolean a() {
        return xbox.getAButton();
    }

    /**
     * Runs a command when the Xbox A button is pressed.
     * 
     * @param
     */
    public void a_runOnPressed(Command command) {
        a.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox A button is released.
     * 
     * @param
     */
    public void a_runOnRelease(Command command) {
        a.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox A button is pressed.
     * 
     * @param
     */
    public void a_toggleOnPress(Command command) {
        a.toggleWhenPressed(command);
    }

    /**
     * Stops a command when the Xbox A button is pressed.
     * 
     * @param
     */
    public void a_cancelOnPress(Command command) {
        a.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox A button is pressed.
     * 
     * @param
     */
    public void a_runWhileHeld(Command command) {
        a.whileHeld(command);
    }

    /**
     * Returns the value of the Xbox B button
     * 
     * @return boolean
     */
    public boolean b() {
        return xbox.getBButton();
    }

    /**
     * Runs a command when the Xbox B button is pressed.
     * 
     * @param
     */
    public void b_runOnPressed(Command command) {
        b.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox B button is released.
     * 
     * @param
     */
    public void b_runOnRelease(Command command) {
        b.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox B button is pressed.
     * 
     * @param
     */
    public void b_toggleOnPress(Command command) {
        b.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox B button is pressed.
     * 
     * @param
     */
    public void b_cancelOnPress(Command command) {
        b.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox B button is pressed.
     * 
     * @param
     */
    public void b_runWhileHeld(Command command) {
        b.whileHeld(command);
    }

    /**
     * Returns the value of the Xbox X button
     * 
     * @return boolean
     */
    public boolean x() {
        return xbox.getXButton();
    }

    /**
     * Runs a command when the Xbox X button is pressed.
     * 
     * @param
     */
    public void x_runOnPressed(Command command) {
        x.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox X button is released.
     * 
     * @param
     */
    public void x_runOnRelease(Command command) {
        x.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox X button is pressed.
     * 
     * @param
     */
    public void x_toggleOnPress(Command command) {
        x.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox X button is pressed.
     * 
     * @param
     */
    public void x_cancelOnPress(Command command) {
        x.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox X button is pressed.
     * 
     * @param
     */
    public void x_runWhileHeld(Command command) {
        x.whileHeld(command);
    }

    /**
     * Returns the value of the Xbox Y button.
     * 
     * @return boolean
     */
    public boolean y() {
        return xbox.getYButton();
    }

    /**
     * Runs a command when the Xbox Y button is pressed.
     * 
     * @param
     */
    public void y_runOnPressed(Command command) {
        y.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox Y button is released.
     * 
     * @param
     */
    public void y_runOnRelease(Command command) {
        y.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox Y button is pressed.
     * 
     * @param
     */
    public void y_toggleOnPress(Command command) {
        y.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox Y button is pressed.
     * 
     * @param
     */
    public void y_cancelOnPress(Command command) {
        y.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox Y button is pressed.
     * 
     * @param
     */
    public void y_runWhileHeld(Command command) {
        y.whileHeld(command);
    }

    /**
     * Returns the value of the Xbox Back button.
     * 
     * @return boolean
     */
    public boolean back() {
        return xbox.getBackButton();
    }

    /**
     * Runs a command when the Xbox Back button is pressed.
     * 
     * @param
     */
    public void back_runOnPressed(Command command) {
        back.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox Back button is released.
     * 
     * @param
     */
    public void back_runOnRelease(Command command) {
        back.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox Back button is pressed.
     * 
     * @param
     */
    public void back_toggleOnPress(Command command) {
        back.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox Back button is pressed.
     * 
     * @param
     */
    public void back_cancelOnPress(Command command) {
        back.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox Back button is pressed.
     * 
     * @param
     */
    public void back_runWhileHeld(Command command) {
        back.whileHeld(command);
    }

    /**
     * Returns the value of the start button
     * 
     * @return boolean
     */
    public boolean start() {
        return xbox.getStartButton();
    }

    /**
     * Runs a command when the Xbox Start button is pressed.
     * 
     * @param
     */
    public void start_runOnPressed(Command command) {
        start.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox Start button is released.
     * 
     * @param
     */
    public void start_runOnRelease(Command command) {
        start.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox Start button is pressed.
     * 
     * @param
     */
    public void start_toggleOnPress(Command command) {
        start.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox Start button is pressed.
     * 
     * @param
     */
    public void start_cancelOnPress(Command command) {
        start.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox Start button is pressed.
     * 
     * @param
     */
    public void start_runWhileHeld(Command command) {
        start.whileHeld(command);
    }

    /**
     * Returns the value of the Xbox Left Stick button.
     * 
     * @return boolean
     */
    public boolean left_stick() {
        return xbox.getStickButton(Hand.kLeft);
    }

    /**
     * Runs a command when the Xbox Left Stick button is pressed.
     * 
     * @param
     */
    public void left_stick_runOnPressed(Command command) {
        left_stick.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox Left Stick button is released.
     * 
     * @param
     */
    public void left_stick_runOnRelease(Command command) {
        left_stick.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox Left Stick button is pressed.
     * 
     * @param
     */
    public void left_stick_toggleOnPress(Command command) {
        left_stick.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox Left Stick button is pressed.
     * 
     * @param
     */
    public void left_stick_cancelOnPress(Command command) {
        left_stick.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox Left Stick button is pressed.
     * 
     * @param
     */
    public void left_stick_runWhileHeld(Command command) {
        left_stick.whileHeld(command);
    }

    /**
     * Returns the value of the Xbox Right Stick button.
     * 
     * @return boolean
     */
    public boolean right_stick() {
        return xbox.getStickButton(Hand.kRight);
    }

    /**
     * Runs a command when the Xbox Right Stick button is pressed.
     * 
     * @param
     */
    public void right_stick_runOnPressed(Command command) {
        right_stick.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox Right Stick button is released.
     * 
     * @param
     */
    public void right_stick_runOnRelease(Command command) {
        right_stick.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox Right Stick button is pressed.
     * 
     * @param
     */
    public void right_stick_toggleOnPress(Command command) {
        right_stick.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox Right Stick button is pressed.
     * 
     * @param
     */
    public void right_stick_cancelOnPress(Command command) {
        right_stick.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox Right Stick button is pressed.
     * 
     * @param
     */
    public void right_stick_runWhileHeld(Command command) {
        right_stick.whileHeld(command);
    }

    /**
     * Returns the value of the Xbox Left Bumper button.
     * 
     * @return boolean
     */
    public boolean left_bumper() {
        return xbox.getBumper(Hand.kLeft);
    }

    public boolean left_bumper_pressed() {
        return xbox.getBumperPressed(Hand.kLeft);
    }

    /**
     * Runs a command when the Xbox Left Bumper button is pressed.
     * 
     * @param
     */
    public void left_bumper_runOnPressed(Command command) {
        left_bumper.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox Left Bumper button is released.
     * 
     * @param
     */
    public void left_bumper_runOnRelease(Command command) {
        left_bumper.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox Left Bumper button is pressed.
     * 
     * @param
     */
    public void left_bumper_toggleOnPress(Command command) {
        left_bumper.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox Left Bumper button is pressed.
     * 
     * @param
     */
    public void left_bumper_cancelOnPress(Command command) {
        left_bumper.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox Left Bumper button is pressed.
     * 
     * @param
     */
    public void left_bumper_runWhileHeld(Command command) {
        left_bumper.whileHeld(command);
    }

    /**
     * Returns the value of the Xbox Right Bumper button.
     * 
     * @return boolean
     */
    public boolean right_bumper() {
        return xbox.getBumper(Hand.kRight);
    }

    /**
     * Runs a command when the Xbox Right Bumper button is pressed.
     * 
     * @param
     */
    public void right_bumper_runOnPressed(Command command) {
        right_bumper.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox Right Bumper button is released.
     * 
     * @param
     */
    public void right_bumper_runOnRelease(Command command) {
        right_bumper.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox Right Bumper button is pressed.
     * 
     * @param
     */
    public void right_bumper_toggleOnPress(Command command) {
        right_bumper.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox Right Bumper button is pressed.
     * 
     * @param
     */
    public void right_bumper_cancelOnPress(Command command) {
        right_bumper.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox Right Bumper button is pressed.
     * 
     * @param
     */
    public void right_bumper_runWhileHeld(Command command) {
        right_bumper.whileHeld(command);
    }

    /**
     * POV
     */
    /**
     * Returns whether or not the Xbox POV is in the up position.
     * 
     * @return boolean
     */
    public boolean dpad_up() {
        return dpad_up.get();
    }

    /**
     * Runs a command when the Xbox POV is in the up position.
     * 
     * @param
     */
    public void dpad_up_runOnPressed(Command command) {
        dpad_up.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox Dpad POV is no longer in the up position.
     * 
     * @param
     */
    public void dpad_up_runOnRelease(Command command) {
        dpad_up.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox POV is in the up position.
     * 
     * @param
     */
    public void dpad_up_toggleOnPress(Command command) {
        dpad_up.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox POV is in the up position.
     * 
     * @param
     */
    public void dpad_up_cancelOnPress(Command command) {
        dpad_up.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox POV is in the up position.
     * 
     * @param
     */
    public void dpad_up_runWhileHeld(Command command) {
        dpad_up.whileHeld(command);
    }

    /**
     * Returns whether or not the xbox POV is in the down position.
     * 
     * @return boolean
     */
    public boolean dpad_down() {
        return dpad_down.get();
    }

    /**
     * Runs a command when the Xbox POV is in the down position.
     * 
     * @param
     */
    public void dpad_down_runOnPressed(Command command) {
        dpad_down.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox POV is no longer in the down position.
     * 
     * @param
     */
    public void dpad_down_runOnRelease(Command command) {
        dpad_down.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox POV is in the down position.
     * 
     * @param
     */
    public void dpad_down_toggleOnPress(Command command) {
        dpad_down.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox POV is in the down position.
     * 
     * @param
     */
    public void dpad_down_cancelOnPress(Command command) {
        dpad_down.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox POV is in the down position.
     * 
     * @param
     */
    public void dpad_down_runWhileHeld(Command command) {
        dpad_down.whileHeld(command);
    }

    /**
     * Returns whether or not the Xbox POV is in the left position.
     * 
     * @return boolean
     */
    public boolean dpad_left() {
        return dpad_left.get();
    }

    /**
     * Runs a command when the Xbox POV is in the left position.
     * 
     * @param
     */
    public void dpad_left_runOnPressed(Command command) {
        dpad_left.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox POV is no longer in the left position.
     * 
     * @param
     */
    public void dpad_left_runOnRelease(Command command) {
        dpad_left.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox POV is in the left position.
     * 
     * @param
     */
    public void dpad_left_toggleOnPress(Command command) {
        dpad_left.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox POV is in the left position.
     * 
     * @param
     */
    public void dpad_left_cancelOnPress(Command command) {
        dpad_left.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox POV is in the left position.
     * 
     * @param
     */
    public void dpad_left_runWhileHeld(Command command) {
        dpad_left.whileHeld(command);
    }

    /**
     * Returns whether or not the Xbox POV is in the right position.
     * 
     * @return boolean
     */
    public boolean dpad_right() {
        return dpad_right.get();
    }

    /**
     * Runs a command when the Xbox POV is in the right position.
     * 
     * @param
     */
    public void dpad_right_runOnPressed(Command command) {
        dpad_right.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox POV is no longer in the right position.
     * 
     * @param
     */
    public void dpad_right_runOnRelease(Command command) {
        dpad_right.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox POV is in the right position.
     * 
     * @param
     */
    public void dpad_right_toggleOnPress(Command command) {
        dpad_right.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox POV is in the right position.
     * 
     * @param
     */
    public void dpad_right_cancelOnPress(Command command) {
        dpad_right.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox POV is in the right position.
     * 
     * @param
     */
    public void dpad_right_runWhileHeld(Command command) {
        dpad_right.whileHeld(command);
    }

    /**
     * Returns whether or not the Xbox POV is in the up light position.
     * 
     * @return boolean
     */
    public boolean dpad_up_right() {
        return dpad_up_right.get();
    }

    /**
     * Runs a command when the Xbox POV is in the up right position.
     * 
     * @param
     */
    public void dpad_up_right_runOnPressed(Command command) {
        dpad_up_right.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox POV is no longer in the up right position.
     * 
     * @param
     */
    public void dpad_up_right_runOnRelease(Command command) {
        dpad_up_right.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox POV is in the up right position.
     * 
     * @param
     */
    public void dpad_up_right_toggleOnPress(Command command) {
        dpad_up_right.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox POV is in the up right position.
     * 
     * @param
     */
    public void dpad_up_right_cancelOnPress(Command command) {
        dpad_up_right.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox POV is in the up right position.
     * 
     * @param
     */
    public void dpad_up_right_runWhileHeld(Command command) {
        dpad_up_right.whileHeld(command);
    }

    /**
     * Returns whether or not the Xbox POV is in the up left position.
     * 
     * @return boolean
     */
    public boolean dpad_up_left() {
        return dpad_up_left.get();
    }

    /**
     * Runs a command when the Xbox POV is in the up left position.
     * 
     * @param
     */
    public void dpad_up_left_runOnPressed(Command command) {
        dpad_up_left.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox POV is no longer in the up left position.
     * 
     * @param
     */
    public void dpad_up_left_runOnRelease(Command command) {
        dpad_up_left.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox POV is in the up left position.
     * 
     * @param
     */
    public void dpad_up_left_toggleOnPress(Command command) {
        dpad_up_left.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox POV is in the up left position.
     * 
     * @param
     */
    public void dpad_up_left_cancelOnPress(Command command) {
        dpad_up_left.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox POV is in the up left position.
     * 
     * @param
     */
    public void dpad_up_left_runWhileHeld(Command command) {
        dpad_up_left.whileHeld(command);
    }

    /**
     * Returns whether or not the Xbox POV is in the down right position.
     * 
     * @return boolean
     */
    public boolean dpad_down_right() {
        return dpad_down_right.get();
    }

    /**
     * Runs a command when the Xbox POV is in the down right position.
     * 
     * @param
     */
    public void dpad_down_right_runOnPressed(Command command) {
        dpad_down_right.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox POV is no longer in the down right position.
     * 
     * @param
     */
    public void dpad_down_right_runOnRelease(Command command) {
        dpad_down_right.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox POV is in the down right position.
     * 
     * @param
     */
    public void dpad_down_right_toggleOnPress(Command command) {
        dpad_down_right.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox POV is in the down right position.
     * 
     * @param
     */
    public void dpad_down_right_cancelOnPress(Command command) {
        dpad_down_right.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox POV is in the down right position.
     * 
     * @param
     */
    public void dpad_down_right_runWhileHeld(Command command) {
        dpad_down_right.whileHeld(command);
    }

    /**
     * Returns whether or not the Xbox POV is in the down left position.
     * 
     * @return boolean
     */
    public boolean dpad_down_left() {
        return dpad_down_left.get();
    }

    /**
     * Runs a command when the Xbox POV is in the down left position.
     * 
     * @param
     */
    public void dpad_down_left_runOnPressed(Command command) {
        dpad_down_left.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox POV is no longer in the down left position.
     * 
     * @param
     */
    public void dpad_down_left_runOnRelease(Command command) {
        dpad_down_left.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox POV is in the down left position.
     * 
     * @param
     */
    public void dpad_down_left_toggleOnPress(Command command) {
        dpad_down_left.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox POV is in the down left position.
     * 
     * @param
     */
    public void dpad_down_left_cancelOnPress(Command command) {
        dpad_down_left.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox POV is in the down left position.
     * 
     * @param
     */
    public void dpad_down_left_runWhileHeld(Command command) {
        dpad_down_left.whileHeld(command);
    }

    /**
     * Returns whether or not the Xbox POV is in the center position.
     * 
     * @return boolean
     */
    public boolean dpad_center() {
        return dpad_center.get();
    }

    /**
     * Runs a command when the Xbox POV is in the center position.
     * 
     * @param
     */
    public void dpad_center_runOnPressed(Command command) {
        dpad_center.whenPressed(command);
    }

    /**
     * Runs a command when the Xbox POV is no longer in the center position.
     * 
     * @param
     */
    public void dpad_center_runOnRelease(Command command) {
        dpad_center.whenReleased(command);
    }

    /**
     * Toggles a command when the Xbox POV is in the center position.
     * 
     * @param
     */
    public void dpad_center_toggleOnPress(Command command) {
        dpad_center.toggleWhenPressed(command);
    }

    /**
     * Cancels a command when the Xbox POV is in the center position.
     * 
     * @param
     */
    public void dpad_center_cancelOnPress(Command command) {
        dpad_center.cancelWhenPressed(command);
    }

    /**
     * Runs a command while the Xbox POV is in the center position.
     * 
     * @param
     */
    public void dpad_center_runWhileHeld(Command command) {
        dpad_center.whileHeld(command);
    }
}
