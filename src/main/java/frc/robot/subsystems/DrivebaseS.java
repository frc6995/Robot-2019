package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DriveArcadeXboxC;

public class DrivebaseS extends Subsystem {

  private WPI_TalonSRX driveLeftFront = null;
  private WPI_VictorSPX driveLeftMiddle = null;
  private WPI_VictorSPX driveLeftBack = null;
  private WPI_TalonSRX driveRightFront = null;
  private WPI_VictorSPX driveRightMiddle = null;
  private WPI_VictorSPX driveRightBack = null;

  private DifferentialDrive differentialDrive = null;
  
  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveArcadeXboxC());
  }

  public DrivebaseS() {
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
    driveLeftFront.configContinuousCurrentLimit(30);
    driveLeftFront.configPeakCurrentDuration(0);

    driveRightFront.enableCurrentLimit(true);
    driveRightFront.configContinuousCurrentLimit(30);
    driveRightFront.configPeakCurrentDuration(0);

    differentialDrive.setRightSideInverted(false);
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
    differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed * 0.65);
    //Rotation throttle disabled, uses xbox joystick X to determine speed
    //Keep in mind for other usage of arcadeDrive
    SmartDashboard.putNumber("Throttle", throttle);
  }

  //visionDrive added for VisionAlign. It has no motor deadzones.
  public void visionDrive(double moveSpeed, double rotateSpeed) {
    driveLeftFront.set(moveSpeed + rotateSpeed);
    driveRightFront.set(moveSpeed - rotateSpeed);
  }
}
