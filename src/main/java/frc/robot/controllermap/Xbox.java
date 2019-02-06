package frc.robot.controllermap;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class Xbox {
    private static XboxController xbox;
    private static Button a;
    private static Button b;
    private static Button x;
    private static Button y;
    private static Button left_stick;
    private static Button right_stick;
    private static Button left_bumper;
    private static Button right_bumper;
    private static Button start;
    private static Button stop;

    public Xbox(int portNo) {
        xbox = new XboxController(portNo);
        a = new JoystickButton(xbox, BUTTON_A);
        b = new JoystickButton(xbox, BUTTON_A);
        x = new JoystickButton(xbox, BUTTON_A);
        y = new JoystickButton(xbox, BUTTON_A);
        left_stick = new JoystickButton(xbox, BUTTON_A);
        right_stick = new JoystickButton(xbox, BUTTON_A);
        left_bumper = new JoystickButton(xbox, BUTTON_A);
        right_bumper = new JoystickButton(xbox, BUTTON_A);
        start = new JoystickButton(xbox, BUTTON_A);
        stop = new JoystickButton(xbox, BUTTON_A);
        
    }

    public double left_stick_x() {
        return xbox.getRawAxis(AXIS_LEFT_STICK_X);
    }

    public double left_stick_y() {
        return xbox.getRawAxis(AXIS_LEFT_STICK_Y);
    }

    public double right_stick_x() {
        return xbox.getRawAxis(AXIS_RIGHT_STICK_X);
    }

    public double right_stick_y() {
        return xbox.getRawAxis(AXIS_RIGHT_STICK_Y);
    }

    public double right_trigger() {
        return xbox.getRawAxis(AXIS_RIGHT_TRIGGER);
    }

    public double left_trigger() {
        return xbox.getRawAxis(AXIS_LEFT_TRIGGER);
    }

    public boolean a() {
        if(xbox.getAButton() == true) {
            return true;
        } 
        else {
            return false;
        }
    }

    public void a_runOnPressed(Command command) {
        a.whenPressed(command);
    }

    public void a_runOnRelease(Command command) {
        a.whenReleased(command);
    }

    public void a_runOnInactive(Command command) {
        a.whenInactive(command);
    }

    public void a_runOnActive(Command command) {
        a.whenActive(command);
    }

    public void a_toggleOnPress(Command command) {
        a.toggleWhenPressed(command);
    }

    public void a_toggleOnActive(Command command) {
        a.toggleWhenActive(command);
    }

    public void a_cancelOnPress(Command command) {
        a.cancelWhenPressed(command);
    }

    public void a_cancelOnActive(Command command) {
        a.cancelWhenActive(command);
    }

    private enum POV {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        UP_RIGHT,
        UP_LEFT,
        DOWN_LEFT,
        DOWN_RIGHT,
        CENTER;
    }

    private static int DPAD(POV pov) {
        switch (pov) {
            case UP: return 0;
            case UP_RIGHT: return 45;
            case RIGHT: return 90;
            case DOWN_RIGHT: return 135;
            case DOWN: return 180;
            case DOWN_LEFT: return 225;
            case LEFT: return 270;
            case UP_LEFT: return 315;
            case CENTER: return -1;
            default: return -1;
        }
   }

    //BUTTONS
    private final static int BUTTON_A = 1;
    private final static int BUTTON_B = 2;
    private final static int BUTTON_X = 3;
    private final static int BUTTON_Y = 4;
    private final static int BUTTON_LEFT_BUMPER = 5;
    private final static int BUTTON_RIGHT_BUMPER = 6;
    private final static int BUTTON_START = 7;
    private final static int BUTTON_BACK = 8;
    private final static int BUTTON_LEFT_STICK = 9;
    private final static int BUTTON_RIGHT_STICK = 10;

    //AXIS
    private final static int AXIS_LEFT_STICK_X = 1;
    private final static int AXIS_LEFT_STICK_Y = 2;
    private final static int AXIS_RIGHT_STICK_X = 3;
    private final static int AXIS_RIGHT_STICK_Y = 4;
    private final static int AXIS_LEFT_TRIGGER = 5;
    private final static int AXIS_RIGHT_TRIGGER = 6;
   
    //DPAD
    private final static int DPAD_UP = DPAD(POV.UP);
    private final static int DPAD_LEFT = DPAD(POV.LEFT);
    private final static int DPAD_DOWN = DPAD(POV.DOWN);
    private final static int DPAD_RIGHT = DPAD(POV.RIGHT);
    private final static int DPAD_UP_RIGHT = DPAD(POV.UP_RIGHT);
    private final static int DPAD_UP_LEFT = DPAD(POV.UP_LEFT);
    private final static int DPAD_DOWN_RIGHT = DPAD(POV.DOWN_RIGHT);
    private final static int DPAD_DOWN_LEFT = DPAD(POV.DOWN_RIGHT);
    private final static int DPAD_CENTER = DPAD(POV.CENTER);
} 