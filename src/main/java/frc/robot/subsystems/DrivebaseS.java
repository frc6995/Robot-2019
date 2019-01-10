
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team6995.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class DrivebaseS extends Subsystem {

  private TalonSRX driveLeftFront = null;
  private TalonSRX driveLeftBack = null;
  private TalonSRX driveRightFront = null;
  private TalonSRX driveRightBack = null;

  Preferences prefs = new Preferences;

  private DifferentialDrive differentialDrive = null;
  private SpeedControllerGroup leftSpeedControllerGroup = null;
  private SpeedControllerGroup rightSpeedControllerGroup = null;

  @Override
  public void initDefaultCommand() {

  }

  public DrivebaseS() {
    driveLeftFront = new TalonSRX(RobotMap.DRIVEBASE_LEFT_TALON_CAN_ID);
    driveLeftBack = new TalonSRX(RobotMap.DRIVEBASE_LEFTB_TALON_CAN_ID);
    driveRightFront = new TalonSRX(RobotMap.DRIVEBASE_RIGHT_TALON_CAN_ID);
    driveRightBack = new TalonSRX(RobotMap.DRIVEBASE_RIGHTB_TALON_CAN_ID);

    leftSpeedControllerGroup = new SpeedControllerGroup(driveLeftFront, driveLeftBack);
    rightSpeedControllerGroup = new SpeedControllerGroup(driveRightFront, driveRightBack);
    differentialDrive = new DifferentialDrive(leftSpeedControllerGroup, rightSpeedControllerGroup);

    driveLeftFront.setNeutralMode(NeutralMode.Brake);
    driveLeftBack.setNeutralMode(NeutralMode.Brake);
    driveRightFront.setNeutralMode(NeutralMode.Brake);
    driveRightBack.setNeutralMode(NeutralMode.Brake);

    driveLeftFront.setSafetyEnabled(false);
    driveLeftBack.setSafetyEnabled(false);
    driveRightFront.setSafetyEnabled(false);
    driveRightBack.setSafetyEnabled(false);
  }

public void arcadeDrive(double moveSpeed, double rotateSpeed, double throttle) {
  differentialDrive.arcadeDrive(moveSpeed * throttle, rotateSpeed);
}

}
