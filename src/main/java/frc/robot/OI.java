package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ladder.LadderChangeLevelC;
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
    
    public OI() {
        //hatchToggle.toggleWhenPressed(new HatchMechToggleCG());

        SmartDashboard.putData("LadderHoldPIDC", new LadderHoldPIDC());
        SmartDashboard.putData("LadderMovePIDC", new LadderMovePIDC());
        SmartDashboard.putData("LadderHomePIDC", new LadderHomeC());
        SmartDashboard.putData("2", new LadderChangeLevelC(2));
        SmartDashboard.putData("3", new LadderChangeLevelC(3));

        xbox.x_runOnPressed(new LadderChangeLevelC(2));

        ladderLevelOne.whileHeld(new LadderChangeLevelC(1));
        ladderLevelTwo.whileHeld(new LadderChangeLevelC(2));
        ladderLevelThree.whileHeld(new LadderChangeLevelC(3));

    }
}