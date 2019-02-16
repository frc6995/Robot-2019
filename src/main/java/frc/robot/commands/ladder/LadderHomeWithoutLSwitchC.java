package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LadderHomeWithoutLSwitchC extends Command {
  private boolean finished = false;
  private int i = 0;
  private int j = 0;

  public LadderHomeWithoutLSwitchC() {
    requires(Robot.m_ladderS);
    this.setInterruptible(false);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    if (i < 20) {
      i += 1;
      Robot.m_ladderS.setLadderPower(-0.2);
    } else {
        if (j < 20) {
          j += 1;
          Robot.m_ladderS.setLadderPower(0.1);
        } else {
          Robot.m_ladderS.resetEncoder();
          Robot.m_ladderS.setLadderPower(0);
          finished = true;
          end();
        }
    }
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  @Override
  protected void end() {
    i = 0;
    j = 0;
    finished = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
