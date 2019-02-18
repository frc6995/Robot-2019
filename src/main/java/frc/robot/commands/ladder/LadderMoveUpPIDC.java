package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * This command finishes and disables the PID when we have reached the set point. 
 * For holding at a set point after we have reached it, see "HoldLadderPIDC"
 * The sequence for moving the ladder to a position and then running other commands is
 * 
 *  -- Sets the ladder to run to position 1, runs there and then holds at that position
 * sequential(LadderMovePIDC())
 * parallel(LadderHoldPIDC())
 * 
 * -- Whatever you need to do (runs during the HoldLadderPIDC)
 * sequential(DoAThing())
 * 
 *  -- Sets the ladder back to position 0, and then goes there. Releasing control of the ladder when we reach it.
 * sequential(ChangeLadderLevel(0))
 * sequential(LadderMovePIDC())
 *  */

public class LadderMoveUpPIDC extends Command {
  
  public LadderMoveUpPIDC() {
    requires(Robot.m_ladderS);
    //This command should not be interrupted, but we may want to change this
    //We could always use a toggleWhenPressed(LadderRunPIDC) to be able to cancel it without it being interruptable.
    this.setInterruptible(false);

    Robot.m_ladderS.setMaxPIDPower(0.5);
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
