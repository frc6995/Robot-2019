package frc.robot.controller_map;

import java.util.HashMap;
import java.util.Map;

public class ControllerMap {

    public static String[] LIST_OF_COMMANDS = {
        "",
        "AlignTargetC", 
        "DrievArcadeXbox2C",
        "DriveArcadeXboxC"
        
      };
      
      public final Map<String,Integer> xBoxMap;
      public final Map<String,Object> dashBoardButtons;

      public ControllerMap() {
        xBoxMap = new HashMap<>();
        dashBoardButtons = new HashMap<>();

        xBoxMap.put("A", 1);
        xBoxMap.put("B", 2);
        xBoxMap.put("X", 3);
        xBoxMap.put("Y", 4);
        xBoxMap.put("Left Bumper", 5);
        xBoxMap.put("Right Bumper", 6);
        xBoxMap.put("Start", 7);
        xBoxMap.put("Back", 8);
        xBoxMap.put("Left Stick Button", 9);
        xBoxMap.put("Right Stick Button", 10);

        xBoxMap.put("Dpad Up", )
      }

}