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

  public void motorForward() {
    climbMotor.set(0.5);
  }

  public void motorStop() {
    climbMotor.set(0.0);
  }

  public void motorReverse() {
    climbMotor.set(-0.5);
  }
}
