package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Robot;

public class MoveLadderC extends Command {

  // -- PID constants --
  private boolean ladderPIDActive = false;
  //Preportoinal constant
  private double ladderKp = 0.01;
  //Integral constant
  private double ladderKi = 0.0;
  //Sum error
  private double sumError = 0;
  //Threshold when we turn the integral on
  private int iThreshold = 50;
  //Feedforward = power needed to hold the ladder in a constant spot
  private double ladderKf = 0.2;

  //limit switch used to reset encoders.
  DigitalInput limitSwitch;

  public MoveLadderC() {
    requires(Robot.m_ladderS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //initialize the limit switch to channel 1, will need to be changed
    limitSwitch = new DigitalInput(0);
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

    if(ladderPIDActive){
      //Retrives the error from the 
      int error = Robot.m_ladderS.GetError();
      
      //                P term                  I term        F term
      double power = error * ladderKp + sumError * ladderKi + ladderKf;

      //Only "turns on" the integral when we need it
      if(Math.abs(error)<iThreshold){
        //Sums the error
        sumError += error;
      }else{
        //Resets the sumError term when we don't need it
        sumError = 0;
      }
    }
    else{
      if (Robot.m_ladderS.GetCurrentLadderLevel() == Robot.m_ladderS.GetNextLadderLevel()) {
        return;
      } 
      else{     
        if (Robot.m_ladderS.GetNextLadderLevel() == 1) {
          do {
            Robot.m_ladderS.MoveLadder(-1);
          } while (Robot.m_ladderS.GetLadderEncoderCount() >= Robot.m_ladderS.LADDER_LEVEL_ONE);
          Robot.m_ladderS.SetLadderLevel(1);
        }
        //if moving down to level 1
        else if (Robot.m_ladderS.GetNextLadderLevel() == 2 && Robot.m_ladderS.GetCurrentLadderLevel() == 1) {  
          do {
            Robot.m_ladderS.MoveLadder(1);
          } while (Robot.m_ladderS.GetLadderEncoderCount() <= Robot.m_ladderS.LADDER_LEVEL_TWO);
          Robot.m_ladderS.SetLadderLevel(2);
        } 
        //if moving up from level 1 to level 2
        else if (Robot.m_ladderS.GetNextLadderLevel() == 2 && Robot.m_ladderS.GetCurrentLadderLevel() == 3) {
          do {
            Robot.m_ladderS.MoveLadder(-1);
          } while (Robot.m_ladderS.GetLadderEncoderCount() >= Robot.m_ladderS.LADDER_LEVEL_TWO);
          Robot.m_ladderS.SetLadderLevel(2);
        }
        //if moving down from level 3 to level 2
        else if (Robot.m_ladderS.GetNextLadderLevel() == 3) {
          do {
            Robot.m_ladderS.MoveLadder(1);
          } while (Robot.m_ladderS.GetLadderEncoderCount() <= Robot.m_ladderS.LADDER_LEVEL_THREE);
          Robot.m_ladderS.SetLadderLevel(3);
        }
        //if moving up to level 3 
      } 
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
