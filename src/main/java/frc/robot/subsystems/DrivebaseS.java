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
    driveLeftFront = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT_F);
    driveLeftMiddle = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_M);
    driveLeftBack = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_LEFT_B);
    driveRightFront = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT_F);
    driveRightMiddle = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_M);
    driveRightBack = new VictorSPX(RobotMap.CAN_ID_VSPX_DRIVEBASE_RIGHT_B);

    driveLeftBack.set(ControlMode.Follower, RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT_F);
    driveLeftMiddle.set(ControlMode.Follower, RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT_F);
    driveRightBack.set(ControlMode.Follower, RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT_F);
    driveRightMiddle.set(ControlMode.Follower, RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT_F);
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
