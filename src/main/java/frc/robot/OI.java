package frc.robot;

import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import frc.robot.subsystems.LadderS.LadderLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.climb_manual.*;
import frc.robot.commands.climb_test.ClimbBothToggleNC;
import frc.robot.commands.climb_test.ClimbFrontToggleNC;
import frc.robot.commands.climb_test.ClimbRearToggleNC;
import frc.robot.commands.climb.ClimbPlatformCG;
import frc.robot.commands.limelight.*;
import frc.robot.commands.hatch.*;
import frc.robot.commands.ladder.*;

public class OI {
    public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    public final JStick stick = new JStick(RobotMap.OI_JOYSTICK);
    public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

    public OI() {
        //FOR TESTING PURPOSES
        //For climber
        // Mostly working sequence from 2/19/19 - Not using limit switches
        SmartDashboard.putData("Climb Test", new ClimbTestLiftCG());
        SmartDashboard.putData("Climber Motors Reverse", new ClimbMotorsReverseC()); //drivebase not moving
        SmartDashboard.putData("Rear Retract", new ClimbRearRetractC());
            // Crawler still running, used Xbox to move drivebase
        SmartDashboard.putData("Front Retract", new ClimbFrontRetractC());
        SmartDashboard.putData("Climber Motors Stop", new ClimbMotorsStopC());
        //For Testing at competition
        SmartDashboard.putBoolean("LimitRear Check", Robot.m_ClimbRearS.cSwitchRear());
        SmartDashboard.putBoolean("Limit FrontCheck", Robot.m_ClimbFrontS.cSwitchFront());
        SmartDashboard.putData("NEW Both Toggle", new ClimbBothToggleNC());
        SmartDashboard.putData("NEW Front Toggle", new ClimbFrontToggleNC());
        SmartDashboard.putData("NEW Rear Toggle", new ClimbRearToggleNC());

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

        //Button Board Assignments
        buttonBoard.thumb_runOnPress(new HatchIntakeC());
        buttonBoard.index_toggleOnPress(new LadderLevelScoreCG(buttonBoard.thumb(), LadderLevel.LEVEL_ONE));
        buttonBoard.middle_toggleOnPress(new LadderLevelScoreCG(buttonBoard.thumb(), LadderLevel.LEVEL_TWO));
        buttonBoard.ring_toggleOnPress(new LadderLevelScoreCG(buttonBoard.thumb(), LadderLevel.LEVEL_THREE));
        //Need to change this to everything but lift - TODO
        buttonBoard.pinky_runWhileHeld(new ClimbPlatformCG(buttonBoard.thumb()));

        //Joystick Assignments
        stick.button_1_runOnPress(new HatchMechCG());
        stick.button_3_runOnPress(new ClimbRearRetractC());  //change to retract 2nd half - TODO
        stick.button_5_runOnPress(new ClimbFrontRetractC()); //change to retract 1st half - TODO
        //stick.button_6_runWhileHeld(new ClimbMotorsStartC()); - TODO
        //stick.stick_x() - ClimbMotorsStartC - turn drivebase - TODO
        //stick.stick_y() - ClimbMotorsStartC - drive forward - TODO
        //stick.button_7() - Holds Ladder position when manually operated
        //stick.button_8() - Moves Ladder up 
        stick.button_9_runOnPress(new ClimbMotorsStopC());
        //Need to run whilepressed if using Double Solenoid
        stick.button_11_runOnPress(new ClimbBothToggleC(stick.button_12()));

    }
}