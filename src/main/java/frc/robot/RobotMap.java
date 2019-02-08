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

//OI - joystick
  public final static int OI_JOYSTICK = 0;
  //HatchMech constants
  public final static int PCM_ID_DSOLENOID_HATCHMECH = 1;
  public final static int DSOLENOID_HATCHMECH_FORWARD = 1;
  public final static int DSOLENOID_HATCHMECH_REVERSE = 0;

  //OI - joystick
  public final static int DRIVE_STICK_MOVE_AXIS = 1;
  public final static int DRIVE_STICK_LEFTRIGHT_AXIS = 0;
  public final static int DRIVE_STICK_ROTATE_AXIS = 2;
  public final static int DRIVE_STICK_THROT_AXIS = 3;
  //climber buttons
  public final static int BUTTON_FRONT_TOGGLE = 3;
  public final static int BUTTON_REAR_TOGGLE = 4;
  public final static int BUTTON_CLIMB_BOX = 2;

  //OI - xbox
  public final static int OI_XBOX = 0;
  public static final int DRIVE_XBOX_LEFT_X_AXIS = 0;
  public static final int DRIVE_XBOX_LEFT_Y_AXIS = 1; 
  public static final int DRIVE_XBOX_LEFT_TRIGGER = 2;
  public static final int DRIVE_XBOX_RIGHT_TRIGGER = 3;

//Climb constants
  public final static int PCM_ID_DSOLENOID_CLIMBER_FRONT = 1;
  public final static int PCM_ID_DSOLENOID_CLIMBER_REAR = 2; //doesn't exist, most likely 2
  public final static int DIO_LIMIT_CLIMBER_FRONT = 0;
  public final static int DIO_LIMIT_CLIMBER_REAR = 1; //doesn't exist, most likely 1
  public final static int PWM_ID_SPARK_CLIMB_MOVEMENT = 7; //doesn't exist, most likely 7, counting down
  
  public static final int DRIVE_XBOX_RIGHT_X_AXIS = 4;
  public final static int BUTTON_HATCH_TOGGLE = 0;
  //public static final int DRIVE_XBOX_RIGHT_Y_AXIS = 5;
}
