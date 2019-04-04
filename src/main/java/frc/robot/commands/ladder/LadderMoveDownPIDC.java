package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
//import frc.robot.subsystems.LadderS.LadderLevel; //not used

public class LadderMoveDownPIDC extends Command {
  private int bufferLevel;
  public LadderMoveDownPIDC() {
    requires(Robot.m_ladderS);
    //This command should not be interrupted, but we may want to change this -- WHY?
    //We could always use a toggleWhenPressed(LadderRunPIDC) to be able to cancel it without it being interruptible.
    this.setInterruptible(false);
    //Robot.m_ladderS.enablePID();  Can we remove this?
    this.bufferLevel = RobotMap.LADDER_LEVEL_CUSHION;
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
    // if (Robot.m_ladderS.isAtSetPoint() || Robot.m_ladderS.getLadderEncoderCount() < RobotMap.LADDER_LEVEL_CUSHION) {
    //   Robot.m_ladderS.setNextLadderLevel(LadderLevel.LEVEL_BOTTOM);
    //   Robot.m_ladderS.setMaxPIDPower(0.05);
    //   Robot.m_ladderS.runPID();
    // } else {
    //   Robot.m_ladderS.setMaxPIDPower(0.4);
    //   Robot.m_ladderS.runPID();
    // }

      if(Robot.m_ladderS.getLadderEncoderCount() < RobotMap.LADDER_LEVEL_TWO) {
        Robot.m_ladderS.setMaxPIDPower(0.15);
      } else {
        Robot.m_ladderS.setMaxPIDPower(0.3);
      }
      Robot.m_ladderS.runPID();
  }

  @Override
  protected boolean isFinished() {
    //return Robot.m_ladderS.getLadderEncoderCount() < 10;
    return (Robot.m_ladderS.getLadderEncoderCount() < bufferLevel);
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
