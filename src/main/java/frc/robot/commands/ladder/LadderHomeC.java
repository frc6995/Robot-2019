package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

//Moves the ladder down to the home positoin and resets the encoders
public class LadderHomeC extends Command {
  public boolean finished = false;

  private int i = 0;
  private boolean encodersReset = false;

  public LadderHomeC() {
    requires(Robot.m_ladderS);
  }

  @Override
  protected void initialize() {
    
  }

  @Override
  protected void execute() {
    //Move ladder slightly up.
    SmartDashboard.putNumber("i", i);
    SmartDashboard.putBoolean("Enc reset", encodersReset);

    if (i < 20 && encodersReset == false) {
    i += 1;
    Robot.m_ladderS.setLadderPower(0.3);
    } else {
      if (Robot.m_ladderS.lowerLimitSwitchPressed() == false) {
        Robot.m_ladderS.setLadderPower(-0.3);
      } else if (Robot.m_ladderS.lowerLimitSwitchPressed() == true) {
        Robot.m_ladderS.resetEncoder();        
        Robot.m_ladderS.setLadderPower(0);
        i = 0;
        encodersReset = true;
        finished = true;
      }      
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
