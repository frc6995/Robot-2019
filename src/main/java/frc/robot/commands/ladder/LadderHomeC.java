package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

//Moves the ladder down to the home position and resets the encoders
public class LadderHomeC extends Command {
  public boolean finished = false;
  private boolean ladderDown = false;

  public LadderHomeC() {
    requires(Robot.m_ladderS);
  }

  @Override
  protected void initialize() {
    
  }

  @Override
  protected void execute() {
    SmartDashboard.putBoolean("Ladder At Bottom\n(Was Ladder Up)", ladderDown);

    
    if(Robot.m_ladderS.lowerLimitSwitchPressed() && !ladderDown){
      //Move up while the limit switch is pressed, and the ladder down
      Robot.m_ladderS.setLadderPower(0.3);
    }else if(!Robot.m_ladderS.lowerLimitSwitchPressed()){
      //Move down when the ladder is up
      ladderDown = true;
      Robot.m_ladderS.setLadderPower(-0.1);
    }else if(Robot.m_ladderS.lowerLimitSwitchPressed() && ladderDown){
      //When the limit switched gets pressed while the ladder is up, stop the ladder, reset the encoders and end.
      Robot.m_ladderS.setLadderPower(0);
      Robot.m_ladderS.resetEncoder();
      finished = true;
    }
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  @Override
  protected void end() {
    //encodersReset = false;
  }

  @Override
  protected void interrupted() {
    end();
  }
}
