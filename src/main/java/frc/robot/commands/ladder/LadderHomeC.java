package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.LadderS.LadderLevel;

//Moves the ladder down to the home position and resets the encoders; Runs when robot is enabled
public class LadderHomeC extends Command {
  public boolean finished;
  private int i;
  private int j;
  private boolean encodersReset;
  private double originalEncoderCount;

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
    originalEncoderCount = Robot.m_ladderS.getLadderEncoderCount();
  }

  @Override
  protected void execute() {
    //Move ladder slightly up.
    SmartDashboard.putBoolean("Enc reset", encodersReset);
    if (i < 20) {
      i += 1;
      Robot.m_ladderS.setLadderPower(0.2);
      if (originalEncoderCount == Robot.m_ladderS.getLadderEncoderCount() && 
          Robot.m_ladderS.getLadderEncoderCount() != 0) {
        SmartDashboard.putString("Oops","Encoder values have not changed!!!");
        return;
      }
    } 
    else {
      j += 1;
      if (Robot.m_ladderS.lowerLimitSwitchPressed() == false && j < 100) {
        Robot.m_ladderS.setLadderPower(-0.05);
        System.out.print("Bringing ladder down");
      } 
      else if (j >= 100) {
        SmartDashboard.putString("Manually reset encoders", "Manually reset encoders");
        finished = true;
        }
      else {
        System.out.print("Resetting Encoders");    
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
    Robot.m_ladderS.setCurrentLadderLevel(LadderLevel.LEVEL_ONE);
    encodersReset = false;
    finished = false;
    i = 0;
    j = 0;
  }

  @Override
  protected void interrupted() {
    end();
  }
}
