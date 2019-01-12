
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class DrivebaseS extends Subsystem {

  private WPI_TalonSRX driveLeftFront = null;
  private WPI_TalonSRX driveLeftBack = null;
  private WPI_TalonSRX driveRightFront = null;
  private WPI_TalonSRX driveRightBack = null;


  private DifferentialDrive differentialDrive = null;
  private SpeedControllerGroup leftSpeedControllerGroup = null;
  private SpeedControllerGroup rightSpeedControllerGroup = null;

  @Override
  public void initDefaultCommand() {

  }

  public DrivebaseS() {
    driveLeftFront = new WPI_TalonSRX(frc.robot.RobotMap.DRIVEBASE_LEFT_TALON_CAN_ID);
    driveLeftBack = new WPI_TalonSRX(frc.robot.RobotMap.DRIVEBASE_LEFTB_TALON_CAN_ID);
    driveRightFront = new WPI_TalonSRX(frc.robot.RobotMap.DRIVEBASE_RIGHT_TALON_CAN_ID);
    driveRightBack = new WPI_TalonSRX(frc.robot.RobotMap.DRIVEBASE_RIGHTB_TALON_CAN_ID);

    driveLeftBack.set(ControlMode.Follower, frc.robot.RobotMap.DRIVEBASE_LEFT_TALON_CAN_ID);
    driveRightBack.set(ControlMode.Follower, frc.robot.RobotMap.DRIVEBASE_RIGHT_TALON_CAN_ID);
    //leftSpeedControllerGroup = new SpeedControllerGroup(driveLeftFront, driveLeftBack);
    //rightSpeedControllerGroup = new SpeedControllerGroup(driveRightFront, driveRightBack);
    differentialDrive = new DifferentialDrive(driveLeftFront, driveRightFront);
 
    driveLeftFront.setNeutralMode(NeutralMode.Brake);
    driveLeftBack.setNeutralMode(NeutralMode.Brake);
    driveRightFront.setNeutralMode(NeutralMode.Brake);
    driveRightBack.setNeutralMode(NeutralMode.Brake);

  }

public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
  differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed);
}

}
