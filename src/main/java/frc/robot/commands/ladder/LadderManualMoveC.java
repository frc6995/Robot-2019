package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//Allows driver to manually move ladder using xbox right stick y axis

public class LadderManualMoveC extends Command {
  public LadderManualMoveC() {
    requires(Robot.m_ladderS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if ((Math.abs(Robot.m_oi.stick.stick_y()) < 0.1) && Robot.m_oi.stick.button_6()){
      Robot.m_ladderS.setLadderPower(0.1);}
    else
      Robot.m_ladderS.setLadderPower(Robot.m_oi.stick.stick_y() * 0.4);
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
