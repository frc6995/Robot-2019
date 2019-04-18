package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

//Displays ladder status, regardless of what command is running on the ladder
public class LadderDisplayStatusC extends Command {
  public LadderDisplayStatusC() {
    //Does not "require" the ladder subsystem, as it is only passively displaying the status
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ladderS.displayStatus();

    //Put any other testing display code you need here
    //SmartDashboard.putBoolean("Limit", Robot.m_ladderS.lowerLimitSwitchPressed());
    SmartDashboard.putNumber("Ladder Encoder", Robot.m_ladderS.getLadderEncoderCount());
    SmartDashboard.putString("ladder level", Robot.m_ladderS.LadderLevelToString(Robot.m_ladderS.getNextLadderLevel()));
  }
  
  @Override
  protected boolean isFinished() {
    //Never finishes as we want the ladder status displayed throughout the match
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
