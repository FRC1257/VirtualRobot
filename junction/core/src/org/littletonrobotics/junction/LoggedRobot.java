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

package org.littletonrobotics.junction;

import edu.wpi.first.hal.DriverStationJNI;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.IterativeRobotBase;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.NotifierJNI;

/**
 * LoggedRobot implements the IterativeRobotBase robot program framework.
 *
 * <p>
 * The LoggedRobot class is intended to be subclassed by a user creating a robot
 * program, and will call all required AdvantageKit periodic methods.
 *
 * <p>
 * periodic() functions from the base class are called on an interval by a
 * Notifier instance.
 */
public class LoggedRobot extends IterativeRobotBase {

  public static final double defaultPeriodSecs = 0.02;
  private final int notifier = NotifierJNI.initializeNotifier();
  private final long periodUs;
  private long nextCycleUs = 0;

  private boolean useTiming = true;

  /** Constructor for LoggedRobot. */
  protected LoggedRobot() {
    this(defaultPeriodSecs);
  }

  /**
   * Constructor for LoggedRobot.
   *
   * @param period Period in seconds.
   */
  protected LoggedRobot(double period) {
    super(period);
    this.periodUs = (long) (period * 1000000);
    NotifierJNI.setNotifierName(notifier, "LoggedRobot");

    HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_Timed);
  }

  @Override
  @SuppressWarnings("NoFinalizer")
  protected void finalize() {
    NotifierJNI.stopNotifier(notifier);
    NotifierJNI.cleanNotifier(notifier);
  }

  /** Provide an alternate "main loop" via startCompetition(). */
  @Override
  @SuppressWarnings("UnsafeFinalization")
  public void startCompetition() {
    long initStart = Logger.getRealTimestamp();
    robotInit();
    if (isSimulation()) {
      simulationInit();
    }
    long initEnd = Logger.getRealTimestamp();

    // Register auto logged outputs
    AutoLogOutputManager.registerFields(this);

    // Save data from init cycle
    Logger.periodicAfterUser(initEnd - initStart, 0);

    // Tell the DS that the robot is ready to be enabled
    System.out.println("********** Robot program startup complete **********");
    DriverStationJNI.observeUserProgramStarting();

    // Loop forever, calling the appropriate mode-dependent function
    while (true) {
      if (useTiming) {
        long currentTimeUs = Logger.getRealTimestamp();
        if (nextCycleUs < currentTimeUs) {
          // Loop overrun, start next cycle immediately
          nextCycleUs = currentTimeUs;
        } else {
          // Wait before next cycle
          NotifierJNI.updateNotifierAlarm(notifier, nextCycleUs);
          NotifierJNI.waitForNotifierAlarm(notifier);
        }
        nextCycleUs += periodUs;
      }

      long periodicBeforeStart = Logger.getRealTimestamp();
      Logger.periodicBeforeUser();
      long userCodeStart = Logger.getRealTimestamp();
      loopFunc();
      long userCodeEnd = Logger.getRealTimestamp();

      Logger.periodicAfterUser(userCodeEnd - userCodeStart, userCodeStart - periodicBeforeStart);
    }
  }

  /** Ends the main loop in startCompetition(). */
  @Override
  public void endCompetition() {
    NotifierJNI.stopNotifier(notifier);
  }

  /** Sets whether to use standard timing or run as fast as possible. */
  public void setUseTiming(boolean useTiming) {
    this.useTiming = useTiming;
  }
}
