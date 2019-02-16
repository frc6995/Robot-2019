package frc.robot.controllermap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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

}