package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class WheelHatchMechS extends Subsystem {
  public Spark hatchMechMotor;
  public static DigitalInput hatchMechLimit;

  public WheelHatchMechS(){
    hatchMechMotor = new Spark(RobotMap.PWM_ID_TALON_HATCHWHEELS);
    //hatchMechLimit = new DigitalInput(RobotMap.DIO_LIMIT_HATCH);

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
}
