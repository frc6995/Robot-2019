package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class ClimbFrontS extends Subsystem {
  private static DoubleSolenoid climbMechanismFront;
  private static DigitalInput climberFrontSwitch;
  public boolean extended;

  public ClimbFrontS(){
    climbMechanismFront = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMBER_FRONT, 2, 0); //why does channel change?
    climberFrontSwitch = new DigitalInput(RobotMap.DIO_LIMIT_CLIMBER_FRONT);
    extended = false;
  }

  @Override
  public void initDefaultCommand() {
  }

  public boolean cSwitchFront() {
    return climberFrontSwitch.get(); //returns the front limit switch
  }
  
  public void deployFront() {
    climbMechanismFront.set(Value.kForward);
  }
  
  public void retractFront() {
    climbMechanismFront.set(Value.kReverse);
  }

}
