package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchDrawerToggleC extends Command {
  public HatchDrawerToggleC() {
    requires(Robot.m_hatchMechDrawerS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.m_hatchMechDrawerS.get_Value() == true) {
      Robot.m_hatchMechDrawerS.set_value(false);
    }
    else {
      Robot.m_hatchMechDrawerS.set_value(true);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}