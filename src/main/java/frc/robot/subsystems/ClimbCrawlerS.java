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

  // Pass positive speed to move forward, negative to go backwards)
  public void motorSet(double motorSpeed) {
    climbMotor.set(motorSpeed);
  }

  public void motorForward() {
    climbMotor.set(RobotMap.CLIMB_MOTORS_SPEED);
  }
  
  public void motorStop() {
    climbMotor.set(0.0);
  }

  public void motorReverse() {
    // In this case, the motor will have to reverse onto the platforms, see NOTE in FrontS.
    climbMotor.set(-RobotMap.CLIMB_MOTORS_SPEED);
  }
}
