package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * This command finishes and disables the PID when we have reached the set point. 
 * For holding at a set point after we have reached it, see "LadderHoldPIDC"
 * The sequence for moving the ladder to a position and then running other commands is
 * 
 *  -- Sets the ladder to run to a specified position, runs there and then holds that position
 * addSequential(new LadderSetLevelC(LadderLevel.LEVEL_TWO));
 * addSequential(new LadderMoveUpPIDC());
 * 
 * addParallel(new LadderHoldPIDC());
 * 
 * -- Whatever you need to do (runs during the HoldLadderPIDC)
 * addSequential(new DoAThing());
 * 
 * -- Return to level 0 gently - Drives to cushion level and lets gravity return to Level 1.
 * addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
 * addSequential(new LadderMoveDownPIDC());
 **/

public class LadderMoveUpPIDC extends Command {
  
  public LadderMoveUpPIDC() {
    requires(Robot.m_ladderS);

    //This command should not be interrupted. We could always use a 
    //toggleWhenPressed(LadderRunPIDC) to be able to cancel it without it being interruptable.
    this.setInterruptible(true);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ladderS.useUpKp();
    Robot.m_ladderS.setMaxPIDPower(0.9);
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
