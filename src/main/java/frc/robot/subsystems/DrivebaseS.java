
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DrivebaseS extends Subsystem {

  private WPI_TalonSRX driveLeftFront = null;
  private WPI_TalonSRX driveLeftBack = null;
  private WPI_TalonSRX driveRightFront = null;
  private WPI_TalonSRX driveRightBack = null;

  private DifferentialDrive differentialDrive = null;
  
  @Override
  protected void initDefaultCommand() {
  }

  public DrivebaseS() {
    driveLeftFront = new WPI_TalonSRX(frc.robot.RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT_F);
    driveLeftBack = new WPI_TalonSRX(frc.robot.RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT_B);
    driveRightFront = new WPI_TalonSRX(frc.robot.RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT_F);
    driveRightBack = new WPI_TalonSRX(frc.robot.RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT_B);

    driveLeftBack.set(ControlMode.Follower, frc.robot.RobotMap.CAN_ID_TALON_DRIVEBASE_LEFT_F);
    driveRightBack.set(ControlMode.Follower, frc.robot.RobotMap.CAN_ID_TALON_DRIVEBASE_RIGHT_F);
    differentialDrive = new DifferentialDrive(driveLeftFront, driveRightFront);
 
    driveLeftFront.setNeutralMode(NeutralMode.Brake);
    driveLeftBack.setNeutralMode(NeutralMode.Brake);
    driveRightFront.setNeutralMode(NeutralMode.Brake);
    driveRightBack.setNeutralMode(NeutralMode.Brake);
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
    differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed * throttle);
  }
}
