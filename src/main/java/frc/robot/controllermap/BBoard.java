package frc.robot.controllermap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class BBoard {
    private static Joystick joystick;
    private static JoystickButton thumb;
    private static JoystickButton index;
    private static JoystickButton middle;
    private static JoystickButton ring;
    private static JoystickButton pinky;

    private final static int BUTTON_1 = 1;
    private final static int BUTTON_2 = 2;
    private final static int BUTTON_3 = 3;
    private final static int BUTTON_4 = 4;
    private final static int BUTTON_5 = 5;

    public BBoard(int port) {
        joystick = new Joystick(port);
        thumb = new JoystickButton(joystick, BUTTON_1);
        index = new JoystickButton(joystick, BUTTON_2);
        middle = new JoystickButton(joystick, BUTTON_3);
        ring = new JoystickButton(joystick, BUTTON_4);
        pinky = new JoystickButton(joystick, BUTTON_5);
    }

    public boolean thumb() {
        if(thumb.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void thumb_runOnPress(Command command) {
        thumb.whenPressed(command);
    }
    public void thumb_runOnRelease(Command command) {
        thumb.whenReleased(command);
    }
    public void thumb_toggleOnPress(Command command) {
        thumb.toggleWhenPressed(command);
    }
    public void thumb_cancelOnPress(Command command) {
        thumb.cancelWhenPressed(command);
    }
    public void thumb_runWhileHeld(Command command) {
        thumb.whileHeld(command);
    }

    public boolean index() {
        if(index.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void index_runOnPress(Command command) {
        index.whenPressed(command);
    }
    public void index_runOnRelease(Command command) {
        index.whenReleased(command);
    }
    public void index_toggleOnPress(Command command) {
        index.toggleWhenPressed(command);
    }
    public void index_cancelOnPress(Command command) {
        index.cancelWhenPressed(command);
    }
    public void index_runWhileHeld(Command command) {
        index.whileHeld(command);
    }

    public boolean middle() {
        if(middle.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void middle_runOnPress(Command command) {
        middle.whenPressed(command);
    }
    public void middle_runOnRelease(Command command) {
        middle.whenReleased(command);
    }
    public void middle_toggleOnPress(Command command) {
        middle.toggleWhenPressed(command);
    }
    public void middle_cancelOnPress(Command command) {
        middle.cancelWhenPressed(command);
    }
    public void middle_runWhileHeld(Command command) {
        middle.whileHeld(command);
    }

    public boolean ring() {
        if(ring.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void ring_runOnPress(Command command) {
        ring.whenPressed(command);
    }
    public void ring_runOnRelease(Command command) {
        ring.whenReleased(command);
    }
    public void ring_toggleOnPress(Command command) {
        ring.toggleWhenPressed(command);
    }
    public void ring_cancelOnPress(Command command) {
        ring.cancelWhenPressed(command);
    }
    public void ring_runWhileHeld(Command command) {
        ring.whileHeld(command);
    }

    public boolean pinky() {
        if(pinky.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void pinky_runOnPress(Command command) {
        pinky.whenPressed(command);
    }
    public void pinky_runOnRelease(Command command) {
        pinky.whenReleased(command);
    }
    public void pinky_toggleOnPress(Command command) {
        pinky.toggleWhenPressed(command);
    }
    public void pinky_cancelOnPress(Command command) {
        pinky.cancelWhenPressed(command);
    }
    public void pinky_runWhileHeld(Command command) {
        pinky.whileHeld(command);
    }
}