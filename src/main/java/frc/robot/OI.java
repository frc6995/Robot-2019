package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.climb_manual.*;
import frc.robot.commands.climb.ClimbPlatformCG;
import frc.robot.commands.limelight.*;
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
        SmartDashboard.putData("Drive for 3 Secs", new DriveForTimeC(3));
        SmartDashboard.putData("Vision Align Target", new VisionAlignTargetC());
        SmartDashboard.putData("Vision Align CG", new VisionAlignCG());
        SmartDashboard.putData("LadderHoldPIDC", new LadderHoldPIDC());
        SmartDashboard.putData("LadderMovePIDC", new LadderMovePIDC());
        SmartDashboard.putData("LadderHomeC", new LadderHomeC());
        SmartDashboard.putData("Set Ladder to L2", new LadderSetLevelC(2));
        SmartDashboard.putData("Set Ladder to L3", new LadderSetLevelC(3));
        //For climber
        SmartDashboard.putData("Climber Front DSol Toggle", new ClimbFrontToggleC());
        SmartDashboard.putData("Climber Rear DSol Toggle", new ClimbRearToggleC());
        SmartDashboard.putData("Climber Motors Forward T", new ClimbMotorsReverseToggleC());
        SmartDashboard.putData("Climber Motors Forward", new ClimbMotorsForwardC());
        SmartDashboard.putData("Climber Motors Reverse", new ClimbMotorsReverseC());
        SmartDashboard.putData("Climber Motors Stop", new ClimbMotorsStopC());
        SmartDashboard.putData("Climb Platform CG", new ClimbPlatformCG(true));
                       

        buttonBoard.thumb_runWhileHeld(new LadderSetLevelC(1));
        buttonBoard.index_runWhileHeld(new LadderSetLevelC(2));
        buttonBoard.middle_runWhileHeld(new LadderSetLevelC(3));
        buttonBoard.ring_toggleOnPress(new ClimbPlatformCG(buttonBoard.pinky()));

        stick.button_1_toggleOnPress(new HatchMechToggleCG());
        stick.button_7_runOnPress(new ClimbFrontToggleC());
        stick.button_8_runOnPress(new ClimbRearToggleC());
        stick.button_9_runOnPress(new ClimbMotorsForwardC());
        stick.button_10_runOnPress(new ClimbMotorsReverseC());
        stick.button_11_runOnPress(new ClimbMotorsReverseToggleC());
        stick.button_12_runOnPress(new ClimbMotorsStopC());

    }
}
