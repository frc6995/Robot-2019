package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

// NOTE!!! The rear should be the side without the ladder, however, the side without the ladder should be going first.

public class ClimbRearS extends Subsystem {
  
  private static Solenoid climbMechanismRear;
  private static DigitalInput climberRearSwitch;
  private boolean extended;

  public ClimbRearS() {
    //likely 3 counting up and following the pattern, see ClimbFrontS.java
    climbMechanismRear = new Solenoid(RobotMap.PCM_ID, RobotMap.PCM_ID_SOLENOID_CLIMBER_REAR); //possibly 3?? 2 for test
    climberRearSwitch = new DigitalInput(RobotMap.DIO_LIMIT_CLIMBER_REAR);
    this.extended = false;
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
    this.extended = true;
  }
  
  public void retractRear() {
    System.out.println("retractRear");
    climbMechanismRear.set(false);
    this.extended = false;
  }

  public boolean getExtended() {
    return this.extended;
  }
}
