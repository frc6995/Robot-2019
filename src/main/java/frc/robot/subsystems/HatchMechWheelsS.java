package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchMechWheelsS extends Subsystem {
  public Spark hatchMechMotor;

  public HatchMechWheelsS () {
    hatchMechMotor = new Spark(RobotMap.PWM_ID_SPARK_HATCHWHEELS);

    //Positive power pulls the hatch in, negative power pushes the hatch out
    hatchMechMotor.setInverted(true); //Adjust if nescasary
  }

  @Override
  public void initDefaultCommand() {
  }

  public void setPower(double power) {
    hatchMechMotor.set(power);
  }
}