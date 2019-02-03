/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HatchMechRectractC;

public class HatchMechS extends Subsystem {
  

  private static DoubleSolenoid hatchMechanism;

  public HatchMechS(){
    //Creates a new solenoid object
    hatchMechanism = new DoubleSolenoid(RobotMap.HATCHMECH_PCM_ID, RobotMap.HATCHMECH_FORWARD_CHANNEL, RobotMap.HATCHMECH_REVERSE_CHANNEL);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HatchMechRectractC());
  }
  
  //Retract the cylinders
  public void retract() {
    hatchMechanism.set(Value.kReverse);
  }

  //Extends the cylinders to push disk onto hatch
  public void deploy() {
    hatchMechanism.set(Value.kForward);
  }
}
