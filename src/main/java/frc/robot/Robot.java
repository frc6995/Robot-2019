/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveArcadeXbox2C;
import frc.robot.commands.DriveArcadeXboxC;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DrivebaseS m_drivebaseS;
  public static HatchMechS m_hatchMechS;

  public static OI m_oi;

  public Command m_autonomousCommand;
  public Command m_driveCommand;
  public SendableChooser<Command> drive_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    // Instantiate Subsystems Here
    m_drivebaseS = new DrivebaseS();
    m_hatchMechS = new HatchMechS();
    

    // Do not instantiate Subsystem below this line!

    m_oi = new OI();

    drive_chooser.setDefaultOption("XboxControl", new DriveArcadeXboxC());
    drive_chooser.addOption("XboxControl2", new DriveArcadeXbox2C());
    SmartDashboard.putData("Drive Control", drive_chooser);
  }

  @Override
  public void robotPeriodic() {
    m_driveCommand = drive_chooser.getSelected();
    m_driveCommand.start();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = drive_chooser.getSelected();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
  
  @Override
  public void testPeriodic() {
  }
}
