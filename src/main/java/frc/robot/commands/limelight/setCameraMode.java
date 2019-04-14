package frc.robot.commands.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

public class setCameraMode extends Command {
  public setCameraMode() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.setTimeout(0.1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Limelight network table stuff
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ledMode = table.getEntry(("ledMode"));
    NetworkTableEntry pipelineEntry = table.getEntry("pipeline");

    //Force the pipeline to be 1 and the leds to be off
    pipelineEntry.setDouble(1);
    ledMode.setDouble(0);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return this.isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
