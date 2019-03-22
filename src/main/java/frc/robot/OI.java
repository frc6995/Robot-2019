package frc.robot;

import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.Xbox;
import frc.robot.subsystems.LadderS.LadderLevel;
import frc.robot.commands.CargoIntakeCG;
import frc.robot.commands.LadderLevelCargoScoreCG;
import frc.robot.commands.HatchIntakeCG;
import frc.robot.commands.LadderLevelHatchScoreCG;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.limelight.*;
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
        xbox.x_toggleOnPress(new VisionAlignCG());
        xbox.a_toggleOnPress(new VisionAlignRocketCargoCG());
        //Command group for aligning and starting the cargo intake.
        //xbox.y_toggleOnPress(new CargoIntakeCG());
        //command group for aligning at the higher level for the rocket cargo.
        //xbox.b_toggleOnPress(new VisionAlignRocketCargoCG());

        //Button Board Assignments (ASSIGN COMMANDS TO BUTTONS)
        // new CargoIntakeCG()
        // new LadderLevelCargoScoreCG(LadderLevel.LEVEL_ONE)
        // new LadderLevelCargoScoreCG(LadderLevel.LEVEL_TWO)
        // new LadderLevelCargoScoreCG(LadderLevel.LEVEL_THREE)

        // new HatchIntakeCG()
        // new LadderLevelCHatchScoreCG(LadderLevel.LEVEL_ONE)
        // new LadderLevelCHatchScoreCG(LadderLevel.LEVEL_TWO)
        // new LadderLevelCHatchScoreCG(LadderLevel.LEVEL_THREE)
        

        //Joystick Assignments
        stick.button_1_runOnPress(new HatchMechCG());
        //stick.button_3_runOnPress(new ClimbRetractStageC(2));
        //stick.button_5_runOnPress(new ClimbRetractStageC(1));
        stick.button_3_toggleOnPress(new CargoIntakeC());
        //stick.button_6_runWhileHeld(new ClimbMotorsDriveC());
        //stick.button_7() - Holds Ladder position when manually operated
        //stick.button_8() - Moves Ladmder up
        //stick.button_9_runOnPress(new ClimbMotorsStopC());
        //stick.button_11_runWhileHeld(new ClimbBothDSLiftC(true));
        //stick.button_11_runOnPress(new ClimbBothLiftC(stick.button_12()));
        //stick.stick_x() - Climb Manual - turn drivebase
        //stick.stick_y() - Climb Manual - drive forward
    }
}