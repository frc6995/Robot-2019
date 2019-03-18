package frc.robot.controllermap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class BBoard {
    private static Joystick joystick;
    private static JoystickButton left_thumb;
    private static JoystickButton left_index;
    private static JoystickButton left_middle;
    private static JoystickButton left_ring;
    private static JoystickButton left_pinky;

    private static JoystickButton right_thumb;
    private static JoystickButton right_index;
    private static JoystickButton right_middle;
    private static JoystickButton right_ring;
    private static JoystickButton right_pinky;

    private final static int BUTTON_1 = 1;
    private final static int BUTTON_2 = 2;
    private final static int BUTTON_3 = 3;
    private final static int BUTTON_4 = 4;
    private final static int BUTTON_5 = 5;

    private final static int BUTTON_6 = 6;
    private final static int BUTTON_7 = 7;
    private final static int BUTTON_8 = 8;
    private final static int BUTTON_9 = 9;
    private final static int BUTTON_10 = 10;

    public BBoard(int port) {
        joystick = new Joystick(port);
        left_thumb = new JoystickButton(joystick, BUTTON_1);
        left_index = new JoystickButton(joystick, BUTTON_2);
        left_middle = new JoystickButton(joystick, BUTTON_3);
        left_ring = new JoystickButton(joystick, BUTTON_4);
        left_pinky = new JoystickButton(joystick, BUTTON_5);
        right_thumb = new JoystickButton(joystick, BUTTON_6);
        right_index = new JoystickButton(joystick, BUTTON_7);
        right_middle = new JoystickButton(joystick, BUTTON_8);
        right_ring = new JoystickButton(joystick, BUTTON_9);
        right_pinky = new JoystickButton(joystick, BUTTON_10);
    }

    public boolean left_thumb() {
        if(left_thumb.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void left_thumb_runOnPress(Command command) {
        left_thumb.whenPressed(command);
    }
    public void left_thumb_runOnRelease(Command command) {
        left_thumb.whenReleased(command);
    }
    public void left_thumb_toggleOnPress(Command command) {
        left_thumb.toggleWhenPressed(command);
    }
    public void left_thumb_cancelOnPress(Command command) {
        left_thumb.cancelWhenPressed(command);
    }
    public void left_thumb_runWhileHeld(Command command) {
        left_thumb.whileHeld(command);
    }

    public boolean left_index() {
        if(left_index.get()) {
            return true;
        }
        else {
            return false;
        }
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
        if(left_middle.get()) {
            return true;
        }
        else {
            return false;
        }
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
        if(left_ring.get()) {
            return true;
        }
        else {
            return false;
        }
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

    public boolean left_pinky() {
        if(left_pinky.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void left_pinky_runOnPress(Command command) {
        left_pinky.whenPressed(command);
    }
    public void left_pinky_runOnRelease(Command command) {
        left_pinky.whenReleased(command);
    }
    public void left_pinky_toggleOnPress(Command command) {
        left_pinky.toggleWhenPressed(command);
    }
    public void left_pinky_cancelOnPress(Command command) {
        left_pinky.cancelWhenPressed(command);
    }
    public void left_pinky_runWhileHeld(Command command) {
        left_pinky.whileHeld(command);
    }
}