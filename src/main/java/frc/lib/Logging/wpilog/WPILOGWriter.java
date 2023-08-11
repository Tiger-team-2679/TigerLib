package frc.lib.Logging.wpilog;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.util.datalog.DataLog;
import frc.lib.Logging.LogTable;
import frc.lib.Logging.LogValue;
import frc.lib.Logging.LoggableTypes;

public class WPILOGWriter {
    private final Map<String, Integer> entriesIds = new HashMap<>();
    private final DataLog log;
    private final int timestampEntryId;

    public WPILOGWriter(String dir) {
        log = new DataLog(dir);
        timestampEntryId = log.start(WPILOGConstants.TIMESTAMP_KEY, LoggableTypes.Integer.asWPILOGType());
    }

    public void writeTable(LogTable table) {
        log.appendInteger(timestampEntryId, table.getTimestamp(), table.getTimestamp());

        table.getAll().forEach((key, value) -> {
            Integer entryId = entriesIds.get(key);
            if (entryId == null) {
                entryId = log.start(key, value.getType().asWPILOGType());
                entriesIds.put(key, entryId);
            }

            writeToEntry(entryId, value, table.getTimestamp());
        });
    }

    private void writeToEntry(int entryId, LogValue value, long timestamp) {
        switch (value.getType()) {
            case Raw:
                log.appendRaw(entryId, value.asRaw(), timestamp);
                break;

            case Boolean:
                log.appendBoolean(entryId, value.asBoolean(), timestamp);
                break;

            case Integer:
                log.appendInteger(entryId, value.asInteger(), timestamp);
                break;

            case Float:
                log.appendFloat(entryId, value.asFloat(), timestamp);
                break;

            case Double:
                log.appendDouble(entryId, value.asDouble(), timestamp);
                break;

            case String:
                log.appendString(entryId, value.asString(), timestamp);
                break;

            case BooleanArray:
                log.appendBooleanArray(entryId, value.asBooleanArray(), timestamp);
                break;

            case IntegerArray:
                log.appendIntegerArray(entryId, value.asIntegerArray(), timestamp);
                break;

            case FloatArray:
                log.appendFloatArray(entryId, value.asFloatArray(), timestamp);
                break;

            case DoubleArray:
                log.appendDoubleArray(entryId, value.asDoubleArray(), timestamp);
                break;

            case StringArray:
                log.appendStringArray(entryId, value.asStringArray(), timestamp);
                break;
        }
    }
}
