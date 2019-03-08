package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.LadderS.LadderLevel;

public class ScoreHatchOrCargoC extends InstantCommand {

  private Command m_ladderLevelCargoScoreCG;
  private Command m_ladderLevelHatchScoreCG;

  public ScoreHatchOrCargoC(LadderLevel ladderLevel) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_ladderLevelCargoScoreCG = new LadderLevelCargoScoreCG(ladderLevel);
    m_ladderLevelHatchScoreCG = new LadderLevelHatchScoreCG(ladderLevel);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.buttonBoard.pinky()){
      m_ladderLevelCargoScoreCG.start();
      System.out.println("Cargo Score Started");
    }else{
      m_ladderLevelHatchScoreCG.start();
      System.out.println("Hatch Score Started");
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
