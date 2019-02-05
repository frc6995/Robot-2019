/**
   * XBOX one Button mapping reference
   * 0 = A
   * 1 = B
   * 2 = X
   * 3 = Y
   * 4 = Left Bumper
   * 5 = Right Bumper
   * 6 = Left Trigger (might be mapped as an analog input)
   * 7 = Right Trigger (might be mapped as an analog input)
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
  public final static int DRIVEBASE_LEFT_TALON_CAN_ID = 2;
  public final static int DRIVEBASE_RIGHT_TALON_CAN_ID = 3;
  public final static int DRIVEBASE_LEFTB_TALON_CAN_ID = 4;
  public final static int DRIVEBASE_RIGHTB_TALON_CAN_ID = 5;

  //HatchMech constants
  public final static int PCM_ID_DSOLENOID_HATCHMECH = 1;
  public final static int DSOLENOID_HATCHMECH_FORWARD_CHANNEL = 1;
  public final static int DSOLENOID_HATCHMECH_REVERSE_CHANNEL = 0;

  //CargoShooter constants
  public final static int CAN_ID_TALON_SHOOTER_L = 6;
  public final static int CAN_ID_TALON_SHOOTER_R = 7;

  //OI - joystick
  public final static int OI_JOYSTICK = 1;
  public final static int DRIVE_STICK_MOVE_AXIS = 1;
  public final static int DRIVE_STICK_LEFTRIGHT_AXIS = 0;
  public final static int DRIVE_STICK_ROTATE_AXIS = 2;
  public final static int DRIVE_STICK_THROT_AXIS = 3;

  //OI - xbox
  public final static int OI_XBOX = 0;
  public static final int DRIVE_XBOX_LEFT_X_AXIS = 0;
  public static final int DRIVE_XBOX_LEFT_Y_AXIS = 1; 
  public static final int DRIVE_XBOX_LEFT_TRIGGER = 2;
  public static final int DRIVE_XBOX_RIGHT_TRIGGER = 3;
  public static final int DRIVE_XBOX_RIGHT_X_AXIS = 4;
  public final static int BUTTON_HATCH_TOGGLE = 0;
  public final static int BUTTON_CARGO_SHOOT = 4;
  //public static final int DRIVE_XBOX_RIGHT_Y_AXIS = 5;
}
