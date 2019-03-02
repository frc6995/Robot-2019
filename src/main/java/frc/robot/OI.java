package frc.robot;

import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import frc.robot.subsystems.LadderS.LadderLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.climb_test.ClimbBothToggleNC;
import frc.robot.commands.climb_test.ClimbMotorsStartC;
import frc.robot.commands.climb_test.ClimbPlatformBetterCG;
import frc.robot.commands.climb_test.ClimbRetractStageC;
import frc.robot.commands.climb.ClimbMotorsStopC;
import frc.robot.commands.climb.ClimbPlatformCG;
import frc.robot.commands.climb_backup.ClimbBothLiftC;
import frc.robot.commands.climb_backup.ClimbFrontRetractC;
import frc.robot.commands.climb_backup.ClimbMotorsForwardC;
import frc.robot.commands.climb_backup.ClimbMotorsReverseC;
import frc.robot.commands.climb_backup.ClimbRearRetractC;
import frc.robot.commands.limelight.*;
import frc.robot.commands.hatch.*;

public class OI {
    public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    public final JStick stick = new JStick(RobotMap.OI_JOYSTICK);
    public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

    public OI() {
        //For testing purposes
        //For climber
        SmartDashboard.putData("Climber Motors Forward", new ClimbMotorsForwardC());
        SmartDashboard.putData("Climber Motors Reverse", new ClimbMotorsReverseC()); //drivebase not moving
        SmartDashboard.putData("Climber Motors Stop", new ClimbMotorsStopC());

        SmartDashboard.putBoolean("LimitRear Check", Robot.m_ClimbRearS.cSwitchRear());
        SmartDashboard.putBoolean("LimitFront Check", Robot.m_ClimbFrontS.cSwitchFront());

        SmartDashboard.putData("Front Retract", new ClimbFrontRetractC());
        SmartDashboard.putData("Rear Retract", new ClimbRearRetractC());

        SmartDashboard.putData("Climb Platform CG", new ClimbPlatformCG(true));
        SmartDashboard.putData("Better ClimbPlatformCG", new ClimbPlatformBetterCG(true));
        // Mostly working sequence - Not using limit switches
        SmartDashboard.putData("Climb Lift", new ClimbBothLiftC(true)); //was ClimbTestLiftCG
        
        //more testing
        SmartDashboard.putData("NEW Both Toggle", new ClimbBothToggleNC());

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
        buttonBoard.pinky_runWhileHeld(new ClimbPlatformBetterCG(buttonBoard.thumb()));

        //Joystick Assignments
        stick.button_1_toggleOnPress(new HatchMechToggleCG());
        stick.button_3_runOnPress(new ClimbRetractStageC(2));
        stick.button_5_runOnPress(new ClimbRetractStageC(1));
        stick.button_6_runWhileHeld(new ClimbMotorsStartC());
        //stick.button_7() - Holds Ladder position when manually operated
        //stick.button_8() - Moves Ladder up 
        stick.button_9_runOnPress(new ClimbMotorsStopC());
        //Need to run whilepressed if using Double Solenoid
        stick.button_11_runOnPress(new ClimbBothLiftC(stick.button_12()));
        //stick.stick_x() - Climb Manual - turn drivebase
        //stick.stick_y() - Climb Manual - drive forward
    }
}