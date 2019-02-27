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

public class OI {
    public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    public final JStick stick = new JStick(RobotMap.OI_JOYSTICK);
    public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

    public OI() {
        //For testing purposes
        //For climber
        //SmartDashboard.putData("Climber Front DSol Toggle", new ClimbFrontToggleC());
        //SmartDashboard.putData("Climber Rear DSol Toggle", new ClimbRearToggleC());
        //SmartDashboard.putData("Climber Motors Forward", new ClimbMotorsForwardC());
        //SmartDashboard.putData("Climb Platform CG", new ClimbPlatformCG(true));
 
        // Mostly working sequence - Not using limit switches
        SmartDashboard.putData("Climb Test", new ClimbTestLiftCG());
        SmartDashboard.putData("Climber Motors Reverse", new ClimbMotorsReverseC()); //drivebase not moving
        SmartDashboard.putData("Rear Retract", new ClimbRearRetractC());
            // Crawler still running, use Xbox to move drivebase
        SmartDashboard.putData("Front Retract", new ClimbFrontRetractC());
        SmartDashboard.putData("Climber Motors Stop", new ClimbMotorsStopC());
        //more testing
        SmartDashboard.putBoolean("LimitRear Check", Robot.m_ClimbRearS.cSwitchRear());
        SmartDashboard.putBoolean("Limit FrontCheck", Robot.m_ClimbFrontS.cSwitchFront());
        SmartDashboard.putData("NEW Both Toggle", new ClimbBothToggleNC());
        SmartDashboard.putData("NEW Front Toggle", new ClimbFrontToggleNC());
        SmartDashboard.putData("NEW Rear Toggle", new ClimbRearToggleNC());

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
        buttonBoard.index_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_ONE));
        buttonBoard.middle_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_TWO));
        buttonBoard.ring_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_THREE));
        //Need to change this to everything but lift - TODO
        buttonBoard.pinky_runWhileHeld(new ClimbPlatformCG(buttonBoard.thumb()));

        //Joystick Assignments
        stick.button_1_toggleOnPress(new HatchMechToggleCG());
        stick.button_3_runOnPress(new ClimbRearRetractC());  //change to retract 2nd half - TODO
        stick.button_5_runOnPress(new ClimbFrontRetractC()); //change to retract 1st half - TODO
        //stick.button_7() - Holds Ladder position when manually operated
        //stick.button_8() - Moves Ladder up 
        stick.button_9_runOnPress(new ClimbMotorsStopC());
        //Need to run whilepressed if using Double Solenoid
        stick.button_11_runOnPress(new ClimbBothToggleC(stick.button_12()));
        //stick.stick_x() - Climb Manual - turn drivebase - TODO
        //stick.stick_y() - Climb Manual - drive forward - TODO
    }
}