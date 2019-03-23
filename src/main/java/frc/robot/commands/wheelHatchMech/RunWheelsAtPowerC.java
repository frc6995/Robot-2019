package frc.robot.commands.wheelHatchMech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunWheelsAtPowerC extends Command {
  public double power;

  public RunWheelsAtPowerC(double power) {
    requires(Robot.m_WheelHatchMechS);
    this.power = power;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_WheelHatchMechS.setPower(power);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_WheelHatchMechS.setPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
