package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * This command finishes and disables the PID when we have reached the set point. 
 * For holding at a set point after we have reached it, see "HoldLadderPIDC"
 * The sequence for moving the ladder to a position and then running other commands is
 * 
 *  -- Sets the ladder to run to positoin 1, runs there and then holds at that position
 * sequential(ChangeLadderLevel(1))
 * sequential(RunLadderPIDC())
 * parallel(HoldLadderPIDC())
 * 
 * -- Whatever you need to do (runs during the HoldLadderPIDC)
 * sequential(DoAThing())
 * 
 *  -- Sets the ladder back to position 0, and then goes there. Releacing control of the ladder when we reach it.
 * seqnential(ChangeLadderLevel(0))
 * sequential(RunLadderPIDC())
 *  */

public class RunLadderPIDC extends Command {
  
  public RunLadderPIDC() {
    requires(Robot.m_ladderS);
    //This command should not be interupted, but we may want to change this
    this.setInterruptible(false);

    Robot.m_ladderS.enablePID();
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ladderS.runPID();
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_ladderS.isAtSetPoint();
  }

  @Override
  protected void end() {
    Robot.m_ladderS.disablePID();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
