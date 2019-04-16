package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//Holds the ladder at a PID value until interrupted by a RunLadderPID command. 
//Warning: Will not finish on its own!
//See RunLadderPID for how to run the ladder to a position, and for how a command group will work

public class LadderHoldPIDC extends Command {
  public LadderHoldPIDC() {
    requires(Robot.m_ladderS);
    
    this.setInterruptible(true);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ladderS.useUpKp();
    Robot.m_ladderS.setMaxPIDPower(0.7);
    Robot.m_ladderS.runPID();
  }

  @Override
  protected boolean isFinished() {
    return false;
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
