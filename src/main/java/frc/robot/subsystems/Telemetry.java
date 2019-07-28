package frc.robot.subsystems;

import frc.robot.*;
import frc.robot.commands.CargoIntakeCG;
import frc.robot.commands.HatchIntakeCG;
import frc.robot.commands.HatchScoreCG;
import frc.robot.commands.LadderLevelCargoScoreCG;
import frc.robot.commands.LadderLevelHatchScoreCG;
import frc.robot.commands.cargo.CargoIntakeC;
import frc.robot.commands.cargo.CargoScoreC;
import frc.robot.commands.drive.DriveArcadeXboxC;
import frc.robot.commands.drive.DriveForTimeC;
import frc.robot.commands.hatch.HatchDrawerDeployC;
import frc.robot.commands.hatch.HatchDrawerRetractC;
import frc.robot.commands.hatch.HatchDrawerToggleC;
import frc.robot.commands.hatch.HatchRunWheelsForTimeC;
import frc.robot.commands.ladder.LadderDisplayStatusC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderHomeC;
import frc.robot.commands.ladder.LadderManualMoveC;
import frc.robot.commands.ladder.LadderMoveDownC;
import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderResetEncoderC;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.limelight.VisionAlignAndDriveCG;
import frc.robot.commands.limelight.VisionAlignTargetC;
import frc.robot.commands.limelight.VisionSetDriverCamC;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.drive.DriveArcadeXboxC;

public class Telemetry extends Subsystem {
 
  private double driveTime = 3;
  private double drivePower = 0.5;
  private double hatchPower = 0.5;
  private double hatchTimeout = 1;

public Telemetry() {

  //commands
  //cargo
  SmartDashboard.putData("CargoIntakeC", new CargoIntakeC());
  SmartDashboard.putData("CargoScoreC", new CargoScoreC());
  //drive
  SmartDashboard.putData("DriveArcadeXboxC", new DriveArcadeXboxC());
  SmartDashboard.putNumber("Drive Time", driveTime);
  SmartDashboard.putNumber("Drive Power", drivePower);
  //TODO - SmartDashboard.putData("DriveForTimeC", new DriveForTimeC(driveTime, drivePower);
  //hatch
  SmartDashboard.putData("HatchDrawerDeployC", new HatchDrawerDeployC());
  SmartDashboard.putData("HatchDrawerRetractC", new HatchDrawerRetractC());
  SmartDashboard.putData("HatchDrawerToggleC", new HatchDrawerToggleC());
  SmartDashboard.putNumber("Hatch Power", hatchPower);
  SmartDashboard.putNumber("Hatch Timeout", hatchTimeout);
 //TODO - SmartDashboard.putData("HatchRunWheelsForTimeC", new HatchRunWheelsForTimeC(hatchPower, hatchTimeout));
  //ladder
  SmartDashboard.putData("LadderDisplayStatusC", new LadderDisplayStatusC());
  SmartDashboard.putData("LadderHoldPIDC", new LadderHoldPIDC());
  SmartDashboard.putData("LadderHomeC", new LadderHomeC());
  SmartDashboard.putData("LadderManualMoveC", new LadderManualMoveC());
  SmartDashboard.putData("LadderMoveDownC", new LadderMoveDownC());
  SmartDashboard.putData("LadderMoveDownPIDC", new LadderMoveDownPIDC());
  SmartDashboard.putData("LadderMoveUpPIDC", new LadderMoveUpPIDC());
  SmartDashboard.putData("LadderResetEncoderC", new LadderResetEncoderC());
 //TODO -  SmartDashboard.putData("LadderSetLevelC", new LadderSetLevelC(fix me please or else ;{  ));
  //limelight
  //TODO - SmartDashboard.putData("VisionAlignAndDriveCG", new VisionAlignAndDriveCG(rocketCargo));
  //TODO -SmartDashboard.putData("VisionAlignTargetC", new VisionAlignTargetC(rocketCargo));
  SmartDashboard.putData("VisionSetDriverCamC", new VisionSetDriverCamC());
  //CGs
  SmartDashboard.putData("CargoIntakeCG", new CargoIntakeCG());
  SmartDashboard.putData("HatchIntakeCG", new HatchIntakeCG());
  SmartDashboard.putData("HatchScoreCG", new HatchScoreCG());
  //TODO -SmartDashboard.putData("LadderLevelCargoScoreCG", new LadderLevelCargoScoreCG(level));
  //todo - this - SmartDashboard.putData("LadderLevelHatchScoreCG", new LadderLevelHatchScoreCG(level));
}

  }
