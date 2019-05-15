/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/* FULL ROCKET AUTO SEQUENCE OF EVENTS

1. Auto Path hl2 to front left rocket
2. limelight hatch level
3.Deploy hatch
4. Auto Path frontside rocket to loading station
5. limelight hatch level
6. Hatch pickup command
7. auto path loading station to backside rocket
8. Limelight hatch level
9. deploy hatch 
10. Auto path backside rocket to loading station
11. limelight hatch level
12. Hatch pickup command
13. Auto path loading station to frontside rocket.
14. limelight hatch level
15. hatch deploy l2 command 
16. auto path frontside rocket to loading station 
17. limelight hatch level
18. hatch pickup command
19. Auto path loading station to backside rocket
20. limelight hatch level
21. hatch deploy l2 command
22. Auto path backside rocket to loading station
23. limelight hatch level
24. Hatch pickup command
25. Auto path loading station to frontside rocket.
26. limelight hatch level
27. hatch deploy l3 command 
28. auto path frontside rocket to loading station 
29. limelight hatch level
30. hatch pickup command
31. Auto path loading station to backside rocket
32. limelight hatch level
33. hatch deploy l3 command
34. auto path backside rocket to loading station
35. limelight hatch level
36. ball pickup command 
37. auto path loading station to ballside rocket
38. limelight ball level
39. ball deploy
40. auto path ballside rocket to loading station
41. limelight hatch level
42. ball pickup command 
43. auto path loading station to ballside rocket
44. limelight ball level
45. ball deploy
46. auto path ballside rocket to loading station 
47. limelight hatch level
48. ball pickup command
49. auto path loading station to ballside rocket
50. limelight command ball level
51. ball deploy l2 command
52. auto path ballside rocket to loading station
53. limelight hatch level
54. ball pickup command 
55. auto path loading station to ballside rocket
56. limelight ball level
56. ball deploy l2 command
57. auto path backside rocket to loading station
58. limelight hatch level
59. ball pickup command 
60. auto path loading station to ballside rocket
61. limelight ball level
62. ball deploy l3 command
63.auto path backside rocket to loading station
64. limelight hatch level
65. ball pickup command 
66. auto path loading station to ballside rocket
67. limelight ball level
68. ball deploy l3 command

          VICTORY SPIN!!!
*/
package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.HatchScoreCG;
import frc.robot.commands.limelight.VisionAlignTargetC;

public class AutoCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoCG() {
    addSequential(new autoinitC());
    addSequential(new VisionAlignTargetC(false));
    addSequential(new HatchScoreCG());
  }
}
