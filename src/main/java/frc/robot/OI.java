package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ladder.LadderChangeLevelC;
import frc.robot.commands.ladder.LadderMovePIDC;
import frc.robot.commands.ladder.LadderHoldPIDC;

public class OI {

    public Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.BUTTON_HATCH_TOGGLE);

    public final Joystick buttonBoard = new Joystick(RobotMap.OI_BUTTONBOARD);

    public final JoystickButton ladderLevelOne = new JoystickButton(buttonBoard, RobotMap.LADDER_LEVEL_ONE);
    public final JoystickButton ladderLevelTwo = new JoystickButton(buttonBoard, RobotMap.LADDER_LEVEL_TWO);
    public final JoystickButton ladderLevelThree = new JoystickButton(buttonBoard, RobotMap.LADDER_LEVEL_THREE);
    
    public OI() {
        //hatchToggle.toggleWhenPressed(new HatchMechToggleCG());
        xbox.y_toggleOnPress(new LadderHoldPIDC());
        xbox.x_toggleOnPress(new LadderMovePIDC());

        ladderLevelOne.whenPressed(new LadderChangeLevelC(1));
        ladderLevelTwo.whenPressed(new LadderChangeLevelC(2));
        ladderLevelThree.whenPressed(new LadderChangeLevelC(3));

    }
}