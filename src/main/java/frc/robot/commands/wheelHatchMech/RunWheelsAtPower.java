package frc.robot.commands.wheelHatchMech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunWheelsAtPower extends Command {
  public double power;

  public RunWheelsAtPower(double power) {
    requires(Robot.m_WheelHatchMech);
    this.power = power;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_WheelHatchMech.setPower(power);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_WheelHatchMech.setPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
