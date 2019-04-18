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
  private double timeup;
  private double timedown;
  private boolean encodersReset;
  //private double originalEncoderCount;
  //originalEncoderCount is not used

  public LadderHomeC() {
    requires(Robot.m_ladderS);
    this.setInterruptible(false);
  }

  @Override
  protected void initialize() {
    encodersReset = false;
    finished = false;
    timeup = 0.5; //In seconds
    timedown = 4; //in seconds
    i = 0;
    j = 0;
    //originalEncoderCount = Robot.m_ladderS.getLadderEncoderCount();
  }

  @Override
  protected void execute() {
    //Move ladder slightly up.
    SmartDashboard.putBoolean("Enc reset", encodersReset);
    if (i < timeup * 50) {
      i += 1;
      Robot.m_ladderS.setLadderPower(0.7);
      //if (originalEncoderCount == Robot.m_ladderS.getLadderEncoderCount() && 
      //    Robot.m_ladderS.getLadderEncoderCount() != 0) {
      //  SmartDashboard.putString("Oops","Encoder values have not changed!!!");
      //  return;
      //}
    } 
    else {
      j += 1;
      if (!Robot.m_ladderS.lowerLimitSwitchPressed() && j < (timedown * 50)) {
        Robot.m_ladderS.setLadderPower(0);
        System.out.print("Bringing ladder down");
      } 
      else if (!Robot.m_ladderS.lowerLimitSwitchPressed() && j >= (timedown * 50)) {
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
