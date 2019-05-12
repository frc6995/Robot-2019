package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

    //this may become a spark, in which case all this goes
    cargoShooterMotor.configContinuousCurrentLimit(40);
    cargoShooterMotor.configPeakCurrentLimit(60);
    cargoShooterMotor.configPeakCurrentDuration(100);
    cargoShooterMotor.enableCurrentLimit(true);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void setSpeed(double speed) {
    cargoShooterMotor.set(speed);
  }

  public boolean getCargoLimit() {
    //inverses the output of the limit swich, when it is pressed send false and when not pressed send true.
    return !cargoLimit.get();
  }

  /**
   * begins current limiting with continuous at 15 and peak at 25.
   * if drastic, cut the voltage down to only 6 volts.
   * @param drastic
   */
  public void lowPowerModeOn(boolean drastic) {
    cargoShooterMotor.configContinuousCurrentLimit(15);
    cargoShooterMotor.configPeakCurrentLimit(25);
    if (drastic) {
      //cargoShooterMotor.configVoltageCompSaturation(6);
      //cargoShooterMotor.enableVoltageCompensation(true);
    }
  }

  /**
   * begins current limiting with continuous at 15 and peak at 25.
   * if drastic, pass
   * if insane, disable motor.
   * @param drastic
   * @param insane
   */
  public void lowPowerModeOn(boolean drastic, boolean insane) {
    cargoShooterMotor.configContinuousCurrentLimit(15);
    cargoShooterMotor.configPeakCurrentLimit(25);
    if (drastic) {
      //cargoShooterMotor.configVoltageCompSaturation(6);
      //cargoShooterMotor.enableVoltageCompensation(true);
    }
    if (insane) {
      //cargoShooterMotor.configVoltageCompSaturation(2);
      //cargoShooterMotor.enableVoltageCompensation(true);
      cargoShooterMotor.disable();
    }
  }

  /**
   * Reset the cargo Shooter to the same voltage settings as initialization.
   * 
   * (Continuous current: 20A)
   * (Peak current: 40A)
   * (Voltage: 12V)
   */
  public void lowPowerModeOff() {
    cargoShooterMotor.configContinuousCurrentLimit(40);
    cargoShooterMotor.configPeakCurrentLimit(60);
    //cargoShooterMotor.configVoltageCompSaturation(12);
    //cargoShooterMotor.enableVoltageCompensation(false); //what happens to the drivebase if we limit it to 12v initially?
    cargoShooterMotor.set(ControlMode.PercentOutput, 0);
  }
}