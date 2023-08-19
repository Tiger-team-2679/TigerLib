package frc.lib.logging;

import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import frc.lib.logging.logvalues.types.BooleanLogValue;
import frc.lib.logging.logvalues.types.IntegerLogValue;
import frc.lib.logging.wpilog.WPILOGReader;
import frc.lib.logging.wpilog.WPILOGWriter;

public class Logger {
    private ReplaySource wpilogReader = null;
    private final LogTable logTable = new LogTable();
    private final DataReceiverManager dataRecieversManager = new DataReceiverManager();
    private long counter = 0;

    public Logger(String logFolder) {
        dataRecieversManager.addReciever(new WPILOGWriter(logFolder));
    }

    public void setReplayLog(String filename) {
        try {
            setReplayLog(new WPILOGReader(filename));
        } catch (IOException e) {
            DriverStation.reportError("Failed to open log file. (" + e.getMessage() + ")", e.getStackTrace());
        }
    }

    public void setReplayLog(ReplaySource replaySource) {
        wpilogReader = replaySource;
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
            logTable.put("boool2", new BooleanLogValue(counter % 4 == 0));
        }

        dataRecieversManager.putTable(logTable.clone());
    }
}
