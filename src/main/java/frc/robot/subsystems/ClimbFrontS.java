package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

// NOTE!!! The rear should be the side without the ladder, however, the side without the ladder should be going first.

public class ClimbFrontS extends Subsystem {
  
  private static Solenoid climbMechanismFront;
  private static DigitalInput climberFrontSwitch;

  public ClimbFrontS(){
    // With the solenoid in pcm port 2, the forward channel is 2.
    // The hatchmech in port 1 has a forward channel of 1.
    // PS, what channel is used when the PCM ID is 0???
    climbMechanismFront = new Solenoid(RobotMap.PCM_ID, RobotMap.PCM_ID_SOLENOID_CLIMBER_FRONT); //why does channel change?
    climberFrontSwitch = new DigitalInput(RobotMap.DIO_LIMIT_CLIMBER_FRONT);
  }

  @Override
  public void initDefaultCommand() {
  }

  public boolean cSwitchFront() {
    // returns the value of the front limit switch as a boolean. Used by the CG in the ClimbFrontLimitRetractC
    return climberFrontSwitch.get();
  }
  
  public void deployFront() {
    // The piston rod is pushed out.
    climbMechanismFront.set(true);
  }
  
  public void retractFront() {
    // the rod is sucked in.
    climbMechanismFront.set(false);
  }

}
