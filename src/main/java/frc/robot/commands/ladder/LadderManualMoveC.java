package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//Allows driver to manually move ladder using joystick buttons 8 & 7.
public class LadderManualMoveC extends Command {
  public LadderManualMoveC() {
    requires(Robot.m_ladderS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
      Robot.m_ladderS.setLadderPower(Robot.m_oi.xbox.left_stick_y());
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
