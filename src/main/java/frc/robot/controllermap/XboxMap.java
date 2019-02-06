package frc.robot.controllermap;

import edu.wpi.first.wpilibj.XboxController;

public class XboxMap {

    private enum POV {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        UP_RIGHT,
        UP_LEFT,
        DOWN_LEFT,
        DOWN_RIGHT;
    }

    private static int DPAD(POV pov) {
        switch (pov) {
            case UP: return 0;
            case LEFT: return 90;
            case DOWN: return 180;
            case RIGHT: return 270;
            case UP_RIGHT: return 315;
            case UP_LEFT: return 45;
            case DOWN_LEFT: return 135;
            case DOWN_RIGHT: return 225;
            default: return -1;
        }
   }

    //BUTTONS
    public final static int BUTTON_A = 1;
    public final static int BUTTON_B = 2;
    public final static int BUTTON_X = 3;
    public final static int BUTTON_Y = 4;
    public final static int BUTTON_LEFT_BUMPER = 5;
    public final static int BUTTON_RIGHT_BUMPER = 6;
    public final static int BUTTON_START = 7;
    public final static int BUTTON_BACK = 8;
    public final static int BUTTON_LEFT_STICK = 9;
    public final static int BUTTON_RIGHT_STICK = 10;

    //AXIS
    public final static int AXIS_LEFT_STICK_X = 1;
    public final static int AXIS_LEFT_STICK_Y = 2;
    public final static int AXIS_RIGHT_STICK_X = 3;
    public final static int AXIS_RIGHT_STICK_Y = 4;
    public final static int AXIS_LEFT_TRIGGER = 5;
    public final static int AXIS_RIGHT_TRIGGER = 6;
   
    //DPAD
    public final static int DPAD_UP = DPAD(POV.UP);
    public final static int DPAD_LEFT = DPAD(POV.LEFT);
    public final static int DPAD_DOWN = DPAD(POV.DOWN);
    public final static int DPAD_RIGHT = DPAD(POV.RIGHT);
    public final static int DPAD_UP_RIGHT = DPAD(POV.UP_RIGHT);
    public final static int DPAD_UP_LEFT = DPAD(POV.UP_LEFT);
    public final static int DPAD_DOWN_RIGHT = DPAD(POV.DOWN_RIGHT);
    public final static int DPAD_DOWN_LEFT = DPAD(POV.DOWN_RIGHT);
} 