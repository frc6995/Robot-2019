package frc.robot;

public class RobotMap {
//CAN bus
//Talons
  public final static int DRIVEBASE_LEFT_TALON_CAN_ID = 2;
  public final static int DRIVEBASE_RIGHT_TALON_CAN_ID = 3;
  public final static int DRIVEBASE_LEFTB_TALON_CAN_ID = 4;
  public final static int DRIVEBASE_RIGHTB_TALON_CAN_ID = 5;

  public final static int LADDER_MOTOR_A_TALON_CAN_ID = 6;
  public final static int LADDER_MOTOR_B_TALON_CAN_ID = 7;

//OI - joystick
  public final static int OI_JOYSTICK = 0;
  public final static int DRIVE_STICK_MOVE_AXIS = 1;
  public final static int DRIVE_STICK_LEFTRIGHT_AXIS = 0;
  public final static int DRIVE_STICK_ROTATE_AXIS = 2;
  public final static int DRIVE_STICK_THROT_AXIS = 3;  
  public final static int LADDER_UP_BUTTON = 4;  // change as needed
  public final static int LADDER_DOWN_BUTTON = 5; //change as needed

//OI - xbox
  public final static int OI_XBOX = 1;
  public static final int DRIVE_XBOX_LEFT_X_AXIS = 0;
  public static final int DRIVE_XBOX_LEFT_Y_AXIS = 1; 
  public static final int DRIVE_XBOX_LEFT_TRIGGER = 2;
  public static final int DRIVE_XBOX_RIGHT_TRIGGER = 3;
}
