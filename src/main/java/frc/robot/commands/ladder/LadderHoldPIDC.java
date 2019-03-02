package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//Holds the ladder at a PID value until interrupted by a RunLadderPID command. 
//Warning: Will not finish on its own!
//See RunLadderPID for how to run the ladder to a position, and for how a command group will work

public class LadderHoldPIDC extends Command {
  public LadderHoldPIDC() {
    requires(Robot.m_ladderS);
    
    this.setInterruptible(false);
    //Robot.m_ladderS.setMaxPIDPower(0.5); //Why initialized to .5 then set to .7 in execute?
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ladderS.setMaxPIDPower(0.7);
    Robot.m_ladderS.runPID();
    //Robot.m_ladderS.setLadderPower(0.15);  Can this be removed?
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    //Might need to add some sort of "handoff" code to the RunLadderPID command - Can this comment be removed?
    Robot.m_ladderS.disablePID();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
