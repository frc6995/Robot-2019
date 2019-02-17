package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.limelight.*;
import frc.robot.commands.hatch.HatchLevelScoreCG;
import frc.robot.commands.hatch.HatchMechToggleCG;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.ladder.LadderMovePIDC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderHomeC;
public class OI {

    public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    
    public final JStick stick = new JStick(RobotMap.OI_JOYSTICK);

    public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);
    
    public OI() {
        stick.button_1_toggleOnPress(new HatchMechToggleCG());
        xbox.x_toggleOnPress(new VisionAlignCG());

        //For testing purposes
        SmartDashboard.putData("Drive for 3 Secs", new DriveForTimeC(3));
        SmartDashboard.putData("Vision Align Target", new VisionAlignTargetC());
        SmartDashboard.putData("Vision Align CG", new VisionAlignCG());
        SmartDashboard.putData("LadderHoldPIDC", new LadderHoldPIDC());
        SmartDashboard.putData("LadderMovePIDC", new LadderMovePIDC());
        SmartDashboard.putData("LadderHomeC", new LadderHomeC());
        SmartDashboard.putData("Set Ladder to L2", new LadderSetLevelC(2));
        SmartDashboard.putData("Set Ladder to L3", new LadderSetLevelC(3));

        buttonBoard.thumb_runWhileHeld(new HatchLevelScoreCG(1));
        buttonBoard.index_runWhileHeld(new HatchLevelScoreCG(2));
        buttonBoard.middle_runWhileHeld(new HatchLevelScoreCG(3));

    }
}
