package frc.lib.logging;

import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.wpilog.WPILOGReader;

public class Logger {
    private static ReplaySource replaySource = null;
    private static final LogTable logTable = new LogTable();
    private static final DataReceiverManager dataReceiversManager = new DataReceiverManager();

    private Logger() {}

    public static void setReplayLog(String filename) {
        try {
            setReplayLog(new WPILOGReader(filename));
        } catch (IOException e) {
            DriverStation.reportError("Failed to open log file. (" + e.getMessage() + ")", e.getStackTrace());
        }
    }

    public static void setReplayLog(ReplaySource replaySource) {
        Logger.replaySource = replaySource;
    }

    public static void addReceiver(DataReceiver dataReceiver) {
        dataReceiversManager.addReceiver(dataReceiver);
    }

    public static void beforePeriodic() {
        if (replaySource != null) {
            boolean isSuccess = replaySource.updateTableToNextCycle(logTable);
            if (!isSuccess)
                System.exit(0);
        } else {
            RealDataManager.updateTableToNextCycle(logTable);
        }

        RealDataManager.updateFieldsFromTable(logTable);
    }

    public static void putLogValue(String key, LogValue value) {
        logTable.put(key, value);
    }

    public static void afterPeriodic() {
        dataReceiversManager.putTable(logTable.clone());
    }
}
