package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.lang.reflect.Array;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.sun.nio.sctp.SendFailedNotification;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.autonomous.Constants;
import frc.robot.commands.drive.DriveArcadeXboxC;

public class DrivebaseS extends Subsystem {

  private static WPI_TalonSRX driveLeftFront = null;
  private static WPI_VictorSPX driveLeftMiddle = null;
  private static WPI_VictorSPX driveLeftBack = null;
  private static WPI_TalonSRX driveRightFront = null;
  private static WPI_VictorSPX driveRightMiddle = null;
  private static WPI_VictorSPX driveRightBack = null;
  private static AHRS navX = null;
  
  private static DifferentialDrive differentialDrive = null;
  
  private static int drivebaseAmpLimit = 20;
  public static double supposedAngle;
  //public class driverConstants {
//
  //  public String driverName;
  //  public double rotThrotConst;

  //}

  //public SendableChooser<driverConstants> driveChooser;

  //public double[] rotThrotConst = new double[1];
  //public String[] rotThrotPeople = new String[1];

  //public driverConstants[] driverArray;

  public double rotThrot = 0.68;

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveArcadeXboxC());
  }

  public void init() {
    //drivebaseAmpLimit = (int) SmartDashboard.getNumber("Amp Limit", 20);

    //driveChooser = new SendableChooser<driverConstants>();
    //driveChooser.addOption("Tom", driverArray[0]);
    //driveChooser.addOption("Elijah", driverArray[1]);
    
    driveLeftFront = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT);
    driveLeftMiddle = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_1);
    driveLeftBack = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_2);
    driveRightFront = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT);
    driveRightMiddle = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_1);
    driveRightBack = new WPI_VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_2);

    differentialDrive = new DifferentialDrive(driveLeftFront, driveRightFront);

    driveLeftBack.configFactoryDefault();
    driveLeftFront.configFactoryDefault();
    driveLeftMiddle.configFactoryDefault();
    driveRightBack.configFactoryDefault();
    driveRightFront.configFactoryDefault();
    driveRightMiddle.configFactoryDefault();

    driveLeftMiddle.follow(driveLeftFront);
    driveLeftBack.follow(driveLeftFront);
    driveRightBack.follow(driveRightFront);
    driveRightMiddle.follow(driveRightFront);

    driveLeftFront.setInverted(false);
    driveLeftBack.setInverted(InvertType.FollowMaster);
    driveLeftMiddle.setInverted(InvertType.FollowMaster);
    driveRightFront.setInverted(true);
    driveRightBack.setInverted(InvertType.FollowMaster);
    driveRightMiddle.setInverted(InvertType.FollowMaster);

    driveLeftFront.setNeutralMode(NeutralMode.Brake);
    driveLeftMiddle.setNeutralMode(NeutralMode.Brake);
    driveLeftBack.setNeutralMode(NeutralMode.Brake);
    driveRightFront.setNeutralMode(NeutralMode.Brake);
    driveRightMiddle.setNeutralMode(NeutralMode.Brake);
    driveRightBack.setNeutralMode(NeutralMode.Brake);

    driveLeftFront.configContinuousCurrentLimit(drivebaseAmpLimit);
    driveLeftFront.configPeakCurrentDuration(0);
    driveLeftFront.enableCurrentLimit(false);
    driveLeftFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    driveRightFront.configContinuousCurrentLimit(drivebaseAmpLimit);
    driveRightFront.configPeakCurrentDuration(0);
    driveRightFront.enableCurrentLimit(false);
    driveRightFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    differentialDrive.setRightSideInverted(false);
    
    //rotThrotConst[0] = 7.8;
    //rotThrotConst[1] = 6.8;

    //driverArray[0].driverName = "Tom";
    //driverArray[0].rotThrotConst = 0.68;
    //driverArray[1].driverName = "Elijah";
    //driverArray[1].rotThrotConst = 0.78;
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
    //Rotation throttle disabled per driver request
    //Keep in mind for other usage of arcadeDrive
    differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed * rotThrot);
    SmartDashboard.putNumber("Throttle", throttle);
  }

  //visionDrive added for VisionAlign. It has no motor deadzones.
  public void visionDrive(double moveSpeed, double rotateSpeed) {
    driveLeftFront.set(moveSpeed + rotateSpeed);
    driveRightFront.set(moveSpeed - rotateSpeed);
  }

  public static void selectPIDF(int slot, double[] right, double[] left)
	{
		//PID SLOT
		driveRightFront.selectProfileSlot(slot, 0);
		driveLeftFront.selectProfileSlot(slot, 0);

		//PID
		driveRightFront.config_kP(slot, right[0], Constants.kTimeoutMs);		
		driveRightFront.config_kI(slot, right[1], Constants.kTimeoutMs);	
		driveRightFront.config_kD(slot, right[2], Constants.kTimeoutMs);
		driveRightFront.config_kF(slot, right[3], Constants.kTimeoutMs);

		driveLeftFront.config_kP(slot, left[0], Constants.kTimeoutMs);		
		driveLeftFront.config_kI(slot, left[1], Constants.kTimeoutMs);	
		driveLeftFront.config_kD(slot, left[2], Constants.kTimeoutMs);
		driveLeftFront.config_kF(slot, left[3], Constants.kTimeoutMs);
	}
  public static void setPercentOutput(double lOutput, double rOutput)
	{
		driveRightFront.set(ControlMode.PercentOutput, rOutput);
		driveLeftFront.set(ControlMode.PercentOutput, lOutput);
	}

	public static void setPercentOutput(double lOutput, double rOutput, boolean scaleInputs)
	{
		if (scaleInputs)
		{
			rOutput *= .6;
			lOutput *= .6;
		}

		driveRightFront.set(ControlMode.PercentOutput, rOutput);
		driveLeftFront.set(ControlMode.PercentOutput, lOutput);
	}

	
	public static void stop()
	{
		driveRightFront.stopMotor();
		driveLeftFront.stopMotor();
	}
	
	public static void velocityDrive(double xValue, double yValue, AHRS gyro)
	{
		selectPIDF(Constants.velocitySlotIdx, Constants.rightVelocityPIDF, Constants.leftVelocityPIDF);
		double threshold = 0.09;
		if(yValue != 0 && Math.abs(xValue) < threshold)
        {
			setVelocity(yValue, yValue);
	 	}
		else if(yValue == 0 && Math.abs(xValue) < threshold)
		{
			resetEncoders();
			gyro.reset();
			stop();
			supposedAngle = gyro.getYaw();
		}
		else
		{
			gyro.reset();
			curvatureDrive(xValue, yValue);
			supposedAngle = gyro.getYaw();
		}
	}

	private static void curvatureDrive(double throttle, double turn)
	{
		try
		{
			differentialDrive.curvatureDrive(throttle, turn, turn < .15);	//curvature drive from WPILIB libraries.
		}
		catch(NullPointerException e)
		{
			System.out.println(e);
			System.out.println("differential drive not initialized\nCreating new DifferentialDrive object");
			differentialDrive = new DifferentialDrive(driveLeftFront, driveRightFront);
		}
	}

	//USED BY AUTO FOR SOME REASON
  public static void setVelocity(double lSpeed, double rSpeed)
	{
		double targetVelocityRight = rSpeed * Constants.velocityConstant;
		double targetVelocityLeft = lSpeed * Constants.velocityConstant;
		
		driveRightFront.set(ControlMode.Velocity, targetVelocityRight);
		driveLeftFront.set(ControlMode.Velocity, targetVelocityLeft);
	}

	public static void setAutoVelocity(double leftDriveSignal, double rightDriveSignal)
	{
		driveRightFront.set(ControlMode.Velocity, rightDriveSignal);
		driveLeftFront.set(ControlMode.Velocity, leftDriveSignal);
	}

	public static void testDrivetrainCurrent()
	{
		System.out.println("Left Motor Current: " + driveLeftFront.getOutputCurrent());
		System.out.println("Right Motor Current:" + driveRightFront.getOutputCurrent());
	}

	public static void printEncoders()
	{
		System.out.println("Left Encoder: " + driveLeftFront.getSelectedSensorPosition());
		System.out.println("Right Encoder:" + driveRightFront.getSelectedSensorPosition());
	}

	public static void printVelocity()
	{
		System.out.println("Left Vel: " + driveLeftFront.getSelectedSensorVelocity());
		System.out.println("Right Vel:" + driveRightFront.getSelectedSensorVelocity());
	}

	public static int prevVelR = 0, prevVelL = 0;
	public static void printAccel()
	{
		int currentVelR = driveRightFront.getSelectedSensorVelocity();
		int currentVelL = driveLeftFront.getSelectedSensorVelocity();

		System.out.println("Left accel: " + (currentVelL - prevVelL) / .02);
		System.out.println("Right Vel:" + (currentVelR - prevVelR) / .02);

		prevVelR = driveRightFront.getSelectedSensorVelocity();
		prevVelL = driveLeftFront.getSelectedSensorVelocity();
	}

	public static void printVelError()
	{
		int velErrorR = driveRightFront.getClosedLoopError();
		int velErrorL = driveLeftFront.getClosedLoopError();
		System.out.println("Right Vel Error: " + velErrorR);
		System.out.println("Left Vel Error: " + velErrorL);
	}
	public static void enableCurrentLimiting(double amps)
	{
		driveLeftFront.enableCurrentLimit(true);
		driveRightFront.enableCurrentLimit(true);
	}
	
	public static void setToBrake()
	{
		driveLeftFront.setNeutralMode(NeutralMode.Brake);
		driveRightFront.setNeutralMode(NeutralMode.Brake);
		driveLeftMiddle.setNeutralMode(NeutralMode.Brake);
		driveLeftBack.setNeutralMode(NeutralMode.Brake);
		driveRightMiddle.setNeutralMode(NeutralMode.Brake);
		driveRightBack.setNeutralMode(NeutralMode.Brake);
	}
	
	public static void setToCoast()
	{
		driveLeftFront.setNeutralMode(NeutralMode.Coast);
		driveRightFront.setNeutralMode(NeutralMode.Coast);
		driveLeftMiddle.setNeutralMode(NeutralMode.Coast);
		driveLeftBack.setNeutralMode(NeutralMode.Coast);
		driveRightMiddle.setNeutralMode(NeutralMode.Coast);
		driveRightBack.setNeutralMode(NeutralMode.Coast);
    }
    


	public static void resetEncoders()
	{
		driveLeftFront.setSelectedSensorPosition(0);
		driveRightFront.setSelectedSensorPosition(0);
	}

	public static void setEncoders(int leftVal, int rightVal)
	{
		driveLeftFront.setSelectedSensorPosition(leftVal);
		driveRightFront.setSelectedSensorPosition(rightVal);
	}
}
