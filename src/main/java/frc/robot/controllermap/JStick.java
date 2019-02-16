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

    private static POVButton hat_up;
    private static POVButton hat_down;
    private static POVButton hat_left;
    private static POVButton hat_right;
    private static POVButton hat_up_left;
    private static POVButton hat_up_right;
    private static POVButton hat_down_left;
    private static POVButton hat_down_right;

    private enum POV {
        UP, DOWN, LEFT, RIGHT, UP_RIGHT, UP_LEFT, DOWN_LEFT, DOWN_RIGHT, CENTER;
    }

    private static int HAT(POV pov) {
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
    private final static int AXIS_THROT = 3;

    private final static int HAT_UP = HAT(POV.UP);
    private final static int HAT_DOWN = HAT(POV.DOWN);
    private final static int HAT_LEFT = HAT(POV.LEFT);
    private final static int HAT_RIGHT = HAT(POV.RIGHT);
    private final static int HAT_UP_LEFT = HAT(POV.UP_LEFT);
    private final static int HAT_UP_RIGHT = HAT(POV.UP_RIGHT);
    private final static int HAT_DOWN_LEFT = HAT(POV.DOWN_LEFT);
    private final static int HAT_DOWN_RIGHT = HAT(POV.DOWN_RIGHT);

    public JStick(int port) {
        joystick = new Joystick(port);
        one_trigger = new JoystickButton(joystick, BUTTON_1);
        two_thumb = new JoystickButton(joystick, BUTTON_2);
        three_topBottomLeft = new JoystickButton(joystick, BUTTON_3);
        four_topBottomRight = new JoystickButton(joystick, BUTTON_4);
        five_topTopLeft = new JoystickButton(joystick, BUTTON_5);
        six_topTopRight = new JoystickButton(joystick, BUTTON_6);
        seven_leftTopLeft = new JoystickButton(joystick, BUTTON_7);
        eight_leftTopRight = new JoystickButton(joystick, BUTTON_8);
        nine_leftMiddleLeft = new JoystickButton(joystick, BUTTON_9);
        ten_leftMiddleRight = new JoystickButton(joystick, BUTTON_10);
        eleven_leftBottomLeft = new JoystickButton(joystick, BUTTON_11);
        twelve_leftBottomRIght = new JoystickButton(joystick, BUTTON_12);
        hat_up = new POVButton(joystick, HAT_UP);
        hat_down = new POVButton(joystick, HAT_DOWN);
        hat_left = new POVButton(joystick, HAT_LEFT);
        hat_right = new POVButton(joystick, HAT_RIGHT);
        hat_up_left = new POVButton(joystick, HAT_UP_LEFT);
        hat_up_right = new POVButton(joystick, HAT_UP_RIGHT);
        hat_down_left = new POVButton(joystick, HAT_DOWN_LEFT);
        hat_down_right = new POVButton(joystick, HAT_DOWN_RIGHT);
    }

    public double stick_x() {
        return joystick.getRawAxis(AXIS_X);
    }

    public double stick_y() {
        return joystick.getRawAxis(AXIS_Y);
    }

    public double stick_throt() {
        return joystick.getRawAxis(AXIS_THROT); 
    }

    public boolean button_1() {
        if(one_trigger.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_2() {
        if(two_thumb.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_3() {
        if(three_topBottomLeft.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_4() {
        if(four_topBottomRight.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_5() {
        if(five_topTopLeft.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_6() {
        if(six_topTopRight.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_7() {
        if(seven_leftTopLeft.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_8() {
        if(two_thumb.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_9() {
        if(nine_leftMiddleLeft.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_10() {
        if(ten_leftMiddleRight.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_11() {
        if(eleven_leftBottomLeft.get()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean button_12() {
        if(twelve_leftBottomRIght.get()) {
            return true;
        }
        else {
            return false;
        }
    }
}