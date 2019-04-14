package frc.robot.commands.outdated;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WaitForHatchLimitC extends Command {
  public WaitForHatchLimitC() {
    this.setTimeout(4.0); //Sets a 4 second timeout
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut() || Robot.m_hatchMechWheelsS.getHatchLimit();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
