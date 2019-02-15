package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

//Method to display ladder status, regardless of what command is running on the ladder
public class displayLadderStatusC extends Command {
  public displayLadderStatusC() {
    //Does not "require" the ladder subsystem, as it is only passively displaying the status
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //This method is designed for just competitoin display code
    Robot.m_ladderS.displayStatus();

    //Put any other testing display code you need here
    SmartDashboard.putBoolean("Limit", Robot.m_ladderS.lowerLimitSwitchPressed());

  }
  
  @Override
  protected boolean isFinished() {
    //Never finishes as we want the ladder status displayed throught the match
    return false;
  }

  @Override
  protected void end() {
  }
  
  @Override
  protected void interrupted() {
    end();
  }
}
