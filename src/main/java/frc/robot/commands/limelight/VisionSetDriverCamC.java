package frc.robot.commands.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class VisionSetDriverCamC extends Command {
  public VisionSetDriverCamC() {
  }

  @Override
  protected void initialize() {
    this.setTimeout(0.1);
  }

  @Override
  protected void execute() {
    //Gets the network tables
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ledMode = table.getEntry(("ledMode"));
    NetworkTableEntry pipelineEntry = table.getEntry("pipeline");

    //Force the pipeline to be the driver cam and the leds to be off
    pipelineEntry.setDouble(RobotMap.PIPELINE_DRIVER_CAM);
    ledMode.setDouble(0);
  }

  @Override
  protected boolean isFinished() {
    return this.isTimedOut();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
