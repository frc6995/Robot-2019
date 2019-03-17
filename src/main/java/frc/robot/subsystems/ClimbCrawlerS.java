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
}
