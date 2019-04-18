package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LadderMoveDownC extends Command {
  private int bufferLevel;

  // Adjust as needed
  private double movePower = -0.1;
  private double slowPower = 0.1;
  private int stopLevel = 300;

  public LadderMoveDownC() {
    requires(Robot.m_ladderS);
    setInterruptible(false);
    bufferLevel = RobotMap.LADDER_LEVEL_CUSHION;
    this.setTimeout(10);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // When we are below the buffer level, apply a bit of breaking power
    if(Robot.m_ladderS.getLadderEncoderCount() < bufferLevel){
      Robot.m_ladderS.setLadderPower(slowPower); // Just enough power to slow the ladder down
    }else{
      Robot.m_ladderS.setLadderPower(movePower); // Coast
    }
  }

  @Override
  protected boolean isFinished() {
    // When the ladder is at the bottom, or we time out
    return (Robot.m_ladderS.getLadderEncoderCount() < stopLevel) || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_ladderS.setLadderPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
