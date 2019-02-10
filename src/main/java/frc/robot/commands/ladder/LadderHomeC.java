package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//Moves the ladder down to the home positoin and resets the encoders
public class LadderHomeC extends Command {
  public boolean finished = false;

  public LadderHomeC() {
    requires(Robot.m_ladderS);
  }

  @Override
  protected void initialize() {
    
  }

  @Override
  protected void execute() {
    //Move ladder slightly up.
    while(Robot.m_ladderS.lowerLimitSwitchPressed() == false){
      Robot.m_ladderS.setLadderPower(0.3);
    }

    //Move ladder down while the limit switch is not closed.
    while (Robot.m_ladderS.lowerLimitSwitchPressed()) {
      Robot.m_ladderS.setLadderPower(-0.3);
    }
    
    Robot.m_ladderS.resetEncoder();
    
    finished = true;
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}