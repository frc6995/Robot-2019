package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class WheelHatchMechS extends Subsystem {
  public WPI_TalonSRX hatchMechMotor;
  public static DigitalInput hatchMechLimit;

  public WheelHatchMechS(){
    hatchMechMotor = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_HATCHWHEELS);
    hatchMechLimit = new DigitalInput(RobotMap.DIO_LIMIT_HATCH);

    //Set current limit
    hatchMechMotor.configContinuousCurrentLimit(20);
    hatchMechMotor.enableCurrentLimit(true);

    //May need to change depending on how the motors go
    //Negative is "pull" hatch in, Positive is "push" hatch out
    hatchMechMotor.setInverted(false);
  }

  @Override
  public void initDefaultCommand() {

  }

  public void setPower(double power){
    hatchMechMotor.set(power);
  }

  public boolean hasHatch(){
    return hatchMechLimit.get();
  }
}
