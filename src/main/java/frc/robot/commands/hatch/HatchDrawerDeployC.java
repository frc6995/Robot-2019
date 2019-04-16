package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class HatchDrawerDeployC extends InstantCommand {
  public HatchDrawerDeployC() {
    requires(Robot.m_hatchMechDrawerS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_hatchMechDrawerS.deploy();
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
    end();
  }
}