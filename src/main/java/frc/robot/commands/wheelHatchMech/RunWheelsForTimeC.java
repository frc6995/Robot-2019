package frc.robot.commands.wheelHatchMech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunWheelsForTimeC extends Command {
  private double power;

  public RunWheelsForTimeC(double power, double timeout) {
    requires(Robot.m_WheelHatchMechS);
    this.power = power;
    this.setTimeout(timeout);
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
    return isTimedOut();
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
