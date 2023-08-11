package frc.lib.Logging.wpilog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.util.datalog.DataLogIterator;
import edu.wpi.first.util.datalog.DataLogReader;
import edu.wpi.first.util.datalog.DataLogRecord;
import edu.wpi.first.util.datalog.DataLogRecord.StartRecordData;
import frc.lib.Logging.LogTable;
import frc.lib.Logging.LoggableTypes;

public class WPILOGReader {
    private final DataLogReader logReader;
    private final DataLogIterator iterator;
    private final Map<Integer, StartRecordData> entriesStartData = new HashMap<>();
    private final int timestampEntryId;
    private long nextCycleTimestamp;

    public WPILOGReader(String filename) throws IOException {
        logReader = new DataLogReader(filename);
        iterator = logReader.iterator();

        DataLogRecord timestampEntryStartRecord = iterateOnLogUntil(
                record -> record.isStart() && record.getStartData().name.equals(WPILOGConstants.TIMESTAMP_KEY));
        if (timestampEntryStartRecord == null)
            throw new IOException("Log file doesn't have valid " + WPILOGConstants.TIMESTAMP_KEY + " entry.");
        timestampEntryId = timestampEntryStartRecord.getStartData().entry;

        DataLogRecord firstTimestampRecord = iterateOnLogUntil(record -> record.getEntry() == timestampEntryId);
        if (firstTimestampRecord == null)
            throw new IOException("Log file doesn't have even one valid cycle.");
        nextCycleTimestamp = firstTimestampRecord.getInteger();
    }

    /**
     * iterates on the log records, until the supplied condition is true for that
     * record. each time continous from the place it left.
     * 
     * @param condition the condition to be checked on the record each iteration.
     * @return the record that passed the condition, or null if none passed the
     *         condition (or there are no records left).
     */
    private DataLogRecord iterateOnLogUntil(Function<DataLogRecord, Boolean> condition) {
        // due to the implementaion of hasNext(), we may lose the last record, but it's
        // negligible.
        while (iterator.hasNext()) {
            DataLogRecord record = iterator.next();
            if (condition.apply(record)) {
                return record;
            }
        }
        return null;
    }

    /**
     * update the given table to the next cycle from the log file. cycle is
     * considered between two cycle timestamp records.
     * 
     * @param table the log table to be updated.
     * @return wheter could update the full cycle, or the log file ended.
     */
    public boolean updateTableToNextCycle(LogTable table) {
        table.setTimestamp(nextCycleTimestamp);

        DataLogRecord nextCycleTimestampRecord = iterateOnLogUntil(record -> {
            if (record.isControl()) {
                handleControlRecord(record);
            } else if (record.getEntry() == timestampEntryId) {
                return true;
            } else {
                updateField(table, record);
            }
            return false;
        });

        if (nextCycleTimestampRecord == null)
            return false;
        nextCycleTimestamp = nextCycleTimestampRecord.getInteger();
        return true;
    }

    private void handleControlRecord(DataLogRecord record) {
        if (record.isStart()) {
            StartRecordData startData = record.getStartData();
            entriesStartData.put(startData.entry, startData);
        }
    }

    private void updateField(LogTable logTable, DataLogRecord record) {
        StartRecordData startData = entriesStartData.get(record.getEntry());
        if (startData == null)
            return;

        switch (LoggableTypes.fromWPILOGType(startData.type)) {
            case Raw:
                logTable.put(startData.name, record.getRaw());
                break;
            case Boolean:
                logTable.put(startData.name, record.getBoolean());
                break;
            case Integer:
                logTable.put(startData.name, record.getInteger());
                break;
            case Float:
                logTable.put(startData.name, record.getFloat());
                break;
            case Double:
                logTable.put(startData.name, record.getDouble());
                break;
            case String:
                logTable.put(startData.name, record.getString());
                break;
            case BooleanArray:
                logTable.put(startData.name, record.getBooleanArray());
                break;
            case IntegerArray:
                logTable.put(startData.name, record.getIntegerArray());
                break;
            case FloatArray:
                logTable.put(startData.name, record.getFloatArray());
                break;
            case DoubleArray:
                logTable.put(startData.name, record.getDoubleArray());
                break;
            case StringArray:
                logTable.put(startData.name, record.getStringArray());
                break;
        }
    }
}
