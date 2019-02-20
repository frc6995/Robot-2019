package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import frc.robot.subsystems.LadderS.LadderLevel;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

        //buttonBoard.thumb_runWhileHeld(new ClimbPlatformCG(buttonBoard.pinky()));
        buttonBoard.index_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_ONE));
        buttonBoard.middle_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_TWO));
        buttonBoard.ring_toggleOnPress(new HatchLevelScoreCG(LadderLevel.LEVEL_THREE));
        buttonBoard.thumb_runOnPress(new HatchIntakeC());

    }
}