/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
  public final static int DRIVE_STICK_MOVE_AXIS = 1;
  public final static int DRIVE_STICK_LEFTRIGHT_AXIS = 0;
  public final static int DRIVE_STICK_ROTATE_AXIS = 2;
  public final static int DRIVE_STICK_THROT_AXIS = 3;
  public final static int CLIMBER_DEPLOY = 3;
  public final static int CLIMBER_RETRACT = 4;
  public final static int CLIMB_BOX = 2;

//OI - xbox
  public final static int OI_XBOX = 0;
  public static final int DRIVE_XBOX_LEFT_X_AXIS = 0;
  public static final int DRIVE_XBOX_LEFT_Y_AXIS = 1; 
  public static final int DRIVE_XBOX_LEFT_TRIGGER = 2;
  public static final int DRIVE_XBOX_RIGHT_TRIGGER = 3;

//Climb constants
  public final static int CLIMBER_ID_FRONT = 1;
  public final static int CLIMBER_ID_REAR = 3; //doesn't exist
  public final static int CLIMBER_FRONT_LIMIT = 0;
  public final static int CLIMBER_REAR_LIMIT = 5;
  
  public static final int DRIVE_XBOX_RIGHT_X_AXIS = 4;
  //public static final int DRIVE_XBOX_RIGHT_Y_AXIS = 5;
}
