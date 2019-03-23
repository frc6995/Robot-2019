package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class WheelHatchMech extends Subsystem {
  public WPI_TalonSRX hatchMechMotor;
  public static DigitalInput hatchMechLimit;

  public WheelHatchMech(){
    hatchMechMotor = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_HATCHWHEELS);
    hatchMechLimit = new DigitalInput(RobotMap.DIO_LIMIT_HATCH);
    hatchMechMotor.configContinuousCurrentLimit(20);
    hatchMechMotor.enableCurrentLimit(true);
  }

  @Override
  public void initDefaultCommand() {
    
  }

  public void setSpeed(double speed){
    hatchMechMotor.set(speed);
  }

  public boolean getHatchLimit(){
    return hatchMechLimit.get();
  }
}
