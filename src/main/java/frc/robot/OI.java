package frc.robot;

import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import frc.robot.subsystems.LadderS.LadderLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.climb_manual.*;
import frc.robot.commands.climb.ClimbPlatformCG;
import frc.robot.commands.limelight.*;
import frc.robot.commands.hatch.*;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderHomeC;

public class OI {

    public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    
    public final JStick stick = new JStick(RobotMap.OI_JOYSTICK);
    public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

    public OI() {
        stick.button_1_toggleOnPress(new HatchMechToggleCG());
        //stick.button_7_runOnPress(new HatchIntakeC());


        //backups
        //xbox.x_toggleOnPress(new ClimbFrontToggleC());
        //xbox.y_toggleOnPress(new ClimbRearToggleC());
        //xbox.a_toggleOnPress(new ClimbMotorsReverseToggleC());

        //xbox.dpad_up_runOnPressed(new ClimbMotorsForwardC());
        //xbox.dpad_up_runOnRelease(new ClimbMotorsStopC());
        //xbox.dpad_down_runOnPressed(new ClimbMotorsReverseC());
        //xbox.dpad_down_runOnRelease(new ClimbMotorsStopC());    
        
        xbox.x_toggleOnPress(new VisionAlignCG());

        //For testing purposes
        SmartDashboard.putData("Drive for 2 Secs", new DriveForTimeC(2,0.2));
        SmartDashboard.putData("Vision Align Target", new VisionAlignTargetC());
        SmartDashboard.putData("Vision Align CG", new VisionAlignCG());
        SmartDashboard.putData("LadderHoldPIDC", new LadderHoldPIDC());
        SmartDashboard.putData("LadderMovePIDC", new LadderMoveUpPIDC());
        SmartDashboard.putData("LadderHomeC", new LadderHomeC());
        SmartDashboard.putData("Set Ladder to vision", new LadderSetLevelC(LadderLevel.LEVEL_VISION));
        SmartDashboard.putData("Set Ladder to L3", new LadderSetLevelC(LadderLevel.LEVEL_THREE));
        SmartDashboard.putData("Hatch Intake", new HatchIntakeC());

        buttonBoard.pinky_runWhileHeld(new ClimbPlatformCG(buttonBoard.thumb()));
        buttonBoard.index_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_ONE));
        buttonBoard.middle_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_TWO));
        buttonBoard.ring_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_THREE));
        buttonBoard.thumb_runOnPress(new HatchIntakeC());

        //For climber
        SmartDashboard.putData("Climber Front DSol Toggle", new ClimbFrontToggleC());
        SmartDashboard.putData("Climber Rear DSol Toggle", new ClimbRearToggleC());
        SmartDashboard.putData("Climber Motors Forward", new ClimbMotorsForwardC());
        SmartDashboard.putData("Climb Platform CG", new ClimbPlatformCG(true));

        // Sequence did not work. Climb Both Toggle was constantly deploying
        SmartDashboard.putData("AClimb Both Toggle", new ClimbBothToggleC());
        SmartDashboard.putData("AClimb Motors Reverse", new ClimbMotorsReverseToggleC(true));
        SmartDashboard.putData("BClimb Rear Reverse til Limit", new ClimbRearReverseLimitC());
        SmartDashboard.putData("CClimb Rear til Retract", new ClimbRearReverseLimitRetractCG());
        SmartDashboard.putData("DClimb Front Reverse til Limit", new ClimbFrontReverseLimitC());
        SmartDashboard.putData("EClimb Front til Retract", new ClimbFrontReverseLimitRetractCG());

        // Mostly working sequence - Not using limit switches
        SmartDashboard.putData("Climb Test", new ClimbTestLiftCG());
        SmartDashboard.putData("Climber Motors Reverse", new ClimbMotorsReverseC()); //drivebase not moving
        SmartDashboard.putData("Rear Retract", new ClimbRearRetractC());
        // Crawler still running, use Xbox to move drivebase
        SmartDashboard.putData("Front Retract", new ClimbFrontRetractC());
        SmartDashboard.putData("Climber Motors Stop", new ClimbMotorsStopC());
        
        SmartDashboard.putBoolean("LimitRear Check", Robot.m_ClimbRearS.cSwitchRear());
        SmartDashboard.putBoolean("Limit FrontCheck", Robot.m_ClimbFrontS.cSwitchFront());

        stick.button_1_toggleOnPress(new HatchMechToggleCG());

        stick.button_2_toggleOnPress(new ClimbBothToggleC());

        stick.button_7_runOnPress(new ClimbFrontLowerC());
        stick.button_8_runOnPress(new ClimbRearLowerC());

        stick.button_9_runOnPress(new ClimbMotorsReverseToggleC(stick.button_10()));
        //stick.button_10_runOnPress(new ClimbMotorsReverseC());
        //stick.button_11_runOnPress(new ClimbMotorsReverseToggleC());
        //stick.button_12_runOnPress(new ClimbMotorsStopC());
    }
}