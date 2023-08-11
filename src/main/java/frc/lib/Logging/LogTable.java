package frc.lib.Logging;

import java.util.HashMap;

import edu.wpi.first.wpilibj.RobotController;

public class LogTable {
    private final HashMap<String, LogValue> table = new HashMap<>();
    private long timestamp = RobotController.getFPGATime();

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Validate that the field current value is in the specified type (or it doesn't
     * have a value yet).
     */
    private boolean validateFieldType(String key, LoggableTypes type) {
        LogValue currentValue = table.get(key);
        if (currentValue == null) {
            return true;
        }
        if (currentValue.getType().equals(type)) {
            return true;
        }
        return false;
    }

    public void put(String key, byte[] value) {
        if (validateFieldType(key, LoggableTypes.Raw))
            table.put(key, new LogValue(value));
    }
    
    public void put(String key, boolean value) {
        if (validateFieldType(key, LoggableTypes.Boolean))
            table.put(key, new LogValue(value));
    }

    public void put(String key, long value) {
        if (validateFieldType(key, LoggableTypes.Integer))
            table.put(key, new LogValue(value));
    }

    public void put(String key, float value) {
        if (validateFieldType(key, LoggableTypes.Float))
            table.put(key, new LogValue(value));
    }

    public void put(String key, double value) {
        if (validateFieldType(key, LoggableTypes.Double))
            table.put(key, new LogValue(value));
    }

    public void put(String key, String value) {
        if (validateFieldType(key, LoggableTypes.String))
            table.put(key, new LogValue(value));
    }

    public void put(String key, boolean[] value) {
        if (validateFieldType(key, LoggableTypes.BooleanArray))
            table.put(key, new LogValue(value));
    }

    public void put(String key, long[] value) {
        if (validateFieldType(key, LoggableTypes.IntegerArray))
            table.put(key, new LogValue(value));
    }

    public void put(String key, float[] value) {
        if (validateFieldType(key, LoggableTypes.FloatArray))
            table.put(key, new LogValue(value));
    }

    public void put(String key, double[] value) {
        if (validateFieldType(key, LoggableTypes.DoubleArray))
            table.put(key, new LogValue(value));
    }

    public void put(String key, String[] value) {
        if (validateFieldType(key, LoggableTypes.StringArray))
            table.put(key, new LogValue(value));
    }

    

    public HashMap<String, LogValue> getAll() {
        return table;
    }
}
