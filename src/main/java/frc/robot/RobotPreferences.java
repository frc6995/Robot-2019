package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;;

public class RobotPreferences {

    RobotMap rm = new RobotMap();

    public void ButtonsCreate() {
        SmartDashboard.putString("x", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("y", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("a", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("b", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("dpad_up", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("dpad_down", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("dpad_left", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("dpad_right", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("start", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("back", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("left_bumper", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("right_bumper", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("left_stick_button", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("right_stick_button", RobotMap.LIST_OF_COMMANDS[0]);

        SmartDashboard.putString("left_stick_x", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("left_stick_y", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("right_stick_x", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("right_stick_y", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("left_trigger", RobotMap.LIST_OF_COMMANDS[0]);
        SmartDashboard.putString("right_trigger", RobotMap.LIST_OF_COMMANDS[0]);
    }

    public void GetCommand() {
        SmartDashboard.getString(key, defaultValue)
    }

}