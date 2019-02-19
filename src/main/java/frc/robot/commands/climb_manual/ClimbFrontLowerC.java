package frc.robot.commands.climb_manual;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot; 

//This lifts the front of the robot using double solenoids

public class ClimbFrontLowerC extends Command {
  public ClimbFrontLowerC() {
    requires (Robot.m_ClimbFrontS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbFrontS.retractFront();
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
