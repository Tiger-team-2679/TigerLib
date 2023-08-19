package frc.lib.logging.wpilog;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.util.datalog.DataLog;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;

public class WPILOGWriter implements DataReceiver {
    private final Map<String, Integer> entriesIds = new HashMap<>();
    private final DataLog log;

    public WPILOGWriter(String directory) {
        log = new DataLog(directory);
    }

    public int getOrCreateEntryId(String key, LoggableType type, long timestamp){
        Integer entryId = entriesIds.get(key);
        if (entryId == null) {
            entryId = log.start(key, type.asWPILOGType(), WPILOGConstants.ENTRY_METADATA, timestamp);
            entriesIds.put(key, entryId);
        }
        return entryId;
    }

    @Override
    public void putRaw(String key, byte[] value, long timestamp) {
        log.appendRaw(getOrCreateEntryId(key, LoggableType.RAW, timestamp), value, timestamp);        
    }

    @Override
    public void putBoolean(String key, boolean value, long timestamp) {
        log.appendBoolean(getOrCreateEntryId(key, LoggableType.BOOLEAN, timestamp), value, timestamp);                
    }

    @Override
    public void putInteger(String key, long value, long timestamp) {
        log.appendInteger(getOrCreateEntryId(key, LoggableType.INTEGER, timestamp), value, timestamp);
    }

    @Override
    public void putFloat(String key, float value, long timestamp) {
        log.appendFloat(getOrCreateEntryId(key, LoggableType.FLOAT, timestamp), value, timestamp);        
    }

    @Override
    public void putDouble(String key, double value, long timestamp) {
        log.appendDouble(getOrCreateEntryId(key, LoggableType.DOUBLE, timestamp), value, timestamp);
    }

    @Override
    public void putString(String key, String value, long timestamp) {
        log.appendString(getOrCreateEntryId(key, LoggableType.STRING, timestamp), value, timestamp);
    }

    @Override
    public void putBooleanArray(String key, boolean[] value, long timestamp) {
        log.appendBooleanArray(getOrCreateEntryId(key, LoggableType.BOOLEAN_ARRAY, timestamp), value, timestamp);
    }

    @Override
    public void putIntegerArray(String key, long[] value, long timestamp) {
        log.appendIntegerArray(getOrCreateEntryId(key, LoggableType.INTEGER_ARRAY, timestamp), value, timestamp);
    }

    @Override
    public void putFloatArray(String key, float[] value, long timestamp) {
        log.appendFloatArray(getOrCreateEntryId(key, LoggableType.FLOAT_ARRAY, timestamp), value, timestamp);
    }

    @Override
    public void putDoubleArray(String key, double[] value, long timestamp) {
        log.appendDoubleArray(getOrCreateEntryId(key, LoggableType.DOUBLE_ARRAY, timestamp), value, timestamp);
    }

    @Override
    public void putStringArray(String key, String[] value, long timestamp) {
        log.appendStringArray(getOrCreateEntryId(key, LoggableType.STRING_ARRAY, timestamp), value, timestamp);
    }

}
