package frc.robot;

public class RobotMap {
  //CAN bus
  //Talons
  public final static int CAN_ID_TALON_DRIVEBASE_LEFT_F = 2;
  public final static int CAN_ID_TALON_DRIVEBASE_RIGHT_F = 3;
  //VictorSPXs
  public final static int CAN_ID_VSPX_DRIVEBASE_LEFT_M = 6;
  public final static int CAN_ID_VSPX_DRIVEBASE_RIGHT_M = 7;
  public final static int CAN_ID_VSPX_DRIVEBASE_LEFT_B = 4;
  public final static int CAN_ID_VSPX_DRIVEBASE_RIGHT_B = 5;

  //Power Control Module
  public final static int PCM_ID_DSOLENOID_HATCHMECH = 1;

  //HatchMech constants
  public final static int DSOLENOID_HATCHMECH_FORWARD = 1;
  public final static int DSOLENOID_HATCHMECH_REVERSE = 0;

  //OI - Joystick
  public final static int OI_JOYSTICK = 1;

  //OI - XBox
  public final static int OI_XBOX = 0;
  public static final int XBOX_DRIVE_LEFT_RIGHT = 0; 
  public static final int XBOX_DRIVE_FORWARD_SPEED = 2;
  public static final int XBOX_DRIVE_BACKWARD_SPEED = 3;
  public static final int XBOX2_DRIVE_LEFT_RIGHT = 4;
  public static final int XBOX2_DRIVE_FORWARD_BACK = 1;

  //XBox Buttons
  public final static int BUTTON_HATCH_TOGGLE = 0;
  public final static int BUTTON_THROTTLE_CHANGE = 2;
  public final static int BUTTON_THROTTLE_RESET = 3;
}
