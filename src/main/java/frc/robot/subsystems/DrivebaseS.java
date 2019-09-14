package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.lang.reflect.Array;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.sun.nio.sctp.SendFailedNotification;
import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.SerialPort;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DriveArcadeXboxC;

public class DrivebaseS extends Subsystem {

  public WPI_TalonSRX driveLeftFront = null;
  private WPI_VictorSPX driveLeftMiddle = null;
  private WPI_VictorSPX driveLeftBack = null;
  public WPI_TalonSRX driveRightFront = null;
  private WPI_VictorSPX driveRightMiddle = null;
  private WPI_VictorSPX driveRightBack = null;
  public AHRS navX = null;  
  private DifferentialDrive differentialDrive = null;
  
  private int drivebaseAmpLimit = 20;
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

  public DrivebaseS() {
    //drivebaseAmpLimit = (int) SmartDashboard.getNumber("Amp Limit", 20);

    //driveChooser = new SendableChooser<driverConstants>();
    //driveChooser.addOption("Tom", driverArray[0]);
    //driveChooser.addOption("Elijah", driverArray[1]);
    navX = new AHRS(SerialPort.Port.kMXP);
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

    differentialDrive.setRightSideInverted(true);

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
    throttle = 1;
    differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed * rotThrot);
    SmartDashboard.putNumber("Throttle", throttle);
  }

  //visionDrive added for VisionAlign. It has no motor deadzones.
  public void visionDrive(double moveSpeed, double rotateSpeed) {
    driveLeftFront.set(moveSpeed + rotateSpeed);
    driveRightFront.set(moveSpeed - rotateSpeed);
  }
  //Tank drive method for autonomous
  public void autoDrive(double leftSpeed, double rightSpeed) {
    driveLeftFront.set(leftSpeed);
    driveRightFront.set(rightSpeed);
  }

  public int getLeftEncoder() {
    return driveLeftFront.getSelectedSensorPosition();
  }

  public int getRightEncoder() {
    return driveRightFront.getSelectedSensorPosition();
  }
}
