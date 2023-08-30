package frc.lib.logging;

import java.util.ArrayList;
import java.util.List;

import frc.lib.logging.logvalues.types.IntegerLogValue;

public class DataReceiverManager {
    private final List<DataReceiver> dataReceivers = new ArrayList<>();
    private LogTable prevLogTable = null;

    /**
     * Put table in all data receivers.
     * 
     * @param dataReceiver the table to put, each call should be a refrence to a new
     *                     instence.
     */
    public void putTable(LogTable logTable) {
        IntegerLogValue cycleTimestampLogValue = new IntegerLogValue(logTable.getTimestamp());
        for (DataReceiver dataReceiver : dataReceivers) {
            cycleTimestampLogValue.putInDataReceiver(dataReceiver, LogConstants.CYCLE_TIMESTAMP_KEY, logTable.getTimestamp());
            logTable.getAll().forEach((key, logValue) -> {
                if (prevLogTable == null || !logValue.equals(prevLogTable.get(key)))
                    logValue.putInDataReceiver(dataReceiver, key, logTable.getTimestamp());
            });
        }

        prevLogTable = logTable;
    }

    public void addReceiver(DataReceiver dataReceiver) {
        dataReceivers.add(dataReceiver);
    }
}
