package frc.robot;

import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import frc.robot.subsystems.LadderS.LadderLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.limelight.*;
import frc.robot.commands.HatchIntakeCG;
import frc.robot.commands.LadderLevelCargoScoreCG;
import frc.robot.commands.LadderLevelHatchScoreCG;
import frc.robot.commands.cargo.CargoIntakeC;
import frc.robot.commands.hatch.*;
import frc.robot.commands.ladder.*;

public class OI {
    public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    public final JStick stick = new JStick(RobotMap.OI_JOYSTICK);
    public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

    public OI() {
        //FOR TESTING PURPOSES

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
        xbox.x_runWhileHeld(new VisionAlignAndDriveCG(false)); //Hatch + CargoShip
        xbox.a_runWhileHeld(new VisionAlignAndDriveCG(true)); //Rocket cargo

        //Button Board Assignments (ASSIGN COMMANDS TO BUTTONS)
        buttonBoard.right_top_runOnPress(new HatchDrawerToggleC());
        buttonBoard.right_index_runOnPress(new LadderLevelCargoScoreCG(LadderLevel.LEVEL_ONE));
        buttonBoard.right_middle_runOnPress(new LadderLevelCargoScoreCG(LadderLevel.LEVEL_TWO));
        buttonBoard.right_ring_runOnPress(new LadderLevelCargoScoreCG(LadderLevel.LEVEL_THREE));
        buttonBoard.right_bottom_runOnPress(new CargoIntakeC());

        buttonBoard.left_top_runOnPress(new HatchRunWheelsForTimeC(1,2)); //Intake
        buttonBoard.left_index_runOnPress(new LadderLevelHatchScoreCG(LadderLevel.LEVEL_ONE));
        buttonBoard.left_middle_runOnPress(new LadderLevelHatchScoreCG(LadderLevel.LEVEL_TWO));
        buttonBoard.left_ring_runOnPress(new LadderLevelHatchScoreCG(LadderLevel.LEVEL_THREE));
        buttonBoard.left_bottom_runOnPress(new HatchIntakeCG());

        //SmartDashboard
        SmartDashboard.putData("Hatch Intake", new HatchRunWheelsForTimeC(0.5,1));
        SmartDashboard.putData("Hatch Output", new HatchRunWheelsForTimeC(-0.5,1));
    }
}
