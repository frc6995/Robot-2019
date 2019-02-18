package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LadderMoveDownPIDC extends Command {
  public LadderMoveDownPIDC() {
    requires(Robot.m_ladderS);
    //This command should not be interrupted, but we may want to change this
    //We could always use a toggleWhenPressed(LadderRunPIDC) to be able to cancel it without it being interruptable.
    this.setInterruptible(false);

    Robot.m_ladderS.setMaxPIDPower(0.3);
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
