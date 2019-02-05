/*
 * POVhat.java
 */

package frc.robot.controller_map;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Allows the POV hat switch on the joystick to be represented like a group 
 * of 8 buttons.  Attach commands as easily as with buttons.
 * @author Dustin Maki
 */
public class XboxPov { // outer class     
    Joystick stick;
    int hatXaxis;
    int hatYaxis;        
    POVtrigger Right;
    POVtrigger UpRight;
    POVtrigger Up;
    POVtrigger UpLeft;
    POVtrigger Left;
    POVtrigger DownLeft;
    POVtrigger Down;
    POVtrigger DownRight;
    
    class POVtrigger extends Trigger {// inner class
        private final int Xval;
        private final int Yval;    
        private boolean exclusive = true;  // if set true(default), trigger is active only when it exactly matches position of hat switch.
                                          // if set false, trigger is active in any position that shares trigger name.
        // i.e. If Up is set not exclusive, Command associated with Up will trigger if hat switch is UpLeft, UpRight, or Up.
        private static final int kRight_XVAL = 1;
        private static final int kUpRight_XVAL = 1;
        private static final int kUp_XVAL = 0;
        private static final int kUpLeft_XVAL = -1;
        private static final int kLeft_XVAL = -1;
        private static final int kDownLeft_XVAL = -1;
        private static final int kDown_XVAL = 0;
        private static final int kDownRight_XVAL = 1;

        private static final int kRight_YVAL = 0;
        private static final int kUpRight_YVAL = -1;
        private static final int kUp_YVAL = -1;
        private static final int kUpLeft_YVAL = -1;
        private static final int kLeft_YVAL = 0;
        private static final int kDownLeft_YVAL = 1;
        private static final int kDown_YVAL = 1;
        private static final int kDownRight_YVAL = 1;

        protected POVtrigger(int Xval, int Yval) {// inner class constructor       
            this.Xval = Xval;
            this.Yval = Yval;
        }
        
        public void setExclusive(boolean exclusive) { //only useful on up, down, left, and right
            this.exclusive = exclusive;            
        }

        public boolean get() { // mandatory override from Trigger class
            if (exclusive)
                return (stick.getRawAxis(hatXaxis) == this.Xval && stick.getRawAxis(hatYaxis) == this.Yval);
            else
                return ((0 == this.Xval || stick.getRawAxis(hatXaxis) == this.Xval) && (0 == this.Yval || stick.getRawAxis(hatYaxis) == this.Yval));
        }
    }   
    // begin outer class constructors
//    public POVhat()
//    {
//        this(Robot.oi.driverJoystick, 5, 6);          
//    }
    
    public XboxPov(Joystick stick) {}
        this(stick, 5, 6);          
    }
    
    public XboxPov(Joystick stick, int hatXaxis, int hatYaxis) {}
        this.stick = stick;
        this.hatXaxis = hatXaxis;
        this.hatYaxis = hatYaxis; 
        this.Right = new POVtrigger(POVtrigger.kRight_XVAL, POVtrigger.kRight_YVAL);
        this.UpRight = new POVtrigger(POVtrigger.kUpRight_XVAL, POVtrigger.kUpRight_YVAL);
        this.Up = new POVtrigger(POVtrigger.kUp_XVAL, POVtrigger.kUp_YVAL);
        this.UpLeft = new POVtrigger(POVtrigger.kUpLeft_XVAL, POVtrigger.kUpLeft_YVAL);
        this.Left = new POVtrigger(POVtrigger.kLeft_XVAL, POVtrigger.kLeft_YVAL);
        this.DownLeft = new POVtrigger(POVtrigger.kDownLeft_XVAL, POVtrigger.kDownLeft_YVAL);
        this.Down = new POVtrigger(POVtrigger.kDown_XVAL, POVtrigger.kDown_YVAL);
        this.DownRight = new POVtrigger(POVtrigger.kDownRight_XVAL, POVtrigger.kDownRight_YVAL);
    }   
    // end outer class constructors
}