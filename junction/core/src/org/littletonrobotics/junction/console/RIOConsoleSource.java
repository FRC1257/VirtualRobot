package org.littletonrobotics.junction.console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Reads console data on the RIO. Saves stdout and sterr from both Java and
 * native code, including lines logged before this class was instantiated.
 */
public class RIOConsoleSource implements ConsoleSource {
  private static final String filePath = "/home/lvuser/FRC_UserProgram.log";
  private BufferedReader reader = null;

  public RIOConsoleSource() {
    try {
      reader = new BufferedReader(new FileReader(filePath));
    } catch (FileNotFoundException e) {
      DriverStation.reportError("Failed to open console file \"" + filePath + "\"", true);
    }
  }

  public String getNewData() {
    if (reader == null) {
      return null;
    }

    String output = "";
    while (true) {
      String nextLine = null;
      try {
        nextLine = reader.readLine();
      } catch (IOException e) {
        DriverStation.reportError("Failed to read console file \"" + filePath + "\"", true);
      }
      if (nextLine == null) {
        break;
      } else {
        output += nextLine + "\n";
      }
    }

    return output;
  }

  public void close() throws Exception {
    reader.close();
  }
}
