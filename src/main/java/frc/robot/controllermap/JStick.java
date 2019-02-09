package frc.robot.controllermap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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

    public JStick(int port) {
        joystick = new Joystick(port);
    }

}