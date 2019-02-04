package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchMechRectractC extends Command {
  public HatchMechRectractC() {
    requires(Robot.m_hatchMechS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //Retract the HatchMech
    Robot.m_hatchMechS.retract();
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