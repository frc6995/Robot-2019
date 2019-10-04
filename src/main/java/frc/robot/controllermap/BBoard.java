package frc.robot.controllermap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class BBoard {
    private static Joystick joystick;
    private static JoystickButton right_top;
    private static JoystickButton right_index;
    private static JoystickButton right_middle;
    private static JoystickButton right_ring;
    private static JoystickButton right_bottom;

    private static JoystickButton left_top;
    private static JoystickButton left_index;
    private static JoystickButton left_middle;
    private static JoystickButton left_ring;
    private static JoystickButton left_bottom;

    private final static int BUTTON_1 = 1;
    private final static int BUTTON_2 = 3;
    private final static int BUTTON_3 = 4;
    private final static int BUTTON_4 = 10;
    private final static int BUTTON_8 = 8;
    private final static int BUTTON_9 = 2;
    private final static int BUTTON_10 = 9;
    private final static int BUTTON_11 = 11;
    private final static int BUTTON_12 = 12;
    private final static int BUTTON_13 = 13;

    public BBoard(int port) {
        joystick = new Joystick(port);
        right_ring = new JoystickButton(joystick, BUTTON_1);
        right_middle = new JoystickButton(joystick, BUTTON_2);
        right_bottom = new JoystickButton(joystick, BUTTON_3);
        left_bottom = new JoystickButton(joystick, BUTTON_4);
        right_index = new JoystickButton(joystick, BUTTON_8);
        left_index = new JoystickButton(joystick, BUTTON_9);
        right_top = new JoystickButton(joystick, BUTTON_10);
        left_top = new JoystickButton(joystick, BUTTON_11);
        left_middle = new JoystickButton(joystick, BUTTON_12);
        left_ring = new JoystickButton(joystick, BUTTON_13);
    }

    public boolean right_top() {
        return right_top.get();
    }
    public void right_top_runOnPress(Command command) {
        right_top.whenPressed(command);
    }
    public void right_top_runOnRelease(Command command) {
        right_top.whenReleased(command);
    }
    public void right_top_toggleOnPress(Command command) {
        right_top.toggleWhenPressed(command);
    }
    public void right_top_cancelOnPress(Command command) {
        right_top.cancelWhenPressed(command);
    }
    public void right_top_runWhileHeld(Command command) {
        right_top.whileHeld(command);
    }

    public boolean right_index() {
        return right_index.get();
    }
    public void right_index_runOnPress(Command command) {
        right_index.whenPressed(command);
    }
    public void right_index_runOnRelease(Command command) {
        right_index.whenReleased(command);
    }
    public void right_index_toggleOnPress(Command command) {
        right_index.toggleWhenPressed(command);
    }
    public void right_index_cancelOnPress(Command command) {
        right_index.cancelWhenPressed(command);
    }
    public void right_index_runWhileHeld(Command command) {
        right_index.whileHeld(command);
    }

    public boolean right_middle() {
        return right_middle.get();
    }
    public void right_middle_runOnPress(Command command) {
        right_middle.whenPressed(command);
    }
    public void right_middle_runOnRelease(Command command) {
        right_middle.whenReleased(command);
    }
    public void right_middle_toggleOnPress(Command command) {
        right_middle.toggleWhenPressed(command);
    }
    public void right_middle_cancelOnPress(Command command) {
        right_middle.cancelWhenPressed(command);
    }
    public void right_middle_runWhileHeld(Command command) {
        right_middle.whileHeld(command);
    }

    public boolean right_ring() {
        return right_ring.get();
    }
    public void right_ring_runOnPress(Command command) {
        right_ring.whenPressed(command);
    }
    public void right_ring_runOnRelease(Command command) {
        right_ring.whenReleased(command);
    }
    public void right_ring_toggleOnPress(Command command) {
        right_ring.toggleWhenPressed(command);
    }
    public void right_ring_cancelOnPress(Command command) {
        right_ring.cancelWhenPressed(command);
    }
    public void right_ring_runWhileHeld(Command command) {
        right_ring.whileHeld(command);
    }

    public boolean right_bottom() {
        return right_bottom.get();
    }
    public void right_bottom_runOnPress(Command command) {
        right_bottom.whenPressed(command);
    }
    public void right_bottom_runOnRelease(Command command) {
        right_bottom.whenReleased(command);
    }
    public void right_bottom_toggleOnPress(Command command) {
        right_bottom.toggleWhenPressed(command);
    }
    public void right_bottom_cancelOnPress(Command command) {
        right_bottom.cancelWhenPressed(command);
    }
    public void right_bottom_runWhileHeld(Command command) {
        right_bottom.whileHeld(command);
    }

    public boolean left_top() {
        return left_top.get();
    }
    public void left_top_runOnPress(Command command) {
        left_top.whenPressed(command);
    }
    public void left_top_runOnRelease(Command command) {
        left_top.whenReleased(command);
    }
    public void left_top_toggleOnPress(Command command) {
        left_top.toggleWhenPressed(command);
    }
    public void left_top_cancelOnPress(Command command) {
        left_top.cancelWhenPressed(command);
    }
    public void left_top_runWhileHeld(Command command) {
        left_top.whileHeld(command);
    }

    public boolean left_index() {
        return left_index.get();
    }
    public void left_index_runOnPress(Command command) {
        left_index.whenPressed(command);
    }
    public void left_index_runOnRelease(Command command) {
        left_index.whenReleased(command);
    }
    public void left_index_toggleOnPress(Command command) {
        left_index.toggleWhenPressed(command);
    }
    public void left_index_cancelOnPress(Command command) {
        left_index.cancelWhenPressed(command);
    }
    public void left_index_runWhileHeld(Command command) {
        left_index.whileHeld(command);
    }

    public boolean left_middle() {
        return left_middle.get();
    }
    public void left_middle_runOnPress(Command command) {
        left_middle.whenPressed(command);
    }
    public void left_middle_runOnRelease(Command command) {
        left_middle.whenReleased(command);
    }
    public void left_middle_toggleOnPress(Command command) {
        left_middle.toggleWhenPressed(command);
    }
    public void left_middle_cancelOnPress(Command command) {
        left_middle.cancelWhenPressed(command);
    }
    public void left_middle_runWhileHeld(Command command) {
        left_middle.whileHeld(command);
    }

    public boolean left_ring() {
        return left_ring.get();
    }
    public void left_ring_runOnPress(Command command) {
        left_ring.whenPressed(command);
    }
    public void left_ring_runOnRelease(Command command) {
        left_ring.whenReleased(command);
    }
    public void left_ring_toggleOnPress(Command command) {
        left_ring.toggleWhenPressed(command);
    }
    public void left_ring_cancelOnPress(Command command) {
        left_ring.cancelWhenPressed(command);
    }
    public void left_ring_runWhileHeld(Command command) {
        left_ring.whileHeld(command);
    }

    public boolean left_bottom() {
        return left_bottom.get();
    }
    public void left_bottom_runOnPress(Command command) {
        left_bottom.whenPressed(command);
    }
    public void left_bottom_runOnRelease(Command command) {
        left_bottom.whenReleased(command);
    }
    public void left_bottom_toggleOnPress(Command command) {
        left_bottom.toggleWhenPressed(command);
    }
    public void left_bottom_cancelOnPress(Command command) {
        left_bottom.cancelWhenPressed(command);
    }
    public void left_bottom_runWhileHeld(Command command) {
        left_bottom.whileHeld(command);
    }
}

