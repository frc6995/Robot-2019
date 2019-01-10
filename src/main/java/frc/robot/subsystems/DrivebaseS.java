
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Preferences;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;

public class DrivebaseS extends Subsystem {

  private TalonSRX driveLeftFront = null;
  private TalonSRX driveLeftBack = null;
  private TalonSRX driveRightFront = null;
  private TalonSRX driveRightBack = null;

  private DifferentialDrive differentialDrive = null;
  private SpeedControllerGroup leftSpeedControllerGroup = null;
  private SpeedControllerGroup rightSpeedControllerGroup = null;

  @Override
  public void initDefaultCommand() {

  }

  public DrivebaseS() {
    driveLeftFront = new TalonSRX(frc.robot.RobotMap.DRIVEBASE_LEFT_TALON_CAN_ID);
    driveLeftBack = new TalonSRX(frc.robot.RobotMap.DRIVEBASE_LEFTB_TALON_CAN_ID);
    driveRightFront = new TalonSRX(frc.robot.RobotMap.DRIVEBASE_RIGHT_TALON_CAN_ID);
    driveRightBack = new TalonSRX(frc.robot.RobotMap.DRIVEBASE_RIGHTB_TALON_CAN_ID);

    leftSpeedControllerGroup = new SpeedControllerGroup(driveLeftFront, driveLeftBack);
    rightSpeedControllerGroup = new SpeedControllerGroup(driveRightFront, driveRightBack);
    differentialDrive = new DifferentialDrive(leftSpeedControllerGroup, rightSpeedControllerGroup);
 
    driveLeftFront.setNeutralMode(NeutralMode.Brake);
    driveLeftBack.setNeutralMode(NeutralMode.Brake);
    driveRightFront.setNeutralMode(NeutralMode.Brake);
    driveRightBack.setNeutralMode(NeutralMode.Brake);

  }

public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
  differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed);
}

}
