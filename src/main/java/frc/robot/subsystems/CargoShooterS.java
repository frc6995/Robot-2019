package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class CargoShooterS extends Subsystem {
  public Spark cargoShooterMotor;

  public CargoShooterS(){
    cargoShooterMotor = new Spark(RobotMap.PWM_ID_SPARK_CARGO_SHOOTER);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void setSpeed(double speed) {
    cargoShooterMotor.set(speed);
  }
}