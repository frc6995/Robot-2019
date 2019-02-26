package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

// NOTE!!! The rear should be the side without the ladder, however, the side without the ladder should be going first.

public class ClimbRearS extends Subsystem {
  
  private static Solenoid climbMechanismRear;
  private static DigitalInput climberRearSwitch;
  private boolean extended;
  //private Value Extended;

  public ClimbRearS() {
    //likely 4 & 5
    climbMechanismRear = new Solenoid(RobotMap.PCM_ID, RobotMap.PCM_ID_SOLENOID_CLIMBER_REAR); //possibly 3?? 2 for test
    //climbMechanismFront = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.PCM_ID_DSOLENOID_CLIMBER_REAR_FORWARD, RobotMap.PCM_ID_DSOLENOID_CLIMBER_REAR_REVERSE);
    climberRearSwitch = new DigitalInput(RobotMap.DIO_LIMIT_CLIMBER_REAR);
    this.extended = false;
    //this.Extended = Value.kReverse;
  }

  @Override
  public void initDefaultCommand() {
  }

  public boolean cSwitchRear() {
    SmartDashboard.putBoolean("LimitRear", climberRearSwitch.get());
    return climberRearSwitch.get();
  }

  public void deployRear() {
    System.out.println("deployRear");
    climbMechanismRear.set(true);
    //climbMechanismRear.set(kForward);
    this.extended = true;
    //this.Extended = Value.kForward;
  }
  
  public void retractRear() {
    System.out.println("retractRear");
    climbMechanismRear.set(false);
    //climbMechanismRear.set(kReverse);
    this.extended = false;
    //this.Extended = Value.kReverse;
  }

  /* public void offFront() {
    System.out.println("offRear");
    climbMechanismRear.set(Value.kOff);
    this.Extended = Value.kOff;
  } */

  public boolean getExtended() { //public Value getExtended() {
    return this.extended;
    //return this.Extended;
  }
}
