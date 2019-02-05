package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot; 

public class ClimbFrontDeployC extends Command {
  public ClimbFrontDeployC() {
    requires (Robot.m_ClimbFrontS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbFrontS.deployFront();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
