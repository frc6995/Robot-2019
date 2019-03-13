package frc.robot;

import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import frc.robot.subsystems.LadderS.LadderLevel;
import frc.robot.triggers.TestTriggerT;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.climb_test.ClimbBothToggleNC;
import frc.robot.commands.climb_backup.*;
import frc.robot.commands.Cargo.CargoIntakeC;
import frc.robot.commands.Cargo.CargoIntakeCG;
import frc.robot.commands.climb.*;
import frc.robot.commands.limelight.*;
import frc.robot.commands.trigger_test.HatchDeployTriggerC;
import frc.robot.commands.trigger_test.HatchMechTriggerCG;
import frc.robot.commands.hatch.*;
import frc.robot.commands.ladder.*;

public class OI {
    public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    public final JStick stick = new JStick(RobotMap.OI_JOYSTICK);
    public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);
    public final Trigger trigger = new TestTriggerT();

    public OI() {
        //FOR TESTING PURPOSES
        
        //SmartDashboard Commands for Emergency Use
        SmartDashboard.putData("Reset Ladder Encoder", new LadderResetEncoderC());
        SmartDashboard.putData("LadderHomeC", new LadderHomeC());

        //BUTTON ASSIGNMENTS - Place a comment for buttons used in other classes

        //Xbox Assignments
        //xbox.left_trigger()  -- DriveArcadeXboxC used for Driving Backwards
        //xbox.right_trigger() -- DriveArcadeXboxC used for Driving Forwards
        //xbox.left_bumper()   -- DriveArcadeXboxC used for decrementing throttle
        //xbox.right_bumper()  -- DriveArcadeXboxC used for setting throttle to max
        //xbox.left_stick_x()  -- DriveArcadeXboxC used for turning drivebase
        xbox.x_toggleOnPress(new VisionAlignCG());
        xbox.a_toggleOnPress(new VisionAlignRocketCargoCG());
        //Command group for aligning and starting the cargo intake.
        //xbox.y_toggleOnPress(new CargoIntakeCG());
        //command group for aligning at the higher level for the rocket cargo.
        //xbox.b_toggleOnPress(new VisionAlignRocketCargoCG());

        //Button Board Assignments
        buttonBoard.thumb_runOnPress(new HatchIntakeC());
        buttonBoard.index_toggleOnPress(new ScoreHatchOrCargoC(LadderLevel.LEVEL_ONE));
        buttonBoard.middle_toggleOnPress(new ScoreHatchOrCargoC(LadderLevel.LEVEL_TWO));
        buttonBoard.ring_toggleOnPress(new ScoreHatchOrCargoC(LadderLevel.LEVEL_THREE));
        //buttonBoard.pinky_runWhileHeld(new ClimbPlatformBetterCG(buttonBoard.thumb()));

        //Joystick Assignments
        stick.button_1_runOnPress(new HatchMechCG());
        //stick.button_3_runOnPress(new ClimbRetractStageC(2));
        //stick.button_5_runOnPress(new ClimbRetractStageC(1));
        stick.button_3_toggleOnPress(new CargoIntakeCG());
        //stick.button_6_runWhileHeld(new ClimbMotorsDriveC());
        //stick.button_7() - Holds Ladder position when manually operated
        //stick.button_8() - Moves Ladmder up
        //stick.button_9_runOnPress(new ClimbMotorsStopC());
        //stick.button_11_runWhileHeld(new ClimbBothDSLiftC(true));

        //New Trigger Testing
        //stick.button_11_runOnPress(new HatchDeployTriggerC()); //if button 12 is hit this deploys, else it retracts
        //stick.button_11_runOnPress(new HatchMechTriggerCG()); //if button 12 is also hit this runs normally.

        trigger.whenActive(new HatchMechCG());

        //stick.stick_x() - Climb Manual - turn drivebase
        //stick.stick_y() - Climb Manual - drive forward
    }
}