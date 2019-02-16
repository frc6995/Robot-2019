package frc.robot.controllermap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

public class JStick {
    private static Joystick joystick;
    private static JoystickButton one_trigger;
    private static JoystickButton two_thumb;
    private static JoystickButton three_topBottomLeft;
    private static JoystickButton four_topBottomRight;
    private static JoystickButton five_topTopLeft;
    private static JoystickButton six_topTopRight;
    private static JoystickButton seven_leftTopLeft;
    private static JoystickButton eight_leftTopRight;
    private static JoystickButton nine_leftMiddleLeft;
    private static JoystickButton ten_leftMiddleRight;
    private static JoystickButton eleven_leftBottomLeft;
    private static JoystickButton twelve_leftBottomRIght;

    private static POVButton dpad_up;
    private static POVButton dpad_down;
    private static POVButton dpad_left;
    private static POVButton dpad_right;
    private static POVButton dpad_up_left;
    private static POVButton dpad_up_right;
    private static POVButton dpad_down_left;
    private static POVButton dpad_down_right;

    private enum POV {
        UP, DOWN, LEFT, RIGHT, UP_RIGHT, UP_LEFT, DOWN_LEFT, DOWN_RIGHT, CENTER;
    }

    private static int DPAD(POV pov) {
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
    private final static int BUTTON_11 = 11;
    private final static int BUTTON_12 = 12;

    private final static int AXIS_X = 1;
    private final static int AXIS_Y = 2;

    public JStick(int port) {
        joystick = new Joystick(port);
        one_trigger = new JoystickButton(joystick, BUTTON_1);
    }

}