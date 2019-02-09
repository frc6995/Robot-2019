package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class DrivebaseS extends Subsystem {

  private WPI_TalonSRX driveLeftFront = null;
  private VictorSPX driveLeftMiddle = null;
  private VictorSPX driveLeftBack = null;
  private WPI_TalonSRX driveRightFront = null;
  private VictorSPX driveRightMiddle = null;
  private VictorSPX driveRightBack = null;

  private DifferentialDrive differentialDrive = null;
  
  @Override
  protected void initDefaultCommand() {
  }

  public DrivebaseS() {
    driveLeftFront = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT);
    driveLeftMiddle = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_1);
    driveLeftBack = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_2);
    driveRightFront = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT);
    driveRightMiddle = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_1);
    driveRightBack = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_2);

    driveLeftBack.set(ControlMode.Follower, RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT);
    driveLeftMiddle.set(ControlMode.Follower, RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT);
    driveRightBack.set(ControlMode.Follower, RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT);
    driveRightMiddle.set(ControlMode.Follower, RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT);
    differentialDrive = new DifferentialDrive(driveLeftFront, driveRightFront);
 
    driveLeftFront.setNeutralMode(NeutralMode.Brake);
    driveLeftMiddle.setNeutralMode(NeutralMode.Brake);
    driveLeftBack.setNeutralMode(NeutralMode.Brake);
    driveRightFront.setNeutralMode(NeutralMode.Brake);
    driveRightMiddle.setNeutralMode(NeutralMode.Brake);
    driveRightBack.setNeutralMode(NeutralMode.Brake);
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
    differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed * throttle);
  }
}
