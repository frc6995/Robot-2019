package frc.robot.commands.ladder;

import java.util.Date;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * This command finishes and disables the PID when we have reached the set point. 
 * For holding at a set point after we have reached it, see "LadderHoldPIDC"
 * The sequence for moving the ladder to a position and then running other commands is
 * 
 *  -- Sets the ladder to run to a specified position, runs there and then holds that position
 * addSequential(new LadderSetLevelC(LadderLevel.LEVEL_TWO));
 * addSequential(new LadderMoveUpPIDC());
 * 
 * addParallel(new LadderHoldPIDC());
 * 
 * -- Whatever you need to do (runs during the HoldLadderPIDC)
 * addSequential(new DoAThing());
 * 
 * -- Return to level 0 gently - Drives to cushion level and lets gravity return to Level 1.
 * addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
 * addSequential(new LadderMoveDownPIDC());
 **/

public class LadderMoveUpPIDC extends Command {

  double previous_count = 0;
  double count = 0;
  String dir = "up";
  Date time1 = null;
  Date time2 = null;
  
  public LadderMoveUpPIDC() {
    requires(Robot.m_ladderS);
    requires(Robot.m_limelight);
    //This command should not be interrupted. We could always use a 
    //toggleWhenPressed(LadderRunPIDC) to be able to cancel it without it being interruptable.
    this.setInterruptible(true);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ladderS.useUpKp();
    Robot.m_ladderS.setMaxPIDPower(0.9);
    Robot.m_ladderS.runPID();
    previous_count = count;
    count = Robot.m_ladderS.getLadderEncoderCount();
    if (dir=="up") {
      if (count > previous_count) {}
      if (count < previous_count) {
        if(time1==null){time1 = new Date();}
        dir = "down";
      }
    }
    if (dir=="down") {
      if (count > previous_count) {
        if(time2==null){time2 = new Date();}
        dir = "up";
      }
      if (count < previous_count) {}
    }
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_ladderS.isAtSetPoint();
  }

  @Override
  protected void end() {
    SmartDashboard.putString("1st PID Loop time", time1.toString());
    SmartDashboard.putString("2nd PID Loop time", time2.toString());
    //Robot.m_ladderS.disablePID();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
