package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveLadderC extends Command {
  public MoveLadderC() {
    requires(Robot.m_ladderS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    if (Robot.m_oi.xbox.getBumperPressed(Hand.kRight) && Robot.m_ladderS.GetCurrentLadderLevel() != 3) {
      Robot.m_ladderS.SetNextLadderLevel(Robot.m_ladderS.GetCurrentLadderLevel() + 1);
    } // if right bumper is pressed, tell the robot to ladder move up a level
    else if (Robot.m_oi.xbox.getBumperPressed(Hand.kLeft) && Robot.m_ladderS.GetCurrentLadderLevel() != 1) {
      Robot.m_ladderS.SetNextLadderLevel(Robot.m_ladderS.GetCurrentLadderLevel() - 1);
    } //if left bumper is pressed, tell the robot to move ladder down a level



    if (Robot.m_ladderS.GetCurrentLadderLevel() == Robot.m_ladderS.GetNextLadderLevel()) {
      return;
    }
    else {

        
        if (Robot.m_ladderS.GetNextLadderLevel() == 1) {
          do {
            Robot.m_ladderS.MoveLadder(-1);
          } while (Robot.m_ladderS.GetMotorAEncoderCount() >= Robot.m_ladderS.LADDER_LEVEL_ONE);
          Robot.m_ladderS.SetLadderLevel(1);
        }
        //if moving down to level 1

        else if (Robot.m_ladderS.GetNextLadderLevel() == 2 && Robot.m_ladderS.GetCurrentLadderLevel() == 1) {  
          do {
            Robot.m_ladderS.MoveLadder(1);
          } while (Robot.m_ladderS.GetMotorAEncoderCount() <= Robot.m_ladderS.LADDER_LEVEL_TWO);
          Robot.m_ladderS.SetLadderLevel(2);
          } 
          //if moving up from level 1 to level 2

        else if (Robot.m_ladderS.GetNextLadderLevel() == 2 && Robot.m_ladderS.GetCurrentLadderLevel() == 3) {
          do {
            Robot.m_ladderS.MoveLadder(-1);
          } while (Robot.m_ladderS.GetMotorAEncoderCount() >= Robot.m_ladderS.LADDER_LEVEL_TWO);
          Robot.m_ladderS.SetLadderLevel(2);
        }
          //if moving down from level 3 to level 2
        else if (Robot.m_ladderS.GetNextLadderLevel() == 3) {
          do {
            Robot.m_ladderS.MoveLadder(1);
          } while (Robot.m_ladderS.GetMotorAEncoderCount() <= Robot.m_ladderS.LADDER_LEVEL_THREE);
          Robot.m_ladderS.SetLadderLevel(3);
          }
          //if moving up to level 3
        }
      
      
    }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
