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
  //HatchMech constants
  public final static int HATCHMECH_PCM_ID = 1;
  public final static int HATCHMECH_FORWARD_CHANNEL = 1;
  public final static int HATCHMECH_REVERSE_CHANNEL = 0;

//OI - joystick
  public final static int OI_JOYSTICK = 1;
  public final static int DRIVE_STICK_MOVE_AXIS = 1;
  public final static int DRIVE_STICK_LEFTRIGHT_AXIS = 0;
  public final static int DRIVE_STICK_ROTATE_AXIS = 2;
  public final static int DRIVE_STICK_THROT_AXIS = 3; 
  public final static int HATCH_DEPLOY = 4;
  public final static int HATCH_RETRACT = 5; 
  public final static int HATCH_TOGGLE = 6;

//OI - xbox
  public final static int OI_XBOX = 0;
  public static final int DRIVE_XBOX_LEFT_X_AXIS = 0;
  public static final int DRIVE_XBOX_LEFT_Y_AXIS = 1; 
  public static final int DRIVE_XBOX_LEFT_TRIGGER = 2;
  public static final int DRIVE_XBOX_RIGHT_TRIGGER = 3;
  public static final int DRIVE_XBOX_RIGHT_X_AXIS = 4;
  //public static final int DRIVE_XBOX_RIGHT_Y_AXIS = 5;
}
