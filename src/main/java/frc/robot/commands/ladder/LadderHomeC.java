package frc.robot.commands.ladder;

import com.sun.source.tree.WhileLoopTree;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

//Moves the ladder down to the home position and resets the encoders
public class LadderHomeC extends Command {
  public boolean finished;
  private int i;
  private int j;
  private boolean encodersReset;

  public LadderHomeC() {
    requires(Robot.m_ladderS);
    this.setInterruptible(false);
  }

  @Override
  protected void initialize() {
    encodersReset = false;
    finished = false;
    i = 0;
    j = 0;
  }

  @Override
  protected void execute() {
    //Move ladder slightly up.
    SmartDashboard.putNumber("i", i);
    SmartDashboard.putBoolean("Enc reset", encodersReset);
    if (i < 20) {
      i += 1;
      Robot.m_ladderS.setLadderPower(-0.2); //negative is up, positive is down.
    } 
    else {
      j += 1;
      if (Robot.m_ladderS.lowerLimitSwitchPressed() == false && j < 100) {
        Robot.m_ladderS.setLadderPower(0.1);
        System.out.print("Bringing ladder down");
      } 
      else if (j >= 100) {
        SmartDashboard.putString("Manually reset encoders", "Manually reset encoders");
         while (!Robot.m_oi.xbox.left_stick()) {
            Robot.m_ladderS.setLadderPower(0.1);
         }
         finished = true;
         Robot.m_ladderS.resetEncoder();
        }
      else {
        System.out.print("Reseting Encoders");    
        Robot.m_ladderS.setLadderPower(0);
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
    Robot.m_ladderS.resetEncoder();
    encodersReset = false;
    finished = false;
    i = 0;
  }

  @Override
  protected void interrupted() {
    end();
  }
}
