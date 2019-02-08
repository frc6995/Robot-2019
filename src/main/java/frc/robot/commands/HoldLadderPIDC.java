package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//Holds the ladder at a PID value until interupted by a RunLadderPID command. 
//Warning: Will not finish on its own!
//See RunLadderPID for how to run the ladder to a position, and for how I think the command group will work

public class HoldLadderPIDC extends Command {
  public HoldLadderPIDC() {
    requires(Robot.m_ladderS);
    
    this.setInterruptible(true);

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
    return false;
  }

  @Override
  protected void end() {
    //Might need to add some sort of "handoff" code to the RunLadderPID command
    Robot.m_ladderS.disablePID();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
