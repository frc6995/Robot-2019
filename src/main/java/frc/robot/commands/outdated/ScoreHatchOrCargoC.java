package frc.robot.commands.outdated;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.LadderLevelCargoScoreCG;
import frc.robot.commands.LadderLevelHatchScoreCG;
import frc.robot.subsystems.LadderS.LadderLevel;


public class ScoreHatchOrCargoC extends InstantCommand {

  private Command m_ladderLevelCargoScoreCG;
  private Command m_ladderLevelHatchScoreCG;
  //private Command runningCommand;

  //private boolean isStarted = false;
  
  public ScoreHatchOrCargoC(LadderLevel ladderLevel) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_ladderLevelCargoScoreCG = new LadderLevelCargoScoreCG(ladderLevel);
    m_ladderLevelHatchScoreCG = new LadderLevelHatchScoreCG(ladderLevel);

    SmartDashboard.putData("Cargo ", m_ladderLevelCargoScoreCG);
    SmartDashboard.putData("", m_ladderLevelCargoScoreCG);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    m_ladderLevelCargoScoreCG.cancel();
    m_ladderLevelCargoScoreCG.close();
    m_ladderLevelHatchScoreCG.cancel();
    m_ladderLevelHatchScoreCG.close();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    m_ladderLevelCargoScoreCG.cancel();
    m_ladderLevelCargoScoreCG.close();
    m_ladderLevelHatchScoreCG.cancel();
    m_ladderLevelHatchScoreCG.close();

    if(Robot.m_oi.buttonBoard.pinky() && !m_ladderLevelHatchScoreCG.isRunning()){
      m_ladderLevelCargoScoreCG.start();
    }else if (!Robot.m_oi.buttonBoard.pinky() && !m_ladderLevelCargoScoreCG.isRunning()) {
      m_ladderLevelHatchScoreCG.start();
    }
    

    // if(isStarted == false){
    //   if(Robot.m_oi.buttonBoard.pinky()){
    //     m_ladderLevelCargoScoreCG.start();
    //     runningCommand = m_ladderLevelCargoScoreCG;
    //     System.out.println("Cargo Score Started");
    //   }else{
    //     m_ladderLevelHatchScoreCG.start();
    //     runningCommand = m_ladderLevelHatchScoreCG;
    //     System.out.println("Hatch Score Started");
    //   }

    //   isStarted = true;
    // }
  }

  // Called once after isFinished returns true
  // @Override
  // protected boolean isFinished() {
  //   System.out.println(runningCommand.isCompleted());
  //   return runningCommand.isCompleted();
  // }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // if(m_ladderLevelHatchScoreCG.isRunning()){
    //   m_ladderLevelHatchScoreCG.cancel();
    //   m_ladderLevelHatchScoreCG.close();
    // }
    // if(m_ladderLevelCargoScoreCG.isRunning()){
    //   m_ladderLevelCargoScoreCG.cancel();
    //   m_ladderLevelCargoScoreCG.close();
    // }
    // isStarted = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
