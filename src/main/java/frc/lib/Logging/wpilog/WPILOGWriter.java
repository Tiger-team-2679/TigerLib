package frc.lib.Logging.wpilog;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.util.datalog.DataLog;
import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;

public class WPILOGWriter implements Writer {
    private final Map<String, Integer> entriesIds = new HashMap<>();
    private final DataLog log;

    public WPILOGWriter(String dir) {
        log = new DataLog(dir);
    }

    public int getOrCreateEntryId(String key, LoggableType type){
        Integer entryId = entriesIds.get(key);
        if (entryId == null) {
            entryId = log.start(key, type.asWPILOGType());
            entriesIds.put(key, entryId);
        }
        return entryId;
    }

    @Override
    public void writeRaw(String key, byte[] value, long timestamp) {
        log.appendRaw(getOrCreateEntryId(key, LoggableType.RAW), value, timestamp);        
    }

    @Override
    public void writeBoolean(String key, boolean value, long timestamp) {
        log.appendBoolean(getOrCreateEntryId(key, LoggableType.BOOLEAN), value, timestamp);                
    }

    @Override
    public void writeInteger(String key, long value, long timestamp) {
        log.appendInteger(getOrCreateEntryId(key, LoggableType.INTEGER), value, timestamp);
    }

    @Override
    public void writeFloat(String key, float value, long timestamp) {
        log.appendFloat(getOrCreateEntryId(key, LoggableType.FLOAT), value, timestamp);        
    }

    @Override
    public void writeDouble(String key, double value, long timestamp) {
        log.appendDouble(getOrCreateEntryId(key, LoggableType.DOUBLE), value, timestamp);
        
    }

    @Override
    public void writeString(String key, String value, long timestamp) {
        log.appendString(getOrCreateEntryId(key, LoggableType.STRING), value, timestamp);
    }

    @Override
    public void writeBooleanArray(String key, boolean[] value, long timestamp) {
        log.appendBooleanArray(getOrCreateEntryId(key, LoggableType.BOOLEAN_ARRAY), value, timestamp);
    }

    @Override
    public void writeIntegerArray(String key, long[] value, long timestamp) {
        log.appendIntegerArray(getOrCreateEntryId(key, LoggableType.INTEGER_ARRAY), value, timestamp);
    }

    @Override
    public void writeFloatArray(String key, float[] value, long timestamp) {
        log.appendFloatArray(getOrCreateEntryId(key, LoggableType.FLOAT_ARRAY), value, timestamp);
    }

    @Override
    public void writeDoubleArray(String key, double[] value, long timestamp) {
        log.appendDoubleArray(getOrCreateEntryId(key, LoggableType.DOUBLE_ARRAY), value, timestamp);
    }

    @Override
    public void writeStringArray(String key, String[] value, long timestamp) {
        log.appendStringArray(getOrCreateEntryId(key, LoggableType.STRING_ARRAY), value, timestamp);
    }

}
