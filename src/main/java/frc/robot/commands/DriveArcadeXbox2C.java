

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveArcadeXbox2C extends Command {
  private double forwardBackSpeed = 0;
  private double rotationSpeed = 0;
  private double throt = 1;

  private int numberPressed = 0;

  public DriveArcadeXbox2C() {
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    forwardBackSpeed = -Robot.m_oi.xbox.getRawAxis(RobotMap.XBOX2_DRIVE_FORWARD_BACK);
    rotationSpeed = Robot.m_oi.xbox.getRawAxis(RobotMap.XBOX2_DRIVE_LEFT_RIGHT);

    if(Robot.m_oi.xbox.getBButtonPressed()) {
      switch(numberPressed) {
        case 0: throt = 0.80; numberPressed = 1; break;
        case 1: throt = 0.65; numberPressed = 2; break;
        case 2: throt = 0.50; numberPressed = 3; break;
        case 3: throt = 1.00; numberPressed = 0; break;
        default: throt = 1.00; numberPressed = 0; break;
      }
    } else if(Robot.m_oi.xbox.getAButtonPressed()) {
      throt = 1;
      numberPressed = 0;
    }

    Robot.m_drivebaseS.arcadeDrive(forwardBackSpeed, rotationSpeed, throt);
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
