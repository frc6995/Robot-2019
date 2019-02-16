package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.climb_manual.*;
import frc.robot.commands.climb.ClimbPlatformCG;
import frc.robot.commands.hatch.HatchMechToggleCG;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.ladder.LadderMovePIDC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderHomeC;
public class OI {

    public Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.BUTTON_HATCH_TOGGLE);

    public final Joystick buttonBoard = new Joystick(RobotMap.OI_BUTTONBOARD);

    public final JoystickButton ladderLevelOne = new JoystickButton(buttonBoard, RobotMap.BUTTONBOARD_LADDER_LEVEL_ONE);
    public final JoystickButton ladderLevelTwo = new JoystickButton(buttonBoard, RobotMap.BUTTONBOARD_LADDER_LEVEL_TWO);
    public final JoystickButton ladderLevelThree = new JoystickButton(buttonBoard, RobotMap.BUTTONBOARD_LADDER_LEVEL_THREE);
    public final JoystickButton climbPlatform = new JoystickButton(buttonBoard, RobotMap.BUTTONBOARD_CLIMB_TOGGLE);
    public final JoystickButton climbConfirmed = new JoystickButton(buttonBoard, RobotMap.BUTTONBOARD_CLIMB_CONFIRMED);

    public OI() {
        hatchToggle.toggleWhenPressed(new HatchMechToggleCG());
        climbPlatform.toggleWhenPressed(new ClimbPlatformCG(climbConfirmed.get()));

        //For testing purpose
        SmartDashboard.putData("Climber Front DSol Toggle", new ClimbFrontToggleC());
        SmartDashboard.putData("Climber Rear DSol Toggle", new ClimbRearToggleC());
        SmartDashboard.putData("Climber Motors Forward T", new ClimbMotorsReverseToggleC());
        SmartDashboard.putData("Climber Motors Forward", new ClimbMotorsForwardC());
        SmartDashboard.putData("Climber Motors Reverse", new ClimbMotorsReverseC());
        SmartDashboard.putData("Climber Motors Stop", new ClimbMotorsStopC());
        SmartDashboard.putData("Climb Platform CG", new ClimbPlatformCG(true));
               
        //backups
        //xbox.x_toggleOnPress(new ClimbFrontToggleC());
        //xbox.y_toggleOnPress(new ClimbRearToggleC());
        //xbox.a_toggleOnPress(new ClimbMotorsReverseToggleC());

        //xbox.dpad_up_runOnPressed(new ClimbMotorsForwardC());
        //xbox.dpad_up_runOnRelease(new ClimbMotorsStopC());
        //xbox.dpad_down_runOnPressed(new ClimbMotorsReverseC());
        //xbox.dpad_down_runOnRelease(new ClimbMotorsStopC());

        SmartDashboard.putData("LadderHoldPIDC", new LadderHoldPIDC());
        SmartDashboard.putData("LadderMovePIDC", new LadderMovePIDC());
        SmartDashboard.putData("LadderHomeC", new LadderHomeC());
        SmartDashboard.putData("Set Ladder to L2", new LadderSetLevelC(2));
        SmartDashboard.putData("Set Ladder to L3", new LadderSetLevelC(3));

        ladderLevelOne.whileHeld(new LadderSetLevelC(1));
        ladderLevelTwo.whileHeld(new LadderSetLevelC(2));
        ladderLevelThree.whileHeld(new LadderSetLevelC(3));

    }
}