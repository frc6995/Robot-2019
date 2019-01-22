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
public class ClimbRear extends Subsystem {
  
  private static DoubleSolenoid ClimbRearMechanism;
  private static DigitalInput ClimberRearSwitch;

//  @Override
//  public void initDefaultCommand() {
//    // Set the default command for a subsystem here.
//    // setDefaultCommand(new MySpecialCommand());
//  }

  public boolean CSwitchRear() {
    return ClimbRearSwitch.get(); //returns the Rear limit switch
  }
  public void deployRear() {
    ClimbRearMechanism.set(Value.kForward);
  }
  public void retractRear() {
    ClimbRearMechanism.set(Value.kReverse);
}
