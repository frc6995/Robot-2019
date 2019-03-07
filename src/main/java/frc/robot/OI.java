package frc.robot;

import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import frc.robot.subsystems.LadderS.LadderLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.climb_test.ClimbBothToggleNC;
import frc.robot.commands.climb_backup.*;
import frc.robot.commands.climb.*;
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
        SmartDashboard.putData("Climber Motors Forward", new ClimbMotorsForwardC()); //relative to climb speed
        SmartDashboard.putData("Climber Motors Reverse", new ClimbMotorsReverseC()); //relative to climb speed
        SmartDashboard.putData("Climber Motors Stop", new ClimbMotorsStopC());
        SmartDashboard.putData("Climber Motors Start", new ClimbMotorsDriveC());
        SmartDashboard.putBoolean("LimitRear Check", Robot.m_ClimbRearS.cSwitchRear());
        SmartDashboard.putBoolean("LimitFront Check", Robot.m_ClimbFrontS.cSwitchFront());
        SmartDashboard.putData("Front Retract", new ClimbFrontRetractC());
        SmartDashboard.putData("Rear Retract", new ClimbRearRetractC());
        SmartDashboard.putData("ClimbPlatformBetterCG", new ClimbPlatformBetterCG(true));
        SmartDashboard.putData("Climb Lift (SS)", new ClimbBothLiftC(true)); 
        SmartDashboard.putData("Climb LIft (DS)", new ClimbBothDSLiftC(true));
        
        //more testing
        SmartDashboard.putData("NEW Both Toggle", new ClimbBothToggleNC());

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
        buttonBoard.pinky_runWhileHeld(new ClimbPlatformBetterCG(buttonBoard.thumb()));

        //Joystick Assignments
        stick.button_1_runOnPress(new HatchMechCG());
        stick.button_3_runOnPress(new ClimbRetractStageC(2));
        stick.button_5_runOnPress(new ClimbRetractStageC(1));
        stick.button_6_runWhileHeld(new ClimbMotorsDriveC());
        //stick.button_7() - Holds Ladder position when manually operated
        //stick.button_8() - Moves Ladmder up
        stick.button_9_runOnPress(new ClimbMotorsStopC());
        stick.button_11_runWhileHeld(new ClimbBothDSLiftC(true));
        //stick.button_11_runOnPress(new ClimbBothLiftC(stick.button_12()));
        //stick.stick_x() - Climb Manual - turn drivebase
        //stick.stick_y() - Climb Manual - drive forward
    }
}