package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.HatchScoreCG;
import frc.robot.commands.autonomous.TimedDriveC;
import frc.robot.commands.autonomous.WallSquareC;
import frc.robot.commands.cargo.*;
import frc.robot.commands.drive.DriveArcadeXboxC;
import frc.robot.commands.hatch.*;
import frc.robot.commands.ladder.*;
import frc.robot.commands.AutoHatchSideCargoShipCG;
import frc.robot.commands.BasicRightTurnAutoCG;

import frc.robot.commands.HatchIntakeCG;
import frc.robot.commands.limelight.VisionSetDriverCamC;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static SendableChooser<Command> autoChooser;
  // Subsystems
  public static DrivebaseS m_drivebaseS;
  public static LadderS m_ladderS;
  public static CargoShooterS m_CargoShooterS;
  public static HatchMechDrawerS m_hatchMechDrawerS;
  public static HatchMechWheelsS m_hatchMechWheelsS;
  public static LimeLight m_limelight;

  // Ladder commands
  public static Command m_ladderHomeC;
  public static Command m_ladderDisplayStatusC;
  public static Command m_ladderMoveUpPIDC;
  public static Command m_ladderMoveDownPIDC;
  public static Command m_ladderHoldPIDC;
  // Hatch/Cargo commands
  public static Command m_hatchDrawerDeployC;
  public static Command m_hatchDrawerRetractC;
  public static Command m_cargoScoreC;
  public static Command m_cargoIntakeC;
  public static Command m_hatchDrawerToggleC;
  public static CommandGroup m_hatchIntakeCG;
  public static CommandGroup m_hatchScoreCG;
  public static CommandGroup m_basicAutoCG;
  public static Command m_wallSquareC;
  // Limelight
  public static Command m_visionSetDriverCamC;

  public static OI m_oi;

  public PowerDistributionPanel m_PDP;

  @Override
  public void robotInit() {
    // Subsystems
    autoChooser = new SendableChooser<>();
    m_drivebaseS = new DrivebaseS();
    m_ladderS = new LadderS();
    m_CargoShooterS = new CargoShooterS();
    m_hatchMechDrawerS = new HatchMechDrawerS();
    m_hatchMechWheelsS = new HatchMechWheelsS();
    m_limelight = new LimeLight();

    // Ladder commands
    m_ladderHomeC = new LadderHomeC();
    m_ladderMoveUpPIDC = new LadderMoveUpPIDC();
    m_ladderMoveDownPIDC = new LadderMoveDownPIDC();
    m_ladderHoldPIDC = new LadderHoldPIDC();
    m_ladderDisplayStatusC = new LadderDisplayStatusC();
    // Cargo/Hatch commands
    m_hatchDrawerDeployC = new HatchDrawerDeployC();
    m_hatchDrawerRetractC = new HatchDrawerRetractC();
    m_cargoIntakeC = new CargoIntakeC();
    m_cargoScoreC = new CargoScoreC();
    m_hatchDrawerToggleC = new HatchDrawerToggleC();
    m_hatchIntakeCG = new HatchIntakeCG();
    m_hatchScoreCG = new HatchScoreCG();
    // Limelight commands
    m_visionSetDriverCamC = new VisionSetDriverCamC();
 
    m_wallSquareC = new WallSquareC();

    m_oi = new OI();
    m_PDP = new PowerDistributionPanel();
    // SmartDashboard.putData("PDP", m_PDP);

    autoChooser.addDefault("Driver Control", new DriveArcadeXboxC());
    //autoChooser.addObject("leftCS", new AutoHatchSideCargoShipCG(true));
    //autoChooser.addObject("Right Turn", new BasicRightTurnAutoCG());
    SmartDashboard.putData("Auto Mode", autoChooser);
  }

  public void robotPeriodic() {
    //Driver feedback
    m_ladderDisplayStatusC.start();
    SmartDashboard.putNumber("Drivebase Encoder A", m_drivebaseS.getLeftEncoder());
    SmartDashboard.putNumber("Drivebase Encoder B", m_drivebaseS.getRightEncoder());
    SmartDashboard.putBoolean("Has Cargo", Robot.m_CargoShooterS.getCargoLimit());
    SmartDashboard.putBoolean("Has Hatch", Robot.m_hatchMechWheelsS.getHatchLimit());
    SmartDashboard.putNumber("Current Encoder Count", m_ladderS.getLadderEncoderCount());
    SmartDashboard.putNumber("YAW", m_drivebaseS.navX.getAngle());
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("Current Encoder Error", m_ladderS.getError());
    SmartDashboard.putNumber("Current Encoder Count", m_ladderS.getLadderEncoderCount());
    SmartDashboard.putBoolean("Ladder limit", m_ladderS.lowerLimitSwitchPressed());
  }

  @Override
  public void autonomousInit() {
    autoChooser.getSelected().start();
    //Force the correct camera mode
    m_visionSetDriverCamC.start();
    //Home the ladder
    m_ladderHomeC.start();
    
    
  }

  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("Current Encoder Count", m_ladderS.getLadderEncoderCount());
    SmartDashboard.putNumber("Current Encoder Error", m_ladderS.getError());
    SmartDashboard.putBoolean("Ladder limit", m_ladderS.lowerLimitSwitchPressed());
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    //m_ladderHomeC.start();
    //Force the correct camera mode
    m_visionSetDriverCamC.start();
  }

  @Override
  public void teleopPeriodic() {
    
    SmartDashboard.putNumber("Jerk", (m_drivebaseS.getLeftEncoder()+m_drivebaseS.getRightEncoder())/2);
    SmartDashboard.putNumber("Current Encoder Count", m_ladderS.getLadderEncoderCount());
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("Current Encoder Error", m_ladderS.getError());
    SmartDashboard.putBoolean("Ladder limit", m_ladderS.lowerLimitSwitchPressed());
  }

  @Override
  public void testPeriodic() {
    SmartDashboard.putNumber("Current Encoder Count", m_ladderS.getLadderEncoderCount());
    SmartDashboard.putBoolean("Ladder limit", m_ladderS.lowerLimitSwitchPressed());
  }
}
