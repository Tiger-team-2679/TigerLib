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
import frc.lib.Logging.LoggableType;
import frc.lib.Logging.logvalues.types.*;

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

        switch (LoggableType.fromWPILOGType(startData.type)) {
            case RAW:
                logTable.put(startData.name, new RawLogValue(record.getRaw()));
                break;
            case BOOLEAN:
                logTable.put(startData.name, new BooleanLogValue(record.getBoolean()));
                break;
            case INTEGER:
                logTable.put(startData.name, new IntegerLogValue(record.getInteger()));
                break;
            case FLOAT:
                logTable.put(startData.name, new FloatLogValue(record.getFloat()));
                break;
            case DOUBLE:
                logTable.put(startData.name, new DoubleLogValue(record.getDouble()));
                break;
            case STRING:
                logTable.put(startData.name, new StringLogValue(record.getString()));
                break;
            case BOOLEAN_ARRAY:
                logTable.put(startData.name, new BooleanArrayLogValue(record.getBooleanArray()));
                break;
            case INTEGER_ARRAY:
                logTable.put(startData.name, new IntegerArrayLogValue(record.getIntegerArray()));
                break;
            case FLOAT_ARRAY:
                logTable.put(startData.name, new FloatArrayLogValue(record.getFloatArray()));
                break;
            case DOUBLE_ARRAY:
                logTable.put(startData.name, new DoubleArrayLogValue(record.getDoubleArray()));
                break;
            case STRING_ARRAY:
                logTable.put(startData.name, new StringArrayLogValue(record.getStringArray()));
                break;
        }
    }
}
