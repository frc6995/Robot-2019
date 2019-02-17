package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveArcadeXboxC extends Command {
  private double forwardSpeed = 0;
  private double backwardSpeed = 0; 
  private double moveSpeed = 0;
  private double rotSpeed = 0;
  private double throt = 1;
  private int numberPressed = 0;

  public DriveArcadeXboxC() {
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    forwardSpeed = -Robot.m_oi.xbox.left_trigger();
    backwardSpeed = -Robot.m_oi.xbox.right_trigger();
    
    moveSpeed = forwardSpeed - backwardSpeed;
    rotSpeed = Robot.m_oi.xbox.left_stick_x();

    if(Robot.m_oi.xbox.left_bumper_pressed()) {
      switch(numberPressed) {
        case 0: throt = 0.80; numberPressed = 1; break;
        case 1: throt = 0.65; numberPressed = 2; break;
        case 2: throt = 0.50; numberPressed = 3; break;
        case 3: throt = 1.00; numberPressed = 0; break;
        default: throt = 1.00; numberPressed = 0; break;
      }
    } else if(Robot.m_oi.xbox.right_bumper()) {
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

  public int getNumberPressed(){
    return numberPressed;
  }
  public void setNumberPressed(int numPressed){
    numberPressed = numPressed;
  }

  public double getThrottle(){
    return throt;
  }

  public void setThrottle(double throttle) {
    throt = throttle;
  }
}
