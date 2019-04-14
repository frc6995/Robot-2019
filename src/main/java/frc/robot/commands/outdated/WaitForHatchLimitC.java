package frc.robot.commands.outdated;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WaitForHatchLimitC extends Command {
  private boolean value;

  public WaitForHatchLimitC(boolean value) {
    this.setTimeout(4.0); //Sets a 4 second timeout
    this.value = value;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut() || (Robot.m_hatchMechWheelsS.getHatchLimit() == value);
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
