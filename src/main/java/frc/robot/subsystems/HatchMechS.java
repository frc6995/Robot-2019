package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
//import frc.robot.commands.hatch.HatchMechRetractC;

public class HatchMechS extends Subsystem {
  
  private static Solenoid hatchMechanism;

  public HatchMechS(){
    hatchMechanism = new Solenoid(RobotMap.PCM_ID, RobotMap.PCM_ID_SOLENOID_HATCHMECH);
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new HatchMechRetractC());
  }
  
  public void retract() {
    hatchMechanism.set(false);
  }

  //Extends the cylinders to push disk onto hatch
  public void deploy() {
    hatchMechanism.set(true);
  }
}