package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LadderMoveDownPIDC extends Command {
  private int bufferLevel;

  public LadderMoveDownPIDC() {
    requires(Robot.m_ladderS);
    this.setInterruptible(false);
    this.bufferLevel = RobotMap.LADDER_LEVEL_CUSHION;
    setTimeout(5.0);
  }

  @Override
  protected void initialize() {
    if(Robot.m_ladderS.getLadderSetPointEncoderCount() <= RobotMap.LADDER_LEVEL_TWO) {
      bufferLevel = RobotMap.LADDER_LEVEL_CUSHION - 500;
    } else {
      bufferLevel = RobotMap.LADDER_LEVEL_CUSHION;
    }
  }

  @Override
  protected void execute() {
    Robot.m_ladderS.useDownKp(); //Should hopefully slow things down

    if(Robot.m_ladderS.getLadderEncoderCount() < RobotMap.LADDER_LEVEL_TWO) {
      Robot.m_ladderS.setMaxPIDPower(0.15);
    } else {
      Robot.m_ladderS.setMaxPIDPower(0.3);
    }

    Robot.m_ladderS.runPID();
  }

  @Override
  protected boolean isFinished() {
    return (Robot.m_ladderS.getLadderEncoderCount() < bufferLevel) || isTimedOut(); //Perhaps let it run past the buffer level a bit
  }

  @Override
  protected void end() {
    Robot.m_ladderS.disablePID();
    
  }
  

  @Override
  protected void interrupted() {
    end();
  }
}
