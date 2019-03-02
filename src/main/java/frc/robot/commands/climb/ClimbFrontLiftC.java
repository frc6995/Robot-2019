package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot; 

//This lifts the front of the robot using solenoids

public class ClimbFrontLiftC extends Command {
  public ClimbFrontLiftC() {
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
