package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class CargoShooterS extends Subsystem {
  //public Spark cargoShooterMotor;
  public WPI_TalonSRX cargoShooterMotor;

  public CargoShooterS(){
    //cargoShooterMotor = new Spark(RobotMap.PWM_ID_SPARK_CARGO_SHOOTER);
    cargoShooterMotor = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_CARGO);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void setSpeed(double speed) {
    cargoShooterMotor.set(speed);
  }
}