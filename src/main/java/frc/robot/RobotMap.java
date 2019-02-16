package frc.robot;

public class RobotMap {
  //CAN bus
  //Talons
  public final static int CAN_ID_TALON_DRIVEBASE_LEFT = 10;
  public final static int CAN_ID_TALON_DRIVEBASE_RIGHT = 11;

  public final static int CAN_ID_TALON_LADDER_A = 13;
  public final static int CAN_ID_TALON_LADDER_B = 12;

  //VictorSPXs
  public final static int CAN_ID_VSPX_DRIVEBASE_LEFT_1 = 20;
  public final static int CAN_ID_VSPX_DRIVEBASE_RIGHT_1 = 22;
  public final static int CAN_ID_VSPX_DRIVEBASE_LEFT_2 = 21;
  public final static int CAN_ID_VSPX_DRIVEBASE_RIGHT_2 = 23;

  public final static int OI_JOYSTICK = 0;
  
  //Power Control Module
  public final static int PCM_ID_DSOLENOID_HATCHMECH = 1;

  //HatchMech constants
  public final static int DSOLENOID_HATCHMECH_FORWARD = 1;
  public final static int DSOLENOID_HATCHMECH_REVERSE = 0;

  //OI - XBox
  public final static int OI_XBOX = 0;
  public static final int XBOX_DRIVE_LEFT_RIGHT = 1; 
  public static final int XBOX_DRIVE_FORWARD_SPEED = 2;
  public static final int XBOX_DRIVE_BACKWARD_SPEED = 3;
  public static final int XBOX2_DRIVE_LEFT_RIGHT = 4;
  public static final int XBOX2_DRIVE_FORWARD_BACK = 1;

  //XBox Buttons
  public final static int BUTTON_HATCH_TOGGLE = 1;


  //OI - Button board
  public final static int OI_BUTTONBOARD = 2;
  public final static int BUTTONBOARD_LADDER_LEVEL_ONE = 1;
  public final static int BUTTONBOARD_LADDER_LEVEL_TWO = 2;
  public final static int BUTTONBOARD_LADDER_LEVEL_THREE = 3; 

  //Limit switch constants
  public static final int DIO_LIMIT_LADDER_TOP = 1;
  public static final int DIO_LIMIT_LADDER_BOTTOM = 2;

  /**
   *Ladder Encoder Constants   
   */  
  public static final int LADDER_LEVEL_ZERO = 0; //The home location
  public static final int LADDER_LEVEL_ONE = -0;
  public static final int LADDER_LEVEL_TWO = -1000; //change as needed
  public static final int LADDER_LEVEL_THREE = -2000; //change as needed

  public final static int BUTTON_THROTTLE_CHANGE = 2;
  public final static int BUTTON_THROTTLE_RESET = 3;
}

