package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveLadderC extends Command {

//constants
private double ladderKp;
private double ladderKi;
private double sumError;

//limit switch used to reset encoders.
DigitalInput limitSwitch;

  public MoveLadderC() {
    requires(Robot.m_ladderS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //initialize the limit switch to channel 1, will need to be changed
    limitSwitch = new DigitalInput(1);
    //move ladder slightly up.
    Robot.m_ladderS.MoveLadder(0.3);
    //move ladder down while the limit switch is not closed.
    while (limitSwitch.getChannel() != 0) {
      Robot.m_ladderS.MoveLadder(-0.3);
    }
    //reset encoders.
    Robot.m_ladderS.ResetEncoders();
  }

  @Override
  protected void execute() {

    if (Robot.m_oi.xbox.getBumperPressed(Hand.kRight) && Robot.m_ladderS.GetCurrentLadderLevel() != 3) {
      Robot.m_ladderS.SetNextLadderLevel(Robot.m_ladderS.GetCurrentLadderLevel() + 1);
    } // if right bumper is pressed, tell the robot to ladder move up a level
    else if (Robot.m_oi.xbox.getBumperPressed(Hand.kLeft) && Robot.m_ladderS.GetCurrentLadderLevel() != 1) {
      Robot.m_ladderS.SetNextLadderLevel(Robot.m_ladderS.GetCurrentLadderLevel() - 1);
    } //if left bumper is pressed, tell the robot to move ladder down a level
   //for xbox controller
   
    if (Robot.m_oi.stick.getRawButton(RobotMap.LADDER_UP_BUTTON) == true && Robot.m_ladderS.GetCurrentLadderLevel() != 3) {
      Robot.m_ladderS.SetNextLadderLevel(Robot.m_ladderS.GetCurrentLadderLevel() + 1);
    }
    else if (Robot.m_oi.stick.getRawButton(RobotMap.LADDER_DOWN_BUTTON) == true && Robot.m_ladderS.GetCurrentLadderLevel() != 1) {
      Robot.m_ladderS.SetNextLadderLevel(Robot.m_ladderS.GetCurrentLadderLevel() - 1);
    }
    //for joystick

    if (Robot.m_ladderS.GetCurrentLadderLevel() == Robot.m_ladderS.GetNextLadderLevel()) {
      return;
    }
    else {

      double error = Robot.m_ladderS.GetError();
      double power = error * ladderKp;
      
      if (Math.abs(error) < 100) {
        power += sumError * ladderKi;
        sumError += error;            
      }
      else {
        sumError = 0;
      }
      Robot.m_ladderS.MoveLadder(power);
        
      /*  if (Robot.m_ladderS.GetNextLadderLevel() == 1) {
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
          //if moving up to level 3 */
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
