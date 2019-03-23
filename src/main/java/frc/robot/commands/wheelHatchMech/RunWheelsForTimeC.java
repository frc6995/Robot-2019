package frc.robot.commands.wheelHatchMech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunWheelsForTimeC extends Command {
  private double power;

  public RunWheelsForTimeC(double power, double timeout) {
    requires(Robot.m_WheelHatchMech);
    this.power = power;
    this.setTimeout(timeout);
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
    return isTimedOut();
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
