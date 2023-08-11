package frc.lib.Logging;

import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import frc.lib.Logging.wpilog.WPILOGReader;
import frc.lib.Logging.wpilog.WPILOGWriter;

public class Logger {
    private final WPILOGWriter wpilogWriter;
    private WPILOGReader wpilogReader = null;
    LogTable table = new LogTable();
    private long counter = 0;

    public Logger(String logFolder) {
        wpilogWriter = new WPILOGWriter(logFolder);
    }

    public void setReplayLog(String filename){
        try {
            wpilogReader = new WPILOGReader(filename);
        } catch (IOException e) {
            DriverStation.reportError("Failed to open log file. (" + e.getMessage() + ")", e.getStackTrace());
        }
    }

    public void periodic() {
        if (wpilogReader != null) {
            boolean isSuccess = wpilogReader.updateTableToNextCycle(table);
            if (!isSuccess)
                System.exit(0);
        } else {
            table.setTimestamp(RobotController.getFPGATime());
            table.put("hey", ++counter);
            table.put("boool", counter % 2 == 0);
        }
        wpilogWriter.writeTable(table);
    }
}
