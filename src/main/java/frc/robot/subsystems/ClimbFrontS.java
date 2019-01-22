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
public class ClimbFrontS extends Subsystem {
  private static DoubleSolenoid ClimbFrontMechanism;
  private static DigitalInput ClimberFrontSwitch;

  public ClimbFrontS(){
    ClimbMechanismFront = new DoubleSolenoid(RobotMap.CLIMBER_ID_FRONT, 0, 1);//0, 1 is correct
    ClimberFrontSwitch = new DigitalInput(RobotMap.CLIMBER_FRONT_LIMIT);
  }

//  @Override
//  public void initDefaultCommand() {
//   // Set the default command for a subsystem here.
//  // setDefaultCommand(new MySpecialCommand());
//  }
  public boolean CSwitchFront() {
    return ClimberFrontSwitch.get(); //returns the front limit switch
  }
  public void deployFront() {
    ClimbMechanismFront.set(Value.kForward);
  }
  public void retractFront() {
    ClimbMechanismFront.set(Value.kReverse);
  }

}
