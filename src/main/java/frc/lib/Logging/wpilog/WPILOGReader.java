package frc.lib.logging.wpilog;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import edu.wpi.first.util.datalog.DataLogIterator;
import edu.wpi.first.util.datalog.DataLogReader;
import edu.wpi.first.util.datalog.DataLogRecord;
import edu.wpi.first.util.datalog.DataLogRecord.StartRecordData;
import frc.lib.logging.LogTable;
import frc.lib.logging.LoggableType;
import frc.lib.logging.ReplaySource;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.*;

public class WPILOGReader implements ReplaySource {
    private final DataLogReader logReader;
    private final DataLogIterator iterator;
    private final Map<Integer, StartRecordData> entriesStartData = new HashMap<>();
    private final int timestampEntryId;
    private long nextCycleTimestamp;

    public WPILOGReader(String filename) throws IOException {
        logReader = new DataLogReader(filename);
        iterator = logReader.iterator();

        DataLogRecord timestampEntryStartRecord = iterateOnLogUntil(
                record -> record.isStart() && record.getStartData().name.equals(WPILOGConstants.CYCLE_TIMESTAMP_KEY)
                        && record.getStartData().metadata.equals(WPILOGConstants.ENTRY_METADATA));
        if (timestampEntryStartRecord == null)
            throw new IOException("Log file doesn't have valid " + WPILOGConstants.CYCLE_TIMESTAMP_KEY + " entry.");
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
            } else if (record.getTimestamp() == table.getTimestamp()) {
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
        if (record.isStart()) { // other types of control records doesn't metter.
            StartRecordData startData = record.getStartData();
            if (startData.metadata.contains("\"source\": \"TigerKit\""))
                entriesStartData.put(startData.entry, startData);
        }
    }

    private LogValue createLogValue(DataLogRecord record, LoggableType type) {
        switch (type) {
            case RAW:
                return new RawLogValue(record.getRaw());
            case BOOLEAN:
                return new BooleanLogValue(record.getBoolean());
            case INTEGER:
                return new IntegerLogValue(record.getInteger());
            case FLOAT:
                return new FloatLogValue(record.getFloat());
            case DOUBLE:
                return new DoubleLogValue(record.getDouble());
            case STRING:
                return new StringLogValue(record.getString());
            case BOOLEAN_ARRAY:
                return new BooleanArrayLogValue(record.getBooleanArray());
            case INTEGER_ARRAY:
                return new IntegerArrayLogValue(record.getIntegerArray());
            case FLOAT_ARRAY:
                return new FloatArrayLogValue(record.getFloatArray());
            case DOUBLE_ARRAY:
                return new DoubleArrayLogValue(record.getDoubleArray());
            case STRING_ARRAY:
                return new StringArrayLogValue(record.getStringArray());
            default:
                return null;
        }
    }

    private void updateField(LogTable logTable, DataLogRecord record) {
        try {
            StartRecordData startData = entriesStartData.get(record.getEntry());

            if (startData == null)
                return; // does not throw because could be caused by record that wasn't written by
                        // TigerLib.

            LogValue logValue = createLogValue(record, LoggableType.fromWPILOGType(startData.type));
            if (logValue == null)
                throw new UnexpectedException("Did not create LogValue.");

            logTable.put(startData.name, logValue);
        } catch (UnexpectedException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
