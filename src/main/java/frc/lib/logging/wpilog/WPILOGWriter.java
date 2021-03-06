package frc.lib.logging.wpilog;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.util.datalog.DataLog;
import frc.lib.logging.LogConstants;
import frc.lib.logging.CycleReceiverBase;
import frc.lib.logging.LoggableType;

public class WPILOGWriter extends CycleReceiverBase<WPILOGOptions> {
    private final Map<String, Integer> entriesIds = new HashMap<>();
    private final DataLog log;
    
    public WPILOGWriter(String directory) {
        super(WPILOGOptions.class);
        log = new DataLog(directory);
    }

    public int getOrCreateEntryId(String key, LoggableType type, long timestamp) {
        return entriesIds.computeIfAbsent(
                key,
                k -> log.start(k, type.asWPILOGType(), LogConstants.ENTRY_METADATA, timestamp));
    }

    @Override
    public void putRaw(String key, byte[] value, long timestamp, WPILOGOptions options) {
        log.appendRaw(getOrCreateEntryId(key, LoggableType.RAW, timestamp), value, timestamp);
    }

    @Override
    public void putBoolean(String key, boolean value, long timestamp, WPILOGOptions options) {
        log.appendBoolean(getOrCreateEntryId(key, LoggableType.BOOLEAN, timestamp), value, timestamp);
    }

    @Override
    public void putInteger(String key, long value, long timestamp, WPILOGOptions options) {
        log.appendInteger(getOrCreateEntryId(key, LoggableType.INTEGER, timestamp), value, timestamp);
    }

    @Override
    public void putFloat(String key, float value, long timestamp, WPILOGOptions options) {
        log.appendFloat(getOrCreateEntryId(key, LoggableType.FLOAT, timestamp), value, timestamp);
    }

    @Override
    public void putDouble(String key, double value, long timestamp, WPILOGOptions options) {
        log.appendDouble(getOrCreateEntryId(key, LoggableType.DOUBLE, timestamp), value, timestamp);
    }

    @Override
    public void putString(String key, String value, long timestamp, WPILOGOptions options) {
        log.appendString(getOrCreateEntryId(key, LoggableType.STRING, timestamp), value, timestamp);
    }

    @Override
    public void putBooleanArray(String key, boolean[] value, long timestamp, WPILOGOptions options) {
        log.appendBooleanArray(getOrCreateEntryId(key, LoggableType.BOOLEAN_ARRAY, timestamp), value, timestamp);
    }

    @Override
    public void putIntegerArray(String key, long[] value, long timestamp, WPILOGOptions options) {
        log.appendIntegerArray(getOrCreateEntryId(key, LoggableType.INTEGER_ARRAY, timestamp), value, timestamp);
    }

    @Override
    public void putFloatArray(String key, float[] value, long timestamp, WPILOGOptions options) {
        log.appendFloatArray(getOrCreateEntryId(key, LoggableType.FLOAT_ARRAY, timestamp), value, timestamp);
    }

    @Override
    public void putDoubleArray(String key, double[] value, long timestamp, WPILOGOptions options) {
        log.appendDoubleArray(getOrCreateEntryId(key, LoggableType.DOUBLE_ARRAY, timestamp), value, timestamp);
    }

    @Override
    public void putStringArray(String key, String[] value, long timestamp, WPILOGOptions options) {
        log.appendStringArray(getOrCreateEntryId(key, LoggableType.STRING_ARRAY, timestamp), value, timestamp);
    }
}
