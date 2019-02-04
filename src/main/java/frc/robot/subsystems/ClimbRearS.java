/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ClimbRearS extends Subsystem {
  
  private static DoubleSolenoid ClimbMechanismRear;
  private static DigitalInput ClimberRearSwitch;

  public ClimbRearS() {
    ClimbMechanismRear = new DoubleSolenoid(RobotMap.SOLENOID_ID_CLIMBER_REAR, 2, 0); //possibly 3?? 2 for test
    ClimberRearSwitch = new DigitalInput(RobotMap.LIMIT_ID_CLIMBER_REAR);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public boolean CSwitchRear() {
    return ClimberRearSwitch.get(); //returns the Rear limit switch
  }

  public void deployRear() {
    ClimbMechanismRear.set(Value.kForward);
  }
  public void retractRear() {
    ClimbMechanismRear.set(Value.kReverse);
  }
}
