package frc.robot.controllermap;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
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
    private static Button back;

    private static POVButton dpad_center;
    private static POVButton dpad_up;
    private static POVButton dpad_down;
    private static POVButton dpad_left;
    private static POVButton dpad_right;
    private static POVButton dpad_up_left;
    private static POVButton dpad_up_right;
    private static POVButton dpad_down_left;
    private static POVButton dpad_dwon_right;
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

    public Xbox(int portNo) {
        xbox = new XboxController(portNo);

        a = new JoystickButton(xbox, BUTTON_A);
        b = new JoystickButton(xbox, BUTTON_B);
        x = new JoystickButton(xbox, BUTTON_X);
        y = new JoystickButton(xbox, BUTTON_Y);
        left_stick = new JoystickButton(xbox, BUTTON_LEFT_STICK);
        right_stick = new JoystickButton(xbox, BUTTON_RIGHT_STICK);
        left_bumper = new JoystickButton(xbox, BUTTON_LEFT_BUMPER);
        right_bumper = new JoystickButton(xbox, BUTTON_RIGHT_BUMPER);
        start = new JoystickButton(xbox, BUTTON_START);
        back = new JoystickButton(xbox, BUTTON_BACK);
        
        dpad_up = new POVButton(xbox, DPAD_UP);
        dpad_down = new POVButton(xbox, DPAD_DOWN);
        dpad_left = new POVButton(xbox, DPAD_LEFT);
        dpad_right = new POVButton(xbox, DPAD_RIGHT);
        dpad_up_left = new POVButton(xbox, DPAD_UP_LEFT);
        dpad_up_right = new POVButton(xbox, DPAD_UP_RIGHT);
        dpad_down_left = new POVButton(xbox, DPAD_DOWN_LEFT);
        dpad_dwon_right = new POVButton(xbox, DPAD_DOWN_RIGHT);
        dpad_center = new POVButton(xbox, DPAD_CENTER);
    }

    /**
     * AXIS
     */
    public double left_stick_x() {return xbox.getRawAxis(AXIS_LEFT_STICK_X);}
    public double left_stick_y() {return xbox.getRawAxis(AXIS_LEFT_STICK_Y);}
    public double right_stick_x() {return xbox.getRawAxis(AXIS_RIGHT_STICK_X);}
    public double right_stick_y() {return xbox.getRawAxis(AXIS_RIGHT_STICK_Y);}
    public double right_trigger() {return xbox.getRawAxis(AXIS_RIGHT_TRIGGER);}
    public double left_trigger() {return xbox.getRawAxis(AXIS_LEFT_TRIGGER);}

    /**
     * BUTTONS
     */
    public boolean a() {if(xbox.getAButton()){return true;} else{return false;}}
    public void a_runOnPressed(Command command) {a.whenPressed(command);}
    public void a_runOnRelease(Command command) {a.whenReleased(command);}
    public void a_toggleOnPress(Command command) {a.toggleWhenPressed(command);}
    public void a_cancelOnPress(Command command) {a.cancelWhenPressed(command);}
    public void a_runWhileHeld(Command command) {a.whileHeld(command);}

    public boolean b() {if(xbox.getBButton()){return true;} else{return false;}}
    public void b_runOnPressed(Command command) {b.whenPressed(command);}
    public void b_runOnRelease(Command command) {b.whenReleased(command);}
    public void b_toggleOnPress(Command command) {b.toggleWhenPressed(command);}
    public void b_cancelOnPress(Command command) {b.cancelWhenPressed(command);}
    public void _runWhileHeld(Command command) {b.whileHeld(command);}

    public boolean x() {if(xbox.getXButton()){return true;} else{return false;}}
    public void x_runOnPressed(Command command) {x.whenPressed(command);}
    public void x_runOnRelease(Command command) {x.whenReleased(command);}
    public void x_toggleOnPress(Command command) {x.toggleWhenPressed(command);}
    public void x_cancelOnPress(Command command) {x.cancelWhenPressed(command);}
    public void x_runWhileHeld(Command command) {x.whileHeld(command);}

    public boolean y() {if(xbox.getYButton()){return true;} else{return false;}}
    public void y_runOnPressed(Command command) {y.whenPressed(command);}
    public void y_runOnRelease(Command command) {y.whenReleased(command);}
    public void y_toggleOnPress(Command command) {y.toggleWhenPressed(command);}
    public void y_cancelOnPress(Command command) {y.cancelWhenPressed(command);}
    public void y_runWhileHeld(Command command) {y.whileHeld(command);}

    public boolean back() {if(xbox.getBackButton()){return true;} else{return false;}}
    public void back_runOnPressed(Command command) {back.whenPressed(command);}
    public void back_runOnRelease(Command command) {back.whenReleased(command);}
    public void back_toggleOnPress(Command command) {back.toggleWhenPressed(command);}
    public void back_cancelOnPress(Command command) {back.cancelWhenPressed(command);}
    public void back_runWhileHeld(Command command) {back.whileHeld(command);}

    public boolean start() {if(xbox.getStartButton()){return true;} else{return false;}}
    public void start_runOnPressed(Command command) {start.whenPressed(command);}
    public void start_runOnRelease(Command command) {start.whenReleased(command);}
    public void start_toggleOnPress(Command command) {start.toggleWhenPressed(command);}
    public void start_cancelOnPress(Command command) {start.cancelWhenPressed(command);}
    public void start_runWhileHeld(Command command) {start.whileHeld(command);}

    public boolean left_stick() {if(xbox.getStickButton(Hand.kLeft)){return true;} else{return false;}}
    public void left_stick_runOnPressed(Command command) {left_stick.whenPressed(command);}
    public void left_stick_runOnRelease(Command command) {left_stick.whenReleased(command);}
    public void left_stick_toggleOnPress(Command command) {left_stick.toggleWhenPressed(command);}
    public void left_stick_cancelOnPress(Command command) {left_stick.cancelWhenPressed(command);}
    public void left_stick_runWhileHeld(Command command) {left_stick.whileHeld(command);}

    public boolean right_stick() {if(xbox.getStickButton(Hand.kRight)){return true;} else{return false;}}
    public void right_stick_runOnPressed(Command command) {right_stick.whenPressed(command);}
    public void right_stick_runOnRelease(Command command) {right_stick.whenReleased(command);}
    public void right_stick_toggleOnPress(Command command) {right_stick.toggleWhenPressed(command);}
    public void right_stick_cancelOnPress(Command command) {right_stick.cancelWhenPressed(command);}
    public void right_stick_runWhileHeld(Command command) {right_stick.whileHeld(command);}

    public boolean left_bumper() {if(xbox.getStartButton()){return true;} else{return false;}}
    public void left_bumper_runOnPressed(Command command) {left_bumper.whenPressed(command);}
    public void left_bumper_runOnRelease(Command command) {left_bumper.whenReleased(command);}
    public void left_bumper_toggleOnPress(Command command) {left_bumper.toggleWhenPressed(command);}
    public void left_bumper_cancelOnPress(Command command) {left_bumper.cancelWhenPressed(command);}
    public void left_bumper_runWhileHeld(Command command) {left_bumper.whileHeld(command);}

    public boolean right_bumper() {if(xbox.getStartButton()){return true;} else{return false;}}
    public void right_bumper_runOnPressed(Command command) {right_bumper.whenPressed(command);}
    public void right_bumper_runOnRelease(Command command) {right_bumper.whenReleased(command);}
    public void right_bumper_toggleOnPress(Command command) {right_bumper.toggleWhenPressed(command);}
    public void right_bumper_cancelOnPress(Command command) {right_bumper.cancelWhenPressed(command);}
    public void right_bumper_runWhileHeld(Command command) {right_bumper.whileHeld(command);}

    /**
     * POV
     */
    public boolean dpad_up() {if(dpad_up.get()){return true;} else{return false;}}
    public void dpad_up_runOnPressed(Command command) {dpad_up.whenPressed(command);}
    public void dpad_up_runOnRelease(Command command) {dpad_up.whenReleased(command);}
    public void dpad_up_toggleOnPress(Command command) {dpad_up.toggleWhenPressed(command);}
    public void dpad_up_cancelOnPress(Command command) {dpad_up.cancelWhenPressed(command);}
    public void dpad_up_runWhileHeld(Command command) {dpad_up.whileHeld(command);}
    //public boolean dpad_down() {
     //   if(xbox.getPOV(90) {

        //}
    //}

} 