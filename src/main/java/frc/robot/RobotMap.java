/**
   * XBOX one Button mapping reference (INCORRECT DO NOT USE)
   * 0 = A
   * 1 = B
   * 2 = X
   * 3 = Y
   * 4 = Left Bumper
   * 5 = Right Bumper
   * 8 = Left center button (what used to be back)
   * 9 = Right center button (what used to be start)
   * 10 = Left Joystick press
   * 11 = Right Joystick press
   * 12 = Dpad up
   * 13 = Dpad down
   * 14 = Dpad Left
   * 15 = Dpad Right
   */

package frc.robot;

public class RobotMap {
  //CAN bus
  //Talons
  public final static int CAN_ID_DRIVEBASE_LEFT_TALON_F = 2;
  public final static int CAN_ID_DRIVEBASE_RIGHT_TALON_F = 3;
  public final static int CAN_ID_DRIVEBASE_LEFT_TALON_B = 4;
  public final static int CAN_ID_DRIVEBASE_RIGHT_TALON_B = 5;

  //HatchMech constants
  public final static int PCM_ID_DSOLENOID_HATCHMECH = 1;
  public final static int DSOLENOID_HATCHMECH_FORWARD = 1;
  public final static int DSOLENOID_HATCHMECH_REVERSE = 0;

  //OI - joystick
  public final static int OI_JOYSTICK = 1;

  //OI - xbox
  public final static int OI_XBOX = 0;
  public static final int XBOX_DRIVE_LEFT_RIGHT = 0;
  public static final int XBOX2_DRIVE_FORWARD_BACK = 1; 
  public static final int XBOX_DRIVE_FORWARD_SPEED = 2;
  public static final int XBOX_DRIVE_RIGHT_TRIGGER = 3;
  public static final int XBOX2_DRIVE_LEFT_RIGHT = 4;
  public final static int BUTTON_HATCH_TOGGLE = 0;
  public final static int BUTTON_THROTTLE_CHANGE = 2;
  public final static int BUTTON_THROTTLE_RESET = 3;
  //public static final int DRIVE_XBOX_RIGHT_Y_AXIS = 5;
}
