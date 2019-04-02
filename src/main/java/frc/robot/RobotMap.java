package frc.robot;
 
public class RobotMap {
  //CAN bus
    //Talons
      //Drive
  public final static int CAN_ID_TALON_DRIVEBASE_LEFT = 10;
  public final static int CAN_ID_TALON_DRIVEBASE_RIGHT = 11;
      //Ladder
  public final static int CAN_ID_TALON_LADDER_A = 13;
  public final static int CAN_ID_TALON_LADDER_B = 12;
      //Cargo
  public static final int CAN_ID_TALON_CARGO = 30;
      //HatchMech
  public static final int PWM_ID_TALON_HATCHWHEELS = 2;

    //VictorSPXs
  public final static int CAN_ID_VSPX_DRIVEBASE_LEFT_1 = 20;
  public final static int CAN_ID_VSPX_DRIVEBASE_RIGHT_1 = 22;
  public final static int CAN_ID_VSPX_DRIVEBASE_LEFT_2 = 21;
  public final static int CAN_ID_VSPX_DRIVEBASE_RIGHT_2 = 23;
  
  //Pneumatics Control Module
  public final static int PCM_ID = 1;
  public final static int PCM_ID_SOLENOID_HATCHMECH = 1;
  public final static int PCM_ID_SOLENOID_CLIMBER_FRONT = 2;
  public final static int PCM_ID_SOLENOID_CLIMBER_REAR = 3;
  public final static int PCM_ID_DSOLENOID_CLIMBER_FRONT_FORWARD = 2;
  public final static int PCM_ID_DSOLENOID_CLIMBER_FRONT_REVERSE = 3;
  public final static int PCM_ID_DSOLENOID_CLIMBER_REAR_FORWARD = 4;
  public final static int PCM_ID_DSOLENOID_CLIMBER_REAR_REVERSE = 5;

  // PWM 
  public static final int PWM_ID_SPARK_CLIMB_MOVEMENT = 0;
  public static final int PWM_ID_SPARK_CARGO_SHOOTER = 1;

  //OI - XBox
  public final static int OI_XBOX = 0;
  public static final int XBOX_DRIVE_LEFT_RIGHT = 1; 
  public static final int XBOX_DRIVE_FORWARD_SPEED = 2;
  public static final int XBOX_DRIVE_BACKWARD_SPEED = 3;

  //OI
  public final static int OI_BUTTONBOARD = 1;
  public final static int OI_JOYSTICK = 2;

  //Limit switch constants
  public static final int DIO_LIMIT_LADDER_BOTTOM = 0;
  public static final int DIO_LIMIT_CLIMBER_FRONT = 2;
  public static final int DIO_LIMIT_CLIMBER_REAR = 3;
  public static final int DIO_LIMIT_CARGO = 4;
  public static final int DIO_LIMIT_HATCH = 5;

  //Ladder Encoder Constants   
  public static final int LADDER_LEVEL_ONE = 0; //The home location
  public static final int LADDER_LEVEL_VISION = 700;
  public static final int LADDER_LEVEL_CUSHION = 3000;
  public static final int LADDER_LEVEL_TWO = 3625; //change as needed
  public static final int LADDER_LEVEL_THREE = 7160; //change as needed
  //TODO - set these values
  public static final int LADDER_LEVEL_CARGO_INTAKE = 1980;
  public static final int LADDER_LEVEL_ROCKET_CARGO_VISION = 1900;

  // Climb Constants
  //Motor Speed: Positive if climbing forward - Negative if climbing backwards
  public static final double CLIMB_MOTORS_SPEED = 0.4;  //PostChange: 0.2 was very slow; try 0.4

    public static final int PCM_ID_SOLENOID_HATCHDRAWER = 6;
}

