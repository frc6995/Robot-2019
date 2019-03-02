package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

//To change to double Solenoids make changes in ClimbFrontS, ClimbRearS, and the test toggles (NC)

// NOTE!!! The rear should be the side without the ladder, however, the side without the ladder should be going first.

public class ClimbFrontS extends Subsystem {
  
  private static Solenoid climbMechanismFront;
  //private static DoubleSolenoid climbMechanismFront;
  private static DigitalInput climberFrontSwitch;
  private boolean extended;
  //private Value Extended;

  public ClimbFrontS(){
    // With the solenoid in pcm port 2, the forward channel is 2.
    climbMechanismFront = new Solenoid(RobotMap.PCM_ID, RobotMap.PCM_ID_SOLENOID_CLIMBER_FRONT);
    //climbMechanismFront = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.PCM_ID_DSOLENOID_CLIMBER_FRONT_FORWARD, RobotMap.PCM_ID_DSOLENOID_CLIMBER_FRONT_REVERSE);
    climberFrontSwitch = new DigitalInput(RobotMap.DIO_LIMIT_CLIMBER_FRONT);
    this.extended = false;
    //this.Extended = Value.kReverse; //It defaulted to this during testing on 2/25/19
  }

  @Override
  public void initDefaultCommand() {
  }

  public boolean cSwitchFront() {
    // returns the value of the front limit switch as a boolean. Used by the CG in the ClimbFrontLimitRetractC
    SmartDashboard.putBoolean("Limit Front", climberFrontSwitch.get());
    return !climberFrontSwitch.get();
  }
  
  public void deployFront() {
    // The piston rod is pushed out.
    System.out.println("deployFront");
    climbMechanismFront.set(true);
    //climbMechanismFront.set(Value.kForward);
    this.extended = true;
    //this.Extended = Value.kForward;
  }
  
  public void retractFront() {
    System.out.println("retractFront");
    // the rod is sucked in.
    climbMechanismFront.set(false);
    //climbMechanismFront.set(Value.kReverse);
    this.extended = false;
    //this.Extended = Value.kReverse;
  }

/*   public void offFront() {
    System.out.println("offFront");
    climbMechanismFront.set(Value.kOff);
    this.Extended = Value.kOff;
  } */

  public boolean getExtended() { //public Value getExtended() {
    return this.extended;
    //return this.Extended;
  }
}
