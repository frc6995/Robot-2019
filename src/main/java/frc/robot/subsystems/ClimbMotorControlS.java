/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotMap;

public class ClimbMotorControlS extends Subsystem {
  public Spark climbMotor = null;

  public ClimbMotorControlS() {
    climbMotor = new Spark(RobotMap.PCM_ID_CLIMBER_SPARK);
  }

  public void motorForwards() {
    climbMotor.set(0.5);
  }

  public void motorStop() {
    climbMotor.set(0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
