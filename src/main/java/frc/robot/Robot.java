package frc.robot;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.cargo.CargoShooterC;
import frc.robot.commands.hatch.HatchMechCG;
import frc.robot.commands.ladder.LadderDisplayStatusC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderHomeC;
import frc.robot.commands.ladder.LadderManualMoveC;
import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.limelight.setCameraMode;
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
  public static WheelHatchMech m_WheelHatchMech;
  public static ClimbFrontS m_ClimbFrontS;
  public static ClimbRearS m_ClimbRearS;
  public static ClimbCrawlerS m_ClimbCrawlerS;
  public static CargoShooterS m_CargoShooterS;
  
  public static OI m_oi;
  public Command m_ladderHomeC;
  public Command m_ladderManualMoveC;
  public Command m_ladderDisplayStatusC;

  public static Command m_ladderMoveUpPIDC;
  public static Command m_ladderMoveDownPIDC;
  public static Command m_ladderHoldPIDC;
  public static Command m_hatchMechCG;
  public static Command m_cargoShooterC;

  public Command m_setCameraModeC;

  //public UsbCamera usbCam = CameraServer.getInstance().startAutomaticCapture();

  @Override
  public void robotInit() {
    // Instantiate Subsystems Here
    m_drivebaseS = new DrivebaseS();
    m_ladderS = new LadderS();
    m_hatchMechS = new HatchMechS();
    m_ClimbFrontS = new ClimbFrontS();
    m_ClimbRearS = new ClimbRearS();
    m_ClimbCrawlerS = new ClimbCrawlerS();
    m_CargoShooterS = new CargoShooterS();
    m_WheelHatchMech = new WheelHatchMech();

    m_oi = new OI();

    m_ladderHomeC = new LadderHomeC();
  
    //m_ladderManualMoveC = new LadderManualMoveC();
    //m_ladderSetLevelC = new LadderSetLevelC(nextLevel);
    m_ladderMoveUpPIDC = new LadderMoveUpPIDC();
    m_ladderMoveDownPIDC = new LadderMoveDownPIDC();
    m_ladderHoldPIDC = new LadderHoldPIDC();
    m_hatchMechCG = new HatchMechCG();
    m_cargoShooterC = new CargoShooterC();

    m_ladderDisplayStatusC = new LadderDisplayStatusC();
    m_setCameraModeC = new setCameraMode();

    //Limelight setup to use camera
    CameraServer cs = CameraServer.getInstance();

    //usbCam.setResolution(320, 240);
    //cs.startAutomaticCapture(usbCam);

    HttpCamera limelight = new HttpCamera("limelightStream", "http://10.69.95.11:5800", HttpCameraKind.kMJPGStreamer);
    limelight.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
    cs.startAutomaticCapture(limelight);

    //Helpful for debugging; shows all running commands and what command is using subsystem
    SmartDashboard.putData(Scheduler.getInstance());
    SmartDashboard.putData(m_drivebaseS);
    SmartDashboard.putData(m_ladderS);
    SmartDashboard.putData(m_hatchMechS);
    SmartDashboard.putData(m_CargoShooterS);
    SmartDashboard.putData(m_setCameraModeC);
    SmartDashboard.putData("Run CargoShooterC", new CargoShooterC());
  }

  public void robotPeriodic() {
    m_ladderDisplayStatusC.start();
    m_ladderManualMoveC.start();
  }

  @Override
  public void disabledInit() {
    SmartDashboard.putBoolean("Cargo Limit Switch", Robot.m_CargoShooterS.getCargoLimit());
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_setCameraModeC.start();
    m_ladderHomeC.start();
  }

  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putBoolean("Cargo Limit Switch", Robot.m_CargoShooterS.getCargoLimit());
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    //m_setCameraModeC.start();
    m_ladderHomeC.start();
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putBoolean("Cargo Limit Switch", Robot.m_CargoShooterS.getCargoLimit());
    Scheduler.getInstance().run();
  }
  
  @Override
  public void testPeriodic() {
  }
}
