package frc.lib.Logging;

import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import frc.lib.Logging.logvalues.types.BooleanLogValue;
import frc.lib.Logging.logvalues.types.IntegerLogValue;
import frc.lib.Logging.wpilog.WPILOGConstants;
import frc.lib.Logging.wpilog.WPILOGReader;
import frc.lib.Logging.wpilog.WPILOGWriter;

public class Logger {
    private final WPILOGWriter wpilogWriter;
    private WPILOGReader wpilogReader = null;
    private final LogTable logTable = new LogTable();
    private long counter = 0;

    public Logger(String logFolder) {
        wpilogWriter = new WPILOGWriter(logFolder);
    }

    public void setReplayLog(String filename) {
        try {
            wpilogReader = new WPILOGReader(filename);
        } catch (IOException e) {
            DriverStation.reportError("Failed to open log file. (" + e.getMessage() + ")", e.getStackTrace());
        }
    }

    public void periodic() {
        if (wpilogReader != null) {
            boolean isSuccess = wpilogReader.updateTableToNextCycle(logTable);
            if (!isSuccess)
                System.exit(0);
        } else {
            logTable.setTimestamp(RobotController.getFPGATime());
            logTable.put("hey", new IntegerLogValue(++counter));
            logTable.put("boool", new BooleanLogValue(counter % 2 == 0));
        }

        writeTable();
    }

    public void writeTable() {
        // logging the cycle timestamp at the beggining of the cycle.
        new IntegerLogValue(logTable.getTimestamp())
                .log(WPILOGConstants.CYCLE_TIMESTAMP_KEY, wpilogWriter, logTable.getTimestamp());

        // lot all the values from logTable
        logTable.getAll().forEach((key, logValue) -> {
            logValue.log(key, wpilogWriter, logTable.getTimestamp());
        });
    }
}
