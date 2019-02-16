package frc.robot.controllermap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.Command;

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
    public void button_1_runOnPress(Command command) {
        one_trigger.whenPressed(command);
    }
    public void button_1_runOnRelease(Command command) {
        one_trigger.whenReleased(command);
    }
    public void button_1_toggleOnPress(Command command) {
        one_trigger.toggleWhenPressed(command);
    }
    public void button_1_cancelOnPress(Command command) {
        one_trigger.cancelWhenPressed(command);
    }
    public void button_1_runWhileHeld(Command command) {
        one_trigger.whileHeld(command);
    }

    public boolean button_2() {
        if(two_thumb.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_2_runOnPress(Command command) {
        two_thumb.whenPressed(command);
    }
    public void button_2_runOnRelease(Command command) {
        two_thumb.whenReleased(command);
    }
    public void button_2_toggleOnPress(Command command) {
        two_thumb.toggleWhenPressed(command);
    }
    public void button_2_cancelOnPress(Command command) {
        two_thumb.cancelWhenPressed(command);
    }
    public void button_2_runWhileHeld(Command command) {
        two_thumb.whileHeld(command);
    }

    public boolean button_3() {
        if(three_topBottomLeft.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_3_runOnPress(Command command) {
        three_topBottomLeft.whenPressed(command);
    }
    public void button_3_runOnRelease(Command command) {
        three_topBottomLeft.whenReleased(command);
    }
    public void button_3_toggleOnPress(Command command) {
        three_topBottomLeft.toggleWhenPressed(command);
    }
    public void button_3_cancelOnPress(Command command) {
        three_topBottomLeft.cancelWhenPressed(command);
    }
    public void button_3_runWhileHeld(Command command) {
        three_topBottomLeft.whileHeld(command);
    }

    public boolean button_4() {
        if(four_topBottomRight.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_4_runOnPress(Command command) {
        four_topBottomRight.whenPressed(command);
    }
    public void button_4_runOnRelease(Command command) {
        four_topBottomRight.whenReleased(command);
    }
    public void button_4_toggleOnPress(Command command) {
        four_topBottomRight.toggleWhenPressed(command);
    }
    public void button_4_cancelOnPress(Command command) {
        four_topBottomRight.cancelWhenPressed(command);
    }
    public void button_4_runWhileHeld(Command command) {
        four_topBottomRight.whileHeld(command);
    }

    public boolean button_5() {
        if(five_topTopLeft.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_5_runOnPress(Command command) {
        five_topTopLeft.whenPressed(command);
    }
    public void button_5_runOnRelease(Command command) {
        five_topTopLeft.whenReleased(command);
    }
    public void button_5_toggleOnPress(Command command) {
        five_topTopLeft.toggleWhenPressed(command);
    }
    public void button_5_cancelOnPress(Command command) {
        five_topTopLeft.cancelWhenPressed(command);
    }
    public void button_5_runWhileHeld(Command command) {
        five_topTopLeft.whileHeld(command);
    }

    public boolean button_6() {
        if(six_topTopRight.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_6_runOnPress(Command command) {
        six_topTopRight.whenPressed(command);
    }
    public void button_6_runOnRelease(Command command) {
        six_topTopRight.whenReleased(command);
    }
    public void button_6_toggleOnPress(Command command) {
        six_topTopRight.toggleWhenPressed(command);
    }
    public void button_6_cancelOnPress(Command command) {
        six_topTopRight.cancelWhenPressed(command);
    }
    public void button_6_runWhileHeld(Command command) {
        six_topTopRight.whileHeld(command);
    }

    public boolean button_7() {
        if(seven_leftTopLeft.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_7_runOnPress(Command command) {
        seven_leftTopLeft.whenPressed(command);
    }
    public void button_7_runOnRelease(Command command) {
        seven_leftTopLeft.whenReleased(command);
    }
    public void button_7_toggleOnPress(Command command) {
        seven_leftTopLeft.toggleWhenPressed(command);
    }
    public void button_7_cancelOnPress(Command command) {
        seven_leftTopLeft.cancelWhenPressed(command);
    }
    public void button_7_runWhileHeld(Command command) {
        seven_leftTopLeft.whileHeld(command);
    }

    public boolean button_8() {
        if(eight_leftTopRight.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_8_runOnPress(Command command) {
        eight_leftTopRight.whenPressed(command);
    }
    public void button_8_runOnRelease(Command command) {
        eight_leftTopRight.whenReleased(command);
    }
    public void button_8_toggleOnPress(Command command) {
        eight_leftTopRight.toggleWhenPressed(command);
    }
    public void button_8_cancelOnPress(Command command) {
        eight_leftTopRight.cancelWhenPressed(command);
    }
    public void button_8_runWhileHeld(Command command) {
        eight_leftTopRight.whileHeld(command);
    }

    public boolean button_9() {
        if(nine_leftMiddleLeft.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_9_runOnPress(Command command) {
        nine_leftMiddleLeft.whenPressed(command);
    }
    public void button_9_runOnRelease(Command command) {
        nine_leftMiddleLeft.whenReleased(command);
    }
    public void button_9_toggleOnPress(Command command) {
        nine_leftMiddleLeft.toggleWhenPressed(command);
    }
    public void button_9_cancelOnPress(Command command) {
        nine_leftMiddleLeft.cancelWhenPressed(command);
    }
    public void button_9_runWhileHeld(Command command) {
        nine_leftMiddleLeft.whileHeld(command);
    }

    public boolean button_10() {
        if(ten_leftMiddleRight.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_10_runOnPress(Command command) {
        ten_leftMiddleRight.whenPressed(command);
    }
    public void button_10_runOnRelease(Command command) {
        ten_leftMiddleRight.whenReleased(command);
    }
    public void button_10_toggleOnPress(Command command) {
        ten_leftMiddleRight.toggleWhenPressed(command);
    }
    public void button_10_cancelOnPress(Command command) {
        ten_leftMiddleRight.cancelWhenPressed(command);
    }
    public void button_10_runWhileHeld(Command command) {
        ten_leftMiddleRight.whileHeld(command);
    }

    public boolean button_11() {
        if(eleven_leftBottomLeft.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_11_runOnPress(Command command) {
        eleven_leftBottomLeft.whenPressed(command);
    }
    public void button_11_runOnRelease(Command command) {
        eleven_leftBottomLeft.whenReleased(command);
    }
    public void button_11_toggleOnPress(Command command) {
        eleven_leftBottomLeft.toggleWhenPressed(command);
    }
    public void button_11_cancelOnPress(Command command) {
        eleven_leftBottomLeft.cancelWhenPressed(command);
    }
    public void button_11_runWhileHeld(Command command) {
        eleven_leftBottomLeft.whileHeld(command);
    }

    public boolean button_12() {
        if(twelve_leftBottomRIght.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void button_12_runOnPress(Command command) {
        twelve_leftBottomRIght.whenPressed(command);
    }
    public void button_12_runOnRelease(Command command) {
        twelve_leftBottomRIght.whenReleased(command);
    }
    public void button_12_toggleOnPress(Command command) {
        twelve_leftBottomRIght.toggleWhenPressed(command);
    }
    public void button_12_cancelOnPress(Command command) {
        twelve_leftBottomRIght.cancelWhenPressed(command);
    }
    public void button_12_runWhileHeld(Command command) {
        twelve_leftBottomRIght.whileHeld(command);
    }
}