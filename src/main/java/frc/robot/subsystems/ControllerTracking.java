package frc.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.HIDType;
import frc.robot.Controllers;


public class ControllerTracking {
  static HIDType isXbox = HIDType.kXInputGamepad;
  static HIDType isPS4 = HIDType.kHIDGamepad;
  static HIDType isJoystick = HIDType.kHIDJoystick;

  private static HIDType JoystickTypes[] = new HIDType[5];

  public static void updatePortNumbers() {
    JoystickTypes = new HIDType[5];
    GenericHID testHID;
    for (int i = 0; i < JoystickTypes.length; i++) {
      testHID = new GenericHID(i);
      if (testHID.isConnected()) {
        JoystickTypes[i] = testHID.getType();
      }
    }
    Controllers.dynamicXbox = new Controllers.ControllerBase(indexOfType(JoystickTypes, isXbox));
    Controllers.dynamicPlaystation = new Controllers.ControllerBase(indexOfType(JoystickTypes, HIDType.kHIDGamepad));
    Controllers.dynamicJoystick = new Controllers.ControllerBase(indexOfType(JoystickTypes, HIDType.kHIDJoystick));
  }

  private static int indexOfType(HIDType[] HIDarray, HIDType type) {
    for (int i = 0; i < HIDarray.length; i++) {
      if (HIDarray[i] == type) {
        return i;
      }
    }
    return 5;
  }
}
