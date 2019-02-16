package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LadderManualMoveC extends Command {
  public LadderManualMoveC() {
    requires(Robot.m_ladderS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ladderS.setLadderPower(Robot.m_oi.xbox.right_stick_y()*0.3);
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}