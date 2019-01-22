/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import java.awt.Button;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ClimbS extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static DoubleSolenoid ClimbMechanismFront;
  private static DoubleSolenoid ClimbMechanismRear;

  private static DigitalInput ClimberFrontSwitch;

  private static DigitalInput ClimberRearSwitch;

  public ClimbS(){
    //Creates a new solenoid object
    ClimbMechanismFront = new DoubleSolenoid(RobotMap.CLIMBER_ID_FRONT, RobotMap.CLIMBER_DEPLOY_CHANNEL_FRONT, RobotMap.CLIMBER_RETRACT_CHANNEL_FRONT);
    ClimbMechanismRear = new DoubleSolenoid(RobotMap.CLIMBER_ID_REAR, RobotMap.CLIMBER_DEPLOY_CHANNEL_REAR, RobotMap.CLIMBER_RETRACT_CHANNEL_REAR);
    ClimberFrontSwitch = new DigitalInput(RobotMap.CLIMBER_FRONT_LIMIT);
    ClimberRearSwitch = new DigitalInput(RobotMap.CLIMBER_REAR_LIMIT);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public boolean CSwitchFront() {
    return ClimberFrontSwitch.get(); //returns the front limit switch
  }
  public boolean CSwitchRear(){
    return ClimberRearSwitch.get(); //returns the rear limit switch
  }


  public void retractFront() {
    ClimbMechanismFront.set(Value.kReverse);
  }

  public void retractRear() {
    ClimbMechanismRear.set(Value.kReverse);
  }

  public void deployFront() {
    ClimbMechanismFront.set(Value.kForward);
  }

  public void deployRear() {
    ClimbMechanismRear.set(Value.kForward);
  }
  
}