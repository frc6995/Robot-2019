package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

//A bang bang controller for the ladder that we are keeping just in case we need it.

//WARNING: THIS IS A BACKUP COMMAND THAT HASN'T BEEN WORKED ON MUCH

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
    
    if (Robot.m_ladderS.getCurrentLadderLevel() == Robot.m_ladderS.getNextLadderLevel()) {
      return;
    } 
    else{     
      if (Robot.m_ladderS.getNextLadderLevel() == 1) {
        do {
          Robot.m_ladderS.setLadderPower(-1);
        } while (Robot.m_ladderS.getLadderEncoderCount() >= RobotMap.LADDER_LEVEL_ONE);
        Robot.m_ladderS.setCurrentLadderLevel(1);
      }      //if moving down to level 1

      else if (Robot.m_ladderS.getNextLadderLevel() == 2 && Robot.m_ladderS.getCurrentLadderLevel() == 1) {  
        do {
          Robot.m_ladderS.setLadderPower(1);
        } while (Robot.m_ladderS.getLadderEncoderCount() <= RobotMap.LADDER_LEVEL_TWO);
        Robot.m_ladderS.setCurrentLadderLevel(2);
      }     //if moving up from level 1 to level 2

      else if (Robot.m_ladderS.getNextLadderLevel() == 2 && Robot.m_ladderS.getCurrentLadderLevel() == 3) {
        do {
          Robot.m_ladderS.setLadderPower(-1);
        } while (Robot.m_ladderS.getLadderEncoderCount() >= RobotMap.LADDER_LEVEL_TWO);
        Robot.m_ladderS.setCurrentLadderLevel(2);
      }      //if moving down from level 3 to level 2

      else if (Robot.m_ladderS.getNextLadderLevel() == 3) {
        do {
          Robot.m_ladderS.setLadderPower(1);
        } while (Robot.m_ladderS.getLadderEncoderCount() <= RobotMap.LADDER_LEVEL_THREE);
        Robot.m_ladderS.setCurrentLadderLevel(3);
      }      //if moving up to level 3 
    
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
