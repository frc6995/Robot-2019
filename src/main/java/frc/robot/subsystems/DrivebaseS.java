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
  
  private int drivebaseAmpLimit = 20;

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveArcadeXboxC());
  }

  public DrivebaseS() {
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
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
    //Rotation throttle disabled per driver request
    //Keep in mind for other usage of arcadeDrive
    differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed * 0.78);
    SmartDashboard.putNumber("Throttle", throttle);
    SmartDashboard.putNumber("Drivebase Speed", moveSpeed*throttle*10);
  }

  //visionDrive added for VisionAlign. It has no motor deadzones.
  public void visionDrive(double moveSpeed, double rotateSpeed) {
    driveLeftFront.set(moveSpeed + rotateSpeed);
    driveRightFront.set(moveSpeed - rotateSpeed);
    SmartDashboard.putNumber("Drivebase Speed", moveSpeed);
  }

  /**
   * conserserve power by current limiting to cont: 20V &
   * peak: 30V. If drastic, give only 10 volts.
   * 
   * @param drastic
   */
  public void lowPowerModeOn(boolean drastic) {
    driveLeftFront.configPeakCurrentLimit(30);
    driveLeftFront.configContinuousCurrentLimit(20);
    driveRightFront.configPeakCurrentLimit(30);
    driveRightFront.configContinuousCurrentLimit(20);

    if (drastic) {
      driveLeftFront.configVoltageCompSaturation(10);
      driveRightFront.configVoltageCompSaturation(10);
      driveLeftFront.enableVoltageCompensation(true);
      driveRightFront.enableVoltageCompensation(true);
    }
  }

  /**
   * Reset drivebase to power settings created when initialized.
   * (Continuous current: 20V or SD value)(Voltage: 12V)
   */
  public void lowPowerModeOff() {
    driveLeftFront.configContinuousCurrentLimit(drivebaseAmpLimit);
    driveRightFront.configContinuousCurrentLimit(drivebaseAmpLimit);
    //driveLeftFront.configVoltageCompSaturation(12);
    //driveRightFront.configVoltageCompSaturation(12);
    driveLeftFront.enableVoltageCompensation(false);
  }
}
