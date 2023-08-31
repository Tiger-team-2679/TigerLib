package frc.lib.logging;

import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.wpilog.WPILOGReader;

public class Logger {
    private static ReplaySource replaySource = null;
    private static final LogTable logTable = new LogTable();
    private static final CycleReceiversManager cycleReceiversManager = new CycleReceiversManager();

    private Logger() {
    }

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

    public static void addCycleReceiver(CycleReceiver dataReceiver, CycleReceiverOptions defaultOptions) {
        cycleReceiversManager.registerCycleReceiver(dataReceiver, defaultOptions);
    }

    public static void beforePeriodic() {
        if (replaySource != null) {
            boolean isSuccess = replaySource.updateTableToNextCycle(
                    logTable,
                    RealFieldsManager::getFieldCycleReceiversOptions);
            if (!isSuccess)
                System.exit(0);
        } else {
            RealFieldsManager.updateTableToNextCycle(logTable);
        }

        RealFieldsManager.updateFieldsFromTable(logTable);
    }

    public static void recordValue(String key, LogValue value) {
        String prefix = replaySource == null ? "realOutputs/" : "replayOutputs/";
        logTable.put(prefix + key, value);
    }

    public static void afterPeriodic() {
        cycleReceiversManager.putTable(logTable.clone());
    }
}
