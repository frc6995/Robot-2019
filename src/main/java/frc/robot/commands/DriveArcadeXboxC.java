

/**
 * This xbox command uses the two triggers for forwards and back with the left stick acting as rotation and X is the throttle
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveArcadeXboxC extends Command {
  private double forwardSpeed = 0;
  private double backwardSpeed = 0; 
  private double moveSpeed = 0;
  private double rotSpeed = 0;
  private double throt = 0;
  private int numberPressed = 0;

  public DriveArcadeXboxC() {
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    forwardSpeed = -Robot.m_oi.xbox.getRawAxis(RobotMap.XBOX_DRIVE_FORWARD_SPEED);
    backwardSpeed = -Robot.m_oi.xbox.getRawAxis(RobotMap.XBOX_DRIVE_BACKWARD_SPEED);

    moveSpeed = forwardSpeed - backwardSpeed;
    rotSpeed = Robot.m_oi.xbox.getRawAxis(RobotMap.XBOX_DRIVE_LEFT_RIGHT);

    if(Robot.m_oi.xbox.getRawButtonPressed(RobotMap.BUTTON_THROTTLE_CHANGE)) {
      switch(numberPressed) {
        case 0: throt = 0.80; numberPressed = 1; break;
        case 1: throt = 0.65; numberPressed = 2; break;
        case 2: throt = 0.50; numberPressed = 3; break;
        case 3: throt = 1.00; numberPressed = 0; break;
        default: throt = 1.00; numberPressed = 0; break;
      }
    } else if(Robot.m_oi.xbox.getRawButtonPressed(RobotMap.BUTTON_THROTTLE_RESET)) {
      throt = 1;
      numberPressed = 0;
    }
    
    Robot.m_drivebaseS.arcadeDrive(moveSpeed, rotSpeed, throt);
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
