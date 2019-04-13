package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchMechWheelsS extends Subsystem {
  public Spark hatchMechMotor;
  public DigitalInput hatchMechLimit;

  public HatchMechWheelsS () {
    hatchMechMotor = new Spark(RobotMap.PWM_ID_SPARK_HATCHWHEELS);
    hatchMechLimit = new DigitalInput(RobotMap.DIO_LIMIT_HATCH);

    //Positive power pulls the hatch in, negative power pushes the hatch out
    hatchMechMotor.setInverted(true); //Adjust if nescasary

    //if it becomes a talon:
    //hatchMechMotor.configContinuousCurrentLimit(20);
    //hatchMechMotor.configPeakCurrentLimit(40);
    //hatchMechMotor.enableCurrentLimit(true);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void setPower(double power) {
    hatchMechMotor.set(power);
  }

  public boolean getHatchLimit() {
    //true = has hatch
    //also inversed so it can be wired the same as the cargo limit
    return !hatchMechLimit.get();
  }
}