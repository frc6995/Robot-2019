package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

//Moves the ladder down to the home positoin and resets the encoders
public class LadderHomeC extends Command {
  public boolean finished;
  private int i;
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
      if (Robot.m_ladderS.lowerLimitSwitchPressed() == false) {
        Robot.m_ladderS.setLadderPower(0.1);
        System.out.print("Bringing ladder down");
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
