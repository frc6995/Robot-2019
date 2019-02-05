/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CargoShooterS extends Subsystem {
  public WPI_TalonSRX shooterL;
  public WPI_TalonSRX shooterR;
  public CargoShooterS(){
    shooterL = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_SHOOTER_L);
    shooterR = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_SHOOTER_R);

    shooterR.set(ControlMode.Follower, RobotMap.CAN_ID_TALON_SHOOTER_L);
    shooterR.setInverted(InvertType.OpposeMaster);
  }
  @Override
  public void initDefaultCommand() {
  }
}
