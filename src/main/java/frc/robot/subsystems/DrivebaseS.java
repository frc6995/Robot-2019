package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DriveArcadeXboxC;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class DrivebaseS extends Subsystem {

  public AHRS navX;

  private static WPI_TalonSRX driveLeftFront = null;
  private static WPI_VictorSPX driveLeftMiddle = null;
  private static WPI_VictorSPX driveLeftBack = null;
  private static WPI_TalonSRX driveRightFront = null;
  private static WPI_VictorSPX driveRightMiddle = null;
  private static WPI_VictorSPX driveRightBack = null;
  
  private DifferentialDrive differentialDrive = null;
  
  private int drivebaseAmpLimit = 20;
  

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveArcadeXboxC());
  }

  public DrivebaseS() {

    navX = new AHRS(SPI.Port.kMXP);

    drivebaseAmpLimit = (int) SmartDashboard.getNumber("Amp Limit", 20);

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

    driveLeftFront.enableCurrentLimit(true);
    driveLeftFront.configContinuousCurrentLimit(drivebaseAmpLimit);
    driveLeftFront.configPeakCurrentDuration(0);

    driveRightFront.enableCurrentLimit(true);
    driveRightFront.configContinuousCurrentLimit(drivebaseAmpLimit);
    driveRightFront.configPeakCurrentDuration(0);

    differentialDrive.setRightSideInverted(false);

    //encoders
    driveRightFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    driveLeftFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    driveLeftFront.configSelectedFeedbackCoefficient(1.0);
    driveRightFront.configSelectedFeedbackCoefficient(1.0);

    int DBPIDSlot = 0;
    double DBkP = 0.45;
    double DBkI = 0.002;
    double DBkD = 10.0;
    double DBkF = 0;
    driveRightFront.config_kP(DBPIDSlot, DBkP);
    driveLeftFront.config_kP(DBPIDSlot, DBkP);
    driveRightFront.config_kI(DBPIDSlot, DBkI);
    driveLeftFront.config_kI(DBPIDSlot, DBkI);
    driveRightFront.config_kD(DBPIDSlot, DBkD);
    driveLeftFront.config_kD(DBPIDSlot, DBkD);
    driveRightFront.config_kF(DBPIDSlot, DBkF);
    driveLeftFront.config_kF(DBPIDSlot, DBkF);

    driveRightFront.config_IntegralZone(DBPIDSlot, 1800);
    driveLeftFront.config_IntegralZone(DBPIDSlot, 1800); //instead of 1500

    driveRightFront.configClosedLoopPeakOutput(DBPIDSlot, 0.6); //instead of 0.4
    driveLeftFront.configClosedLoopPeakOutput(DBPIDSlot, 0.6);

    driveRightFront.selectProfileSlot(DBPIDSlot, 0);
    driveLeftFront.selectProfileSlot(DBPIDSlot, 0);

    driveRightFront.set(ControlMode.PercentOutput, 0); //set to percent for now, until auto init.
    driveLeftFront.set(ControlMode.PercentOutput, 0);
  }

  public void resetNavXYaw() {
     navX.reset();
  }

  public double getNavXYaw() {
    return (navX.getAngle());
  }

  public void setNavXYaw(double setYaw){
    resetNavXYaw();
    navX.setAngleAdjustment(setYaw);
  }
    
  public double getDrivebaseLeftEncoderCount() {
    // Positive is up, negative is down
    return (driveLeftFront.getSensorCollection().getQuadraturePosition());
  }

  public double getDrivebaseRightEncoderCount() {
    // Positive is up, negative is down
    return (driveRightFront.getSensorCollection().getQuadraturePosition());
  }
  public void resetDrivebaseEncoders() {
    //resets both encoders
    driveLeftFront.getSensorCollection().setQuadraturePosition(3, 500);
    driveLeftFront.setSelectedSensorPosition(0);
    driveRightFront.getSensorCollection().setQuadraturePosition(3, 500);
    driveRightFront.setSelectedSensorPosition(0);
  }


  public void setCMtoPercent() {
    driveRightFront.set(ControlMode.PercentOutput, 0); //set to percent for now, until auto init.
    driveLeftFront.set(ControlMode.PercentOutput, 0);
  }


  public static void setAutoVelocity(double leftDriveSignal, double rightDriveSignal) {
		driveRightFront.set(ControlMode.Velocity, rightDriveSignal);
		driveLeftFront.set(ControlMode.Velocity, leftDriveSignal);
	}

  public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
    //Rotation throttle disabled per driver request
    //Keep in mind for other usage of arcadeDrive
    differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed * 0.78);
    SmartDashboard.putNumber("Throttle", throttle);
  }

  //visionDrive added for VisionAlign. It has no motor deadzones.
  public void visionDrive(double moveSpeed, double rotateSpeed) {
    driveLeftFront.set(moveSpeed + rotateSpeed);
    driveRightFront.set(moveSpeed - rotateSpeed);
  }

public void selectPIDF(int velocityslotidx, double[] rightvelocitypidf, double[] leftvelocitypidf) {
}
}
