package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HatchMechRetractC;

public class HatchMechS extends Subsystem {

  private static DoubleSolenoid hatchMechanism;

  public HatchMechS(){
    //Creates a new double solenoid object
    hatchMechanism = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_HATCHMECH, RobotMap.PCM_CONSTANT_HATCHMECH_FORWARD_CHANNEL, RobotMap.PCM_CONSTANT_HATCHMECH_REVERSE_CHANNEL);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HatchMechRetractC());
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