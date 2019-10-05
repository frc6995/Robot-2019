/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.DistanceStraightDriveC;
import frc.robot.commands.autonomous.PathFollowerC;
import frc.robot.commands.autonomous.TimedDriveC;
import frc.robot.commands.autonomous.WallSquareC;
import frc.robot.commands.limelight.VisionAlignAndDriveCG;

public class AutoHatchSideCargoShip extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoHatchSideCargoShip(boolean isLeft) {
    // Add Commands here:
    addSequential(new TimedDriveC(2, 1, 1));
    addSequential(new WallSquareC());
    if (isLeft) {
      addSequential(new PathFollowerC("leftHab2toLeftCargoClose")); //TODO add actual path
    } else {
      addSequential(new PathFollowerC("rightHab2toRightCargoClose"));
    } 
    addSequential(new VisionAlignAndDriveCG(false));
    addSequential(new DistanceStraightDriveC(1024, 102)); //Roughly 1 second
    addSequential(new HatchScoreCG());
    addSequential(new DistanceStraightDriveC(-1024, 102)); //Roughly 1 second

    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
