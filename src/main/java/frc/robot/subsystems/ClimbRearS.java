package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class ClimbRearS extends Subsystem {
  
  private static DoubleSolenoid climbMechanismRear;
  private static DigitalInput climberRearSwitch;

  public ClimbRearS() {
    climbMechanismRear = new DoubleSolenoid(RobotMap.PCM_ID_DSOLENOID_CLIMBER_REAR, 2, 0); //possibly 3?? 2 for test
    climberRearSwitch = new DigitalInput(RobotMap.DIO_LIMIT_CLIMBER_REAR);
  }

  @Override
  public void initDefaultCommand() {
  }

  public boolean cSwitchRear() {
    return climberRearSwitch.get();
  }

  public void deployRear() {
    climbMechanismRear.set(Value.kForward);
  }
  
  public void retractRear() {
    climbMechanismRear.set(Value.kReverse);
  }
}
