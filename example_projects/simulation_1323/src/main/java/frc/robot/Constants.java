// Copyright 2021-2023 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final Mode currentMode = Mode.REAL;

  public static enum Mode {
    /** Running on a real robot. */
    REAL,

    /** Running a physics simulator. */
    SIM,

    /** Replaying from a log file. */
    REPLAY
  }

  public static class PivotArm {
      // PID constants
      public static double[] PIVOT_ARM_PID = new double[] { 0.25, 0, 0, 0 };
      public static double PIVOT_ARM_PID_TOLERANCE = 1;
      public static double PIVOT_ARM_PID_MAX_OUTPUT = 1;

      public static double POSITION_CONVERSION_FACTOR = 1;

      public static double PIVOT_ARM_MAX_ANGLE = 150.0;
      public static double PIVOT_ARM_MIN_ANGLE = -30.0;

      // Setpoints between -1 and 1
      public static double PIVOT_ARM_SETPOINT_UP = 135;
      public static double PIVOT_ARM_SETPOINT_MID = 175;
      // public static double PIVOT_ARM_SETPOINT_INTAKE = 0; // also used for low
      // score
      public static double PIVOT_ARM_SETPOINT_HOLD = 10;
      public static final double PIVOT_ARM_SETPOINT_BOTTOM = 0;
      public static final double PIVOT_ARM_SETPOINT_TOP = 170;

      public static class PivotArmPhysicalConstants {
        public static final double PIVOT_ARM_TOLERANCE = 3;
        public static final double PIVOT_ARM_STOP_BUFFER = 5;
      }

      public static class PivotArmSimConstants {
        public static final int kMotorPort = 2;
        public static final int kEncoderAChannel = 2;
        public static final int kEncoderBChannel = 3;
        public static final int kJoystickPort = 0;

        public static final String kArmPositionKey = "ArmPosition";
        public static final String kArmPKey = "ArmP";

        // The P gain for the PID controller that drives this arm.
        public static final double kDefaultArmKp = 50.0;
        public static final double kDefaultArmSetpointDegrees = 75.0;

        // distance per pulse = (angle per revolution) / (pulses per revolution)
        // = (2 * PI rads) / (4096 pulses)
        public static final double kArmEncoderDistPerPulse = 2.0 * Math.PI / 4096;

        public static final double kArmReduction = 200;
        public static final double kArmMass = 5.0; // Kilograms
        public static final double kArmLength = Units.inchesToMeters(20);
        public static final double kMinAngleRads = Units.degreesToRadians(-175);
        public static final double kMaxAngleRads = Units.degreesToRadians(255);
        public static double kEncoderDistancePerPulse;
      }
    }

    public static class ElectricalLayout {
      // Controllers
      public final static int CONTROLLER_DRIVER_ID = 0;
      public final static int CONTROLLER_OPERATOR_ID = 1;
  
      // Drivetrain Test Bot
      /*
       * public final static int DRIVE_FRONT_LEFT = 17;
       * public final static int DRIVE_FRONT_RIGHT = 10;
       * public final static int DRIVE_BACK_LEFT = 9;
       * public final static int DRIVE_BACK_RIGHT = 11;
       */
  
      // Drivetrain Main
      public final static int DRIVE_FRONT_LEFT = 1;
      public final static int DRIVE_FRONT_RIGHT = 2;
      public final static int DRIVE_BACK_LEFT = 3;
      public final static int DRIVE_BACK_RIGHT = 4;
  
      // Intakes
      public final static int INTAKE_MOTOR_ID = 9;
  
      public static final int INTAKE_ARM_MOTOR_LEFT_ID = 6;
      public static final int INTAKE_ARM_MOTOR_RIGHT_ID = 8;
  
      // Claw
      public final static int CLAW_MOTOR_LEFT_ID = 10;
      public final static int CLAW_PIECE_BUTTON = 0;
  
      // New Elevator Motor Design
      public final static int ELEVATOR_MOTOR_ID = 5;
  
      // Pivot ARm
      public static int PIVOT_ARM_ID = 7;
  
      // Sensors
      public static final int INTAKE_BUMP_SWITCH_ID = 24;
      public static final int ELEVATOR_LIMIT_SWITCH = 0;
    };
  
    public static double PI = 3.141592653589793238462643;
    public static double UPDATE_PERIOD = 0.010; // seconds
    public final static int NEO_550_CURRENT_LIMIT = 25; // amps
    public final static int QUADRATURE_COUNTS_PER_REV = 8192; // encoder resolution
    // https://www.revrobotics.com/rev-11-1271/
  
    /** Ambiguous with NEO_CURRENT_LIMIT in ElectricalLayout */
    // public final static int NEO_CURRENT_LIMIT = 80; // amps
  
    public final static int NEO_CURRENT_LIMIT = 80; // amps
  }

