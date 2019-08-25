package frc.robot;

import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.Xbox;
import frc.robot.subsystems.LadderS.LadderLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.limelight.*;
import frc.robot.commands.CargoIntakeCG;
import frc.robot.commands.HatchIntakeCG;
import frc.robot.commands.HatchScoreCG;
import frc.robot.commands.LadderLevelCargoScoreCG;
import frc.robot.commands.LadderLevelHatchScoreCG;
import frc.robot.commands.cargo.*;
import frc.robot.commands.hatch.*;
import frc.robot.commands.ladder.*;
import frc.robot.Robot;


public class OI {
    public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
    //public final JStick stick = new JStick(RobotMap.OI_JOYSTICK);
    public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

    public OI() {
        //FOR TESTING PURPOSES

        //SmartDashboard Commands for Emergency Use
        SmartDashboard.putData("Reset Ladder Encoder", new LadderResetEncoderC());
        SmartDashboard.putData("LadderHomeC", new LadderHomeC());

        SmartDashboard.putData("Hatch Intake", new HatchIntakeCG());
        SmartDashboard.putData("Hatch Score", new HatchScoreCG());
        SmartDashboard.putData("Cargo Intake", new CargoIntakeC());
        SmartDashboard.putData("Cargo Score", new CargoScoreC());

        SmartDashboard.putData("Ladder move up", new LadderMoveUpPIDC());
        SmartDashboard.putData("Ladder Hold PID", new LadderHoldPIDC());
        SmartDashboard.putData("Ladder move down", new LadderMoveDownC());
        SmartDashboard.putData("Ladder set level 2", new LadderSetLevelC(LadderLevel.LEVEL_TWO));
        SmartDashboard.putData("Ladder set level 3", new LadderSetLevelC(LadderLevel.LEVEL_THREE));
        SmartDashboard.putData("Level 2 Cargo", new LadderLevelCargoScoreCG(LadderLevel.LEVEL_TWO));
        SmartDashboard.putData("Level 2 Hatch", new LadderLevelHatchScoreCG(LadderLevel.LEVEL_TWO));
        SmartDashboard.putData("Level 3 Hatch", new LadderLevelHatchScoreCG(LadderLevel.LEVEL_THREE));
        SmartDashboard.putData("Ladder Cargo Intake", new CargoIntakeCG());

         //BUTTON ASSIGNMENTS - Place a comment for buttons used in other classes

        //Xbox Assignments
        //xbox.left_trigger()  -- DriveArcadeXboxC used for Driving Backwards
        //xbox.right_trigger() -- DriveArcadeXboxC used for Driving Forwards
        //xbox.left_bumper()   -- DriveArcadeXboxC used for decrementing throttle
        //xbox.right_bumper()  -- DriveArcadeXboxC used for setting throttle to max
        //xbox.left_stick_x()  -- DriveArcadeXboxC used for turning drivebase
        xbox.x_toggleOnPress(new VisionAlignAndDriveCG(false)); //Hatch + CargoShip
        xbox.y_runOnPressed(new LadderLevelHatchScoreCG(LadderLevel.LEVEL_TWO));
        xbox.a_toggleOnPress(new VisionAlignAndDriveCG(true)); //Rocket cargo
        xbox.b_runWhileHeld(new LadderManualMoveC());

        //Button Board Assignments (ASSIGN COMMANDS TO BUTTONS)
        buttonBoard.right_top_runOnPress(new LadderLevelCargoScoreCG(LadderLevel.LEVEL_CARGO_SHIP));
        buttonBoard.right_index_toggleOnPress(Robot.m_cargoScoreC);
        buttonBoard.right_middle_toggleOnPress(new LadderLevelCargoScoreCG(LadderLevel.LEVEL_TWO));
        buttonBoard.right_ring_toggleOnPress(new LadderLevelCargoScoreCG(LadderLevel.LEVEL_THREE));
        buttonBoard.right_bottom_toggleOnPress(new CargoIntakeCG());

        buttonBoard.left_top_runOnPress(new HatchRunWheelsForTimeC(1,2)); //Intake
        buttonBoard.left_index_toggleOnPress(Robot.m_hatchScoreCG);
        buttonBoard.left_middle_toggleOnPress(new LadderLevelHatchScoreCG(LadderLevel.LEVEL_TWO));
        buttonBoard.left_ring_toggleOnPress(new LadderLevelHatchScoreCG(LadderLevel.LEVEL_THREE));
        buttonBoard.left_bottom_runOnPress(Robot.m_hatchIntakeCG);
    }
}
