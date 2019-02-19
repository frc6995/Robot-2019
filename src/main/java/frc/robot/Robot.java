package frc.robot;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.commands.drive.DriveArcadeXboxC;
//import frc.robot.commands.ladder.LadderHoldPIDC;
//import frc.robot.commands.ladder.LadderHomeC;
//import frc.robot.commands.ladder.LadderManualMoveC;
import frc.robot.commands.ladder.LadderDisplayStatusC;
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
  public static LadderS m_ladderS;
  public static HatchMechS m_hatchMechS;
  public static ClimbFrontS m_ClimbFrontS;
  public static ClimbRearS m_ClimbRearS;
  public static ClimbCrawlerS m_ClimbCrawlerS;

  public static OI m_oi;
  public Command m_autonomousCommand;
  public Command m_driveCommand;
  public Command m_homeLadderC;
  public Command m_ladderManualMoveC;
  public Command m_ladderDisplayStatusC;
  public SendableChooser<Command> drive_chooser = new SendableChooser<>();

  public DigitalInput limitSwitch;
  @Override
  public void robotInit() {
    // Instantiate Subsystems Here
    m_drivebaseS = new DrivebaseS();
    m_ladderS = new LadderS();
    m_hatchMechS = new HatchMechS();
    m_ClimbFrontS = new ClimbFrontS();
    m_ClimbRearS = new ClimbRearS();
    m_ClimbCrawlerS = new ClimbCrawlerS();

    m_oi = new OI();
    
    //Resets the ladder whenever we start the robot.
    //m_holdLadderC.start();
    //m_homeLadderC.start(); Disabled so we can test other things first

    //Resets the ladder whenever we start the robot
    //m_homeLadderCommand = new LadderHomeC();
    //m_homeLadderCommand.start();

    //Limelight setup to use camera
    CameraServer cs = CameraServer.getInstance();
    HttpCamera limelight = new HttpCamera("limelight", "http://10.69.95.11:5800", HttpCameraKind.kMJPGStreamer);
    limelight.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
    cs.startAutomaticCapture(limelight);
    //m_ladderManualMoveC = new LadderManualMoveC();
    m_ladderDisplayStatusC = new LadderDisplayStatusC();
  }

  public void robotPeriodic() {
    //m_ladderManualMoveC.start();
    m_ladderDisplayStatusC.start();
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
    m_ladderS.resetEncoder();
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
    //m_ladderS.resetEncoder();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
  
  @Override
  public void testPeriodic() {
  }
}
