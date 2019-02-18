package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotMap;

public class ClimbCrawlerS extends Subsystem {

  public Spark climbMotor = null;

  public ClimbCrawlerS() {
    climbMotor = new Spark(RobotMap.PWM_ID_SPARK_CLIMB_MOVEMENT);
  }

  @Override
  public void initDefaultCommand() {
  }

  // These are defined here so motors are not set in the commands

  public void motorForward() {
    climbMotor.set(1.0);
  }

  public void motorStop() {
    climbMotor.set(0.0);
  }

  public void motorReverse() {
    // In this case, the motor will have to reverse onto the platforms, see NOTE in FrontS.
    climbMotor.set(-1.0);
  }
}
