package frc.robot;

import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import frc.robot.subsystems.LadderS.LadderLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.climb_test.ClimbBothToggleNC;
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
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderHomeC;

public class OI {

    public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    
    public final JStick stick = new JStick(RobotMap.OI_JOYSTICK);
    public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

    public OI() {
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


        xbox.x_toggleOnPress(new VisionAlignCG());


        buttonBoard.pinky_runWhileHeld(new ClimbPlatformBetterCG(buttonBoard.thumb()));
        buttonBoard.index_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_ONE));
        buttonBoard.middle_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_TWO));
        buttonBoard.ring_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_THREE));
        buttonBoard.thumb_runOnPress(new HatchIntakeC());

        stick.button_1_toggleOnPress(new HatchMechToggleCG());

        stick.button_3_runOnPress(new ClimbRetractStageC(2));
        stick.button_5_runOnPress(new ClimbRetractStageC(1));

        stick.button_9_runOnPress(new ClimbMotorsStopC());
        stick.button_11_runOnPress(new ClimbBothLiftC(stick.button_12()));
    }
}