package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotMap;

public class ClimbMotorControlS extends Subsystem {
  public Spark climbMotor = null;

  public ClimbMotorControlS() {
    climbMotor = new Spark(RobotMap.PWM_ID_SPARK_CLIMB_MOVEMENT);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void motorForwards() {
    climbMotor.set(0.5);
  }

  public void motorStop() {
    climbMotor.set(0.0);
  }
}
