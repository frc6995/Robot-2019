package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.lang.reflect.Array;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants;
import frc.robot.Robot;
import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.sun.nio.sctp.SendFailedNotification;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DriveArcadeXboxC;

public class DrivebaseS extends Subsystem {

	public static WPI_TalonSRX driveLeftFront = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT);
	public static WPI_TalonSRX driveRightFront = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT);
	
	private static VictorSPX driveLeftMiddle = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_1);
	private static VictorSPX driveRightMiddle = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_1);
	private static VictorSPX driveLeftBack = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_2);
	private static VictorSPX driveRightBack = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_2);

	
	public static int leftEncoder, rightEncoder;

  public static double supposedAngle;

	public static DifferentialDrive differentialDrive = new DifferentialDrive(driveLeftFront, driveRightFront);
	
	public static boolean initialized = false;
  	private static int drivebaseAmpLimit = 20;
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

  public static void init()
	{
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

		driveRightFront.configContinuousCurrentLimit(drivebaseAmpLimit);
		driveRightFront.configPeakCurrentDuration(0);
		driveRightFront.enableCurrentLimit(false);

		differentialDrive.setRightSideInverted(false);

		
		// resetEncoders();

		setToBrake();

		initialized = true;
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

	/**
	 * Method to control robot
	 * @param xValue joystick x value
	 * @param yValue joystick y value
	 * @param gyro gyro object
	 * @param quickTurn can the robot turn on itself
	 * @param scaleInputs 60% of input is used for forward and 65% for turn if true
	 */
  public static void customArcadeDrive(double xValue, double yValue, boolean quickTurn, boolean scaleInputs)
	{
		if(scaleInputs)
		{
			xValue *= .65;
			yValue *= .6;
		}

		differentialDrive.curvatureDrive(yValue, xValue, quickTurn);
	}

	public static void updateEncoders()
	{
		leftEncoder = driveLeftFront.getSelectedSensorPosition(0);
		rightEncoder = driveRightFront.getSelectedSensorPosition(0);
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

		//-- -- -- Methods for testing max velocity and acceleration -- -- -- //
	// 	// Resets all values needed for testing max velocity and acceleration
	// 	/**
	// 	 * This method is required to run before running {@link #VelAccel()} , it resets all the values, the timer object and the encoders
	// 	 * 
	// 	 */
	private static double prevLeftVelocity, prevRightVelocity;
	private static double maxLeftVelocity, maxRightVelocity;

	private static double prevLeftAccel, prevRightAccel;
	private static double maxLeftAccel, maxRightAccel;

	// private static double prevRightJerk, prevLeftJerk;
	private static double maxRightJerk, maxLeftJerk;
	private static Timer maxVelAccelTimer;
	public static void initializeVelAccel()
	{
		maxVelAccelTimer = new Timer();
		// Resets timer to make the programs run for 2sec
		maxVelAccelTimer.reset();
		maxVelAccelTimer.start();
		resetEncoders();

		// Velocity variables initialization (and reset)
		//Used to get the acceleration of left Motor with the current velocity. (derivative)
		prevLeftVelocity = 0;
		//Used to get the acceleration of right Motor with the current velocity. (derivative)
		prevRightVelocity = 0;
		
		// Will store the max retrieved velocity at every point while the velAccel() method runs
		maxLeftVelocity = 0;
		maxRightVelocity = 0;

		//Acceleration variables initialization (and reset)
		// prev Accel isn't currently used
		prevLeftAccel = 0;
		prevRightAccel = 0;
		
		// Will store the max calculated acceleration at every point while the velAccel() method runs
		maxLeftAccel = 0;
		maxRightAccel = 0;

		// prevLeftJerk = 0;
		// prevRightJerk = 0;

		maxRightJerk = 0;
		maxLeftJerk = 0;
	}

	// Puts velocity on the smart dashboard
	public static void initializeSmartDashboardVelAccel()
	{
		SmartDashboard.putNumber("lMaxVelocity", 0);
		SmartDashboard.putNumber("lCurrentVelocity", 0);
		SmartDashboard.putNumber("rMaxVelocity", 0);
		SmartDashboard.putNumber("rCurrentVelocity", 0);


		SmartDashboard.putNumber("lMaxAccel", 0);
		SmartDashboard.putNumber("lCurrentAccel", 0);
		SmartDashboard.putNumber("rMaxAccel", 0);
		SmartDashboard.putNumber("rCurrentAccel", 0);
		SmartDashboard.putNumber("encoderValue", 0);

		// Displays current and max acceleration values on smartdashboard
		SmartDashboard.putNumber("lMaxJerk", 0);
		SmartDashboard.putNumber("lCurrentJerk", 0);
		SmartDashboard.putNumber("rMaxJerk", 0);
		SmartDashboard.putNumber("rCurrentJerk", 0);
	}
	// Converting encoder ticks per 100ms to meters per second (m/s)
	// Takes sensors encoder ticks per .1second (100ms) and wheel radius to get velocity for left and right motors
	private static double encoderToMpS(double encoderVal, double wheelRadius)
	{
		// Makes the encoder absolute value in case the motors are backwards or not set inverted
		return Math.abs(encoderVal) / .1  / 4096.0 * .0254 * wheelRadius * Math.PI * 2;
	}

	// Running the motors at full speed in order to test max velocity and acceleration
	private static void runMotorsMax()
	{
		driveLeftFront.set(ControlMode.Velocity, 3250);
		driveRightFront.set(ControlMode.Velocity, 3250);
	}
	/**
	 * A method that calculates max acceleration and velocity for robot, requires running {@link #initializeVelAccel()} , as well
	 * <p>
	 * The method uses getSelectedSensorVelocity(0) an SRX method that gives encoder ticks / 100ms, it then converts to 
	 * meters/second and uses conditional to determine max velocity.
	 * </p>
	 * 
	 * <p>
	 * To get max acceleration the method subtracts previous velocity from current velocity and divides by .02s, the time 
	 * between every report of velocity from the motor controller. It skips the first 20ms when calculating max acceleration
	 * in order to ignore the first "jolt" of the robot and get more accurate values.
	 * </p>
	 * 
	 */
	public static void velAccel()
	{
		// Runs motors in full power
		runMotorsMax();

		// Store current velocity for left motor in meters/second, 3 is wheel radius in inches
		double leftMpS = encoderToMpS(driveLeftFront.getSelectedSensorVelocity(0), 3);
		// Store current velocity for right motor in meters/second, 3 is wheel radius in inches
		double rightMpS = encoderToMpS(driveRightFront.getSelectedSensorVelocity(0), 3);
				
		// Compare current velocity to max velocity recoreded
		if(leftMpS > maxLeftVelocity)
		{
				// If current left velocity is bigger than max, set max to current
				maxLeftVelocity = leftMpS;
		}
		if(rightMpS > maxRightVelocity)
		{
				// Same for right motor
				maxRightVelocity = rightMpS;
		}

		// Initializes the left acceleration and right acceleration variables as 0
		double leftAccel = 0;
		double rightAccel = 0;

		double rightJerk = 0;
		double leftJerk = 0;

		/*
		* In order to ignore the first 20ms of acceleration, the "jolt" at the start and get more accurate acceleration
		* the program checks that the timer is after 20ms and then calculates acceleration
		*/
		if(maxVelAccelTimer.get()>.02)
		{
			// Gets left motor acceleration by subtracting previous velocity from current and divide by .02s (the time between every report for velocity)
			leftAccel = (leftMpS - prevLeftVelocity) / .02;
			// Same for right motor
			rightAccel = (rightMpS - prevRightVelocity) / .02;

			leftJerk = (leftAccel - prevLeftAccel) / .02;
			rightJerk = (rightAccel - prevRightAccel) / .02;
			// Compare previous acceleration to current acceleration
			if(leftAccel > maxLeftAccel)
			{
				// If current bigger than max, set max to current
				maxLeftAccel = leftAccel;
			}
			if(rightAccel > maxRightAccel)
			{
				// If current bigger than max, set max to current
				maxRightAccel = rightAccel;
			}

			if(leftJerk > maxLeftJerk)
			{
				maxLeftJerk = leftJerk;
			}
			if(rightJerk > maxRightJerk)
			{
				maxRightJerk = rightJerk;
			}
			
		}
				// Displays current and max velocity values on smartdashboard
				SmartDashboard.putNumber("lMaxVelocity", maxLeftVelocity);
				SmartDashboard.putNumber("lCurrentVelocity", leftMpS);
				SmartDashboard.putNumber("rMaxVelocity", maxRightVelocity);
				SmartDashboard.putNumber("rCurrentVelocity", rightMpS);



				// Displays current and max acceleration values on smartdashboard
				SmartDashboard.putNumber("lMaxAccel", maxLeftAccel);
				SmartDashboard.putNumber("lCurrentAccel", leftAccel);
				SmartDashboard.putNumber("rMaxAccel", maxRightAccel);
				SmartDashboard.putNumber("rCurrentAccel", rightAccel);

				// Displays current and max acceleration values on smartdashboard
				SmartDashboard.putNumber("lMaxJerk", maxLeftJerk);
				SmartDashboard.putNumber("lCurrentJerk", leftJerk);
				SmartDashboard.putNumber("rMaxJerk", maxRightJerk);
				SmartDashboard.putNumber("rCurrentJerk", rightJerk);

				// Sets previous velocity to current velocity before method ends
				prevLeftVelocity = leftMpS;
				prevRightVelocity = rightMpS;

				// Sets previous acceleraion to current acceleraion before method ends
				prevLeftAccel = leftAccel;
				prevRightAccel = rightAccel;		
	}

	// Getters for max velocity, left and right
	public static double getMaxLeftVelocity()
	{
		return maxLeftVelocity;
	}
	public static double getMaxRightVelocity()
	{
		return maxRightVelocity;
	}

	// Getters for max acceleration, left and right
	public static double getMaxLeftAccel()
	{
		return maxLeftAccel;
	}

	public static double getMaxRightAccel()
	{
		return maxRightAccel;
	}

	// 	// -- -- -- End of methods to test max velocity and acceleration -- -- -- //

}
}
