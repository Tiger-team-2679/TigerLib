package frc.lib.logging;

import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import frc.lib.logging.networktables.NT4Publisher;
import frc.lib.logging.wpilog.WPILOGReader;
import frc.lib.logging.wpilog.WPILOGWriter;

public class Logger {
    private ReplaySource wpilogReader = null;
    private final LogTable logTable = new LogTable();
    private final DataReceiverManager dataReceiversManager = new DataReceiverManager();

    public Logger(String logFolder) {
        dataReceiversManager.addReceiver(new WPILOGWriter(logFolder));
        dataReceiversManager.addReceiver(new NT4Publisher());
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

    public void beforePeriodic() {
        if (wpilogReader != null) {
            boolean isSuccess = wpilogReader.updateTableToNextCycle(logTable);
            if (!isSuccess)
                System.exit(0);
        } else {
            RealDataManager.updateTableToNextCycle(logTable);
        }

        RealDataManager.updateFieldsFromTable(logTable);
    }

    public void afterPeriodic() {
        dataReceiversManager.putTable(logTable.clone());
    }
}
