package frc.robot.commands.climb_backup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This lifts the back of the robot using solenoids

public class ClimbRearLiftC extends Command {
  public ClimbRearLiftC() {
    requires(Robot.m_ClimbRearS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbRearS.deployRear();
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