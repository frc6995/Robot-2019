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
    SmartDashboard.putNumber("Ladder Height", Robot.m_ladderS.getLadderEncoderCount());
    SmartDashboard.putString("Ladder Level", Robot.m_ladderS.LadderLevelToString(Robot.m_ladderS.getNextLadderLevel()));
    SmartDashboard.putNumber("Ladder Output A", Robot.m_ladderS.ladderTalonA.getMotorOutputPercent());
    SmartDashboard.putNumber("Ladder Output B", Robot.m_ladderS.ladderTalonB.getMotorOutputPercent());

    SmartDashboard.putNumber("Ladder voltage A", Robot.m_ladderS.ladderTalonA.getMotorOutputVoltage());

    SmartDashboard.putNumber("Ladder Current A", Robot.m_ladderS.ladderTalonA.getOutputCurrent());

    SmartDashboard.putNumber("Integral", Robot.m_ladderS.ladderTalonA.getIntegralAccumulator());
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
