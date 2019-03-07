package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class CargoShooterS extends Subsystem {
  //public Spark cargoShooterMotor;
  public WPI_TalonSRX cargoShooterMotor;
  private static DigitalInput cargoLimit;

  public CargoShooterS(){
    //cargoShooterMotor = new Spark(RobotMap.PWM_ID_SPARK_CARGO_SHOOTER);
    cargoShooterMotor = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_CARGO);
    cargoLimit = new DigitalInput(RobotMap.DIO_LIMIT_CARGO);
    cargoShooterMotor.configContinuousCurrentLimit(20);
    cargoShooterMotor.enableCurrentLimit(true);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void setSpeed(double speed) {
    cargoShooterMotor.set(speed);
  }

  public boolean getCargoLimit() {
    return cargoLimit.get();
  }
}