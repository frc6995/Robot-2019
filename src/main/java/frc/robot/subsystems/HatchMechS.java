package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.hatch.HatchMechRetractC;

public class HatchMechS extends Subsystem {
  
  private static Solenoid hatchMechanism;

  public HatchMechS(){
    //Creates a new double solenoid object
    hatchMechanism = new Solenoid(1, 1);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HatchMechRetractC());
  }
  
  //Retract the cylinders
  public void retract() {
    hatchMechanism.set(false);
  }

  //Extends the cylinders to push disk onto hatch
  public void deploy() {
    hatchMechanism.set(true);
  }
}