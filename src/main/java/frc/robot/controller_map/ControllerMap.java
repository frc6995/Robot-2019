package frc.robot.controller_map;

import java.util.HashMap;
import java.util.Map;

import frc.robot.commands.AlignTargetC;
import frc.robot.commands.DriveArcadeXbox2C;
import frc.robot.commands.DriveArcadeXboxC;

public class ControllerMap {

    public static String[] LIST_OF_COMMANDS = {
        "",
        "AlignTargetC", 
        "DrievArcadeXbox2C",
        "DriveArcadeXboxC"
        
      };
      
      public final Map<String,Integer> xBoxButtons;
      public final String[] xBoxDPad;
      public final String[] xBoxAxis;
      public final Map<String,Object> commandsList;

      public ControllerMap() {
        xBoxButtons = new HashMap<>();
        xBoxDPad = new String[7];
        xBoxAxis = new String[5];
        commandsList = new HashMap<>();

        xBoxButtons.put("A", 1);
        xBoxButtons.put("B", 2);
        xBoxButtons.put("X", 3);
        xBoxButtons.put("Y", 4);
        xBoxButtons.put("Left Bumper", 5);
        xBoxButtons.put("Right Bumper", 6);
        xBoxButtons.put("Start", 7);
        xBoxButtons.put("Back", 8);
        xBoxButtons.put("Left Stick Button", 9);
        xBoxButtons.put("Right Stick Button", 10);

        xBoxDPad[0] = "Dpad_Up";
        xBoxDPad[1] = "Dpad_Down";
        xBoxDPad[2] = "Dpad_Left";
        xBoxDPad[3] = "Dpad_Right";
        xBoxDPad[4] = "Dpad_UpLeft";
        xBoxDPad[5] = "Dpad_UpRight";
        xBoxDPad[6] = "Dpad_DownLeft";
        xBoxDPad[7] = "Dpad_DownRight";

        xBoxAxis[0] = "Left Stick X";
        xBoxAxis[1] = "Left Stick Y";
        xBoxAxis[2] = "Right Stick X";
        xBoxAxis[3] = "Right Stick Y";
        xBoxAxis[4] = "Left Trigger";
        xBoxAxis[5] = "Right Trigger";

        commandsList.put("AlignTargetC", new AlignTargetC());
        commandsList.put("DriverArcadeXbox2C", new DriveArcadeXbox2C());
        commandsList.put("DriveArcadeXboxC", new DriveArcadeXboxC());
      }
}