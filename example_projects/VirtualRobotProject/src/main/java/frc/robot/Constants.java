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


}
