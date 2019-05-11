package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class HatchDrawerToggleC extends InstantCommand {
  public HatchDrawerToggleC() {
    requires(Robot.m_hatchMechDrawerS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_hatchMechDrawerS.toggle();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}