// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.ControllerTracking;

/** Where all of the controller objects are stored. */
public class Controllers {

  // effects unknown if port-bound controllers are not xbox

  /** XboxController on port zero */
  public static class Zero {
    public static CommandXboxController commObject = new CommandXboxController(0);
    public static XboxController object = commObject.getHID();
    public static Trigger A = commObject.a();
    public static Trigger B = commObject.b();
    public static Trigger X = commObject.x();
    public static Trigger Y = commObject.y();
    public static Trigger LeftBumper = commObject.leftBumper();
    public static Trigger RightBumper = commObject.rightBumper();
    public static Trigger LeftStickPress = commObject.leftStick();
    public static Trigger RightStickPress = commObject.rightStick();
    public static Trigger Share = commObject.start();
    public static Trigger Options = commObject.back();
    public static Trigger LeftTrigger = commObject.leftTrigger();
    public static Trigger RightTrigger = commObject.rightTrigger();
    public static Trigger PovUp = commObject.povUp();
    public static Trigger PovUpLeft = commObject.povUpLeft();
    public static Trigger PovUpRight = commObject.povUpRight();
    public static Trigger PovDown = commObject.povDown();
    public static Trigger PovDownLeft = commObject.povDownLeft();
    public static Trigger PovDownRight = commObject.povDownRight();
    public static Trigger PovLeft = commObject.povLeft();
    public static Trigger PovRight = commObject.povRight();
  }

  /** XboxController on port one */
  public static class One {
    public static CommandXboxController commObject = new CommandXboxController(1);
    public static XboxController object = commObject.getHID();
    public static Trigger A = commObject.a();
    public static Trigger B = commObject.b();
    public static Trigger X = commObject.x();
    public static Trigger Y = commObject.y();
    public static Trigger LeftBumper = commObject.leftBumper();
    public static Trigger RightBumper = commObject.rightBumper();
    public static Trigger LeftStickPress = commObject.leftStick();
    public static Trigger RightStickPress = commObject.rightStick();
    public static Trigger Share = commObject.start();
    public static Trigger Options = commObject.back();
    public static Trigger LeftTrigger = commObject.leftTrigger();
    public static Trigger RightTrigger = commObject.rightTrigger();
    public static Trigger PovUp = commObject.povUp();
    public static Trigger PovUpLeft = commObject.povUpLeft();
    public static Trigger PovUpRight = commObject.povUpRight();
    public static Trigger PovDown = commObject.povDown();
    public static Trigger PovDownLeft = commObject.povDownLeft();
    public static Trigger PovDownRight = commObject.povDownRight();
    public static Trigger PovLeft = commObject.povLeft();
    public static Trigger PovRight = commObject.povRight();
  }

  /** XboxController on port two */
  public static class Two {
    public static CommandXboxController commObject = new CommandXboxController(2);
    public static XboxController object = commObject.getHID();
    public static Trigger A = commObject.a();
    public static Trigger B = commObject.b();
    public static Trigger X = commObject.x();
    public static Trigger Y = commObject.y();
    public static Trigger LeftBumper = commObject.leftBumper();
    public static Trigger RightBumper = commObject.rightBumper();
    public static Trigger LeftStickPress = commObject.leftStick();
    public static Trigger RightStickPress = commObject.rightStick();
    public static Trigger Share = commObject.start();
    public static Trigger Options = commObject.back();
    public static Trigger LeftTrigger = commObject.leftTrigger();
    public static Trigger RightTrigger = commObject.rightTrigger();
    public static Trigger PovUp = commObject.povUp();
    public static Trigger PovUpLeft = commObject.povUpLeft();
    public static Trigger PovUpRight = commObject.povUpRight();
    public static Trigger PovDown = commObject.povDown();
    public static Trigger PovDownLeft = commObject.povDownLeft();
    public static Trigger PovDownRight = commObject.povDownRight();
    public static Trigger PovLeft = commObject.povLeft();
    public static Trigger PovRight = commObject.povRight();
  }

  /** XboxController on port three */
  public static class Three {
    public static CommandXboxController commObject = new CommandXboxController(3);
    public static XboxController object = commObject.getHID();
    public static Trigger A = commObject.a();
    public static Trigger B = commObject.b();
    public static Trigger X = commObject.x();
    public static Trigger Y = commObject.y();
    public static Trigger LeftBumper = commObject.leftBumper();
    public static Trigger RightBumper = commObject.rightBumper();
    public static Trigger LeftStickPress = commObject.leftStick();
    public static Trigger RightStickPress = commObject.rightStick();
    public static Trigger Share = commObject.start();
    public static Trigger Options = commObject.back();
    public static Trigger LeftTrigger = commObject.leftTrigger();
    public static Trigger RightTrigger = commObject.rightTrigger();
    public static Trigger PovUp = commObject.povUp();
    public static Trigger PovUpLeft = commObject.povUpLeft();
    public static Trigger PovUpRight = commObject.povUpRight();
    public static Trigger PovDown = commObject.povDown();
    public static Trigger PovDownLeft = commObject.povDownLeft();
    public static Trigger PovDownRight = commObject.povDownRight();
    public static Trigger PovLeft = commObject.povLeft();
    public static Trigger PovRight = commObject.povRight();
  }

  /** XboxController on port four */
  public static class Four {
    public static CommandXboxController commObject = new CommandXboxController(4);
    public static XboxController object = commObject.getHID();
    public static Trigger A = commObject.a();
    public static Trigger B = commObject.b();
    public static Trigger X = commObject.x();
    public static Trigger Y = commObject.y();
    public static Trigger LeftBumper = commObject.leftBumper();
    public static Trigger RightBumper = commObject.rightBumper();
    public static Trigger LeftStickPress = commObject.leftStick();
    public static Trigger RightStickPress = commObject.rightStick();
    public static Trigger Share = commObject.start();
    public static Trigger Options = commObject.back();
    public static Trigger LeftTrigger = commObject.leftTrigger();
    public static Trigger RightTrigger = commObject.rightTrigger();
    public static Trigger PovUp = commObject.povUp();
    public static Trigger PovUpLeft = commObject.povUpLeft();
    public static Trigger PovUpRight = commObject.povUpRight();
    public static Trigger PovDown = commObject.povDown();
    public static Trigger PovDownLeft = commObject.povDownLeft();
    public static Trigger PovDownRight = commObject.povDownRight();
    public static Trigger PovLeft = commObject.povLeft();
    public static Trigger PovRight = commObject.povRight();
  }

  /** XboxController on port five */
  public static class Five {
    public static CommandXboxController commObject = new CommandXboxController(5);
    public static XboxController object = commObject.getHID();
    public static Trigger A = commObject.a();
    public static Trigger B = commObject.b();
    public static Trigger X = commObject.x();
    public static Trigger Y = commObject.y();
    public static Trigger LeftBumper = commObject.leftBumper();
    public static Trigger RightBumper = commObject.rightBumper();
    public static Trigger LeftStickPress = commObject.leftStick();
    public static Trigger RightStickPress = commObject.rightStick();
    public static Trigger Share = commObject.start();
    public static Trigger Options = commObject.back();
    public static Trigger LeftTrigger = commObject.leftTrigger();
    public static Trigger RightTrigger = commObject.rightTrigger();
    public static Trigger PovUp = commObject.povUp();
    public static Trigger PovUpLeft = commObject.povUpLeft();
    public static Trigger PovUpRight = commObject.povUpRight();
    public static Trigger PovDown = commObject.povDown();
    public static Trigger PovDownLeft = commObject.povDownLeft();
    public static Trigger PovDownRight = commObject.povDownRight();
    public static Trigger PovLeft = commObject.povLeft();
    public static Trigger PovRight = commObject.povRight();
  }

  /** Xbox controller that will change to lowest numbered port that contains an Xbox controller */
  public static class dynamicXbox {
    public static XboxController object = new XboxController(5);
    public static CommandXboxController commObject;
    public static Trigger A;
    public static Trigger B;
    public static Trigger X;
    public static Trigger Y;
    public static Trigger LeftBumper;
    public static Trigger RightBumper;
    public static Trigger LeftStickPress;
    public static Trigger RightStickPress;
    public static Trigger Share;
    public static Trigger Options;
    public static Trigger LeftTrigger;
    public static Trigger RightTrigger;
    public static Trigger PovUp;
    public static Trigger PovUpLeft;
    public static Trigger PovUpRight;
    public static Trigger PovDown;
    public static Trigger PovDownLeft;
    public static Trigger PovDownRight;
    public static Trigger PovLeft;
    public static Trigger PovRight;

    public static void updateController() {
      ControllerTracking.updatePortNumbers();
      System.out.print(" Assigning dXbox: " + object.getPort());
      commObject = new CommandXboxController(object.getPort());
      A = commObject.a();
      B = commObject.b();
      X = commObject.x();
      Y = commObject.y();
      LeftBumper = commObject.leftBumper();
      RightBumper = commObject.rightBumper();
      LeftStickPress = commObject.leftStick();
      RightStickPress = commObject.rightStick();
      Share = commObject.start();
      Options = commObject.back();
      LeftTrigger = commObject.leftTrigger();
      RightTrigger = commObject.rightTrigger();
      PovUp = commObject.povUp();
      PovUpLeft = commObject.povUpLeft();
      PovUpRight = commObject.povUpRight();
      PovDown = commObject.povDown();
      PovDownLeft = commObject.povDownLeft();
      PovDownRight = commObject.povDownRight();
      PovLeft = commObject.povLeft();
      PovRight = commObject.povRight();
    }
  }

  /** PS4 controller that will change to lowest numbered port that contains a PS4 controller */
  public static class dynamicPlaystation {
    public static PS4Controller object = new PS4Controller(5);
    public static CommandPS4Controller commObject;
    public static Trigger A;
    public static Trigger B;
    public static Trigger X;
    public static Trigger Y;
    public static Trigger LeftBumper;
    public static Trigger RightBumper;
    public static Trigger LeftStickPress;
    public static Trigger RightStickPress;
    public static Trigger Share;
    public static Trigger Options;
    public static Trigger LeftTrigger;
    public static Trigger RightTrigger;
    public static Trigger PovUp;
    public static Trigger PovUpLeft;
    public static Trigger PovUpRight;
    public static Trigger PovDown;
    public static Trigger PovDownLeft;
    public static Trigger PovDownRight;
    public static Trigger PovLeft;
    public static Trigger PovRight;
    public static Trigger Touchpad;
    public static Trigger PS;

    public static void updateController() {
      ControllerTracking.updatePortNumbers();
      System.out.print(" Assigning dPlaystations: " + object.getPort());
      commObject = new CommandPS4Controller(object.getPort());
      A = commObject.cross();
      B = commObject.circle();
      X = commObject.square();
      Y = commObject.triangle();
      LeftBumper = commObject.L1();
      RightBumper = commObject.R1();
      LeftStickPress = commObject.L3();
      RightStickPress = commObject.R3();
      Share = commObject.share();
      Options = commObject.options();
      LeftTrigger = commObject.L2();
      RightTrigger = commObject.R2();
      PovUp = commObject.povUp();
      PovUpLeft = commObject.povUpLeft();
      PovUpRight = commObject.povUpRight();
      PovDown = commObject.povDown();
      PovDownLeft = commObject.povDownLeft();
      PovDownRight = commObject.povDownRight();
      PovLeft = commObject.povLeft();
      PovRight = commObject.povRight();
      Touchpad = commObject.touchpad();
      PS = commObject.PS();
    }
  }

  /** Joystick that will change to lowest numbered port that contains a joystick */
  public static class dynamicJoystick {
    public static Joystick object = new Joystick(5);
    public static CommandJoystick commObject;
    public static Trigger Trigger;
    public static Trigger Two;
    public static Trigger Three;
    public static Trigger Four;
    public static Trigger Five;
    public static Trigger Six;
    public static Trigger Seven;
    public static Trigger Eight;
    public static Trigger Nine;
    public static Trigger Ten;
    public static Trigger Eleven;

    public static void updateController() {
      ControllerTracking.updatePortNumbers();
      System.out.print(" Assigning dJoystick: " + object.getPort());
      commObject = new CommandJoystick(object.getPort());
      Trigger = commObject.button(1);
      Two = commObject.button(2);
      Three = commObject.button(3);
      Four = commObject.button(4);
      Five = commObject.button(5);
      Six = commObject.button(6);
      Seven = commObject.button(7);
      Eight = commObject.button(8);
      Nine = commObject.button(9);
      Ten = commObject.button(10);
      Eleven = commObject.button(11);
    }
  }
}