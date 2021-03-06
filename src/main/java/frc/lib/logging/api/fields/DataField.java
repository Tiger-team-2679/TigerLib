package frc.lib.logging.api.fields;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.RealFieldsManager;
import frc.lib.logging.logvalues.LogValue;

public abstract class DataField implements DataReceiver {
    private final CycleReceiverOptions[] cycleReceiversOptions;

    public DataField(String key, CycleReceiverOptions[] cycleReceiversOptions) {
        this.cycleReceiversOptions = cycleReceiversOptions;
        RealFieldsManager.registerField(key, this);
    }

    public CycleReceiverOptions[] getCycleReceiversOptions() {
        return cycleReceiversOptions;
    }

    public abstract LogValue getLogValue();

    @Override
    public void putRaw(String key, byte[] value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
            "You cannot put raw to field '" + key + "' (timestamp: " + timestamp + ").");
    }

    @Override
    public void putBoolean(String key, boolean value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
                "You cannot put boolean to field '" + key + "' (timestamp: " + timestamp + ").");
    }

    @Override
    public void putInteger(String key, long value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
                "You cannot put integer to field '" + key + "' (timestamp: " + timestamp + ").");
    }

    @Override
    public void putFloat(String key, float value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
                "You cannot put float to field '" + key + "' (timestamp: " + timestamp + ").");
    }

    @Override
    public void putDouble(String key, double value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
                "You cannot put double to field '" + key + "' (timestamp: " + timestamp + ").");
    }

    @Override
    public void putString(String key, String value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
                "You cannot put string to field '" + key + "' (timestamp: " + timestamp + ").");
    }

    @Override
    public void putBooleanArray(String key, boolean[] value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
                "You cannot put boolean array to field '" + key + "' (timestamp: " + timestamp + ").");
    }

    @Override
    public void putIntegerArray(String key, long[] value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
                "You cannot put integer array to field '" + key + "' (timestamp: " + timestamp + ").");
    }

    @Override
    public void putFloatArray(String key, float[] value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
                "You cannot put float array to field '" + key + "' (timestamp: " + timestamp + ").");
    }

    @Override
    public void putDoubleArray(String key, double[] value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
                "You cannot put double array to field '" + key + "' (timestamp: " + timestamp + ").");
    }

    @Override
    public void putStringArray(String key, String[] value, long timestamp, Object options) {
        throw new UnsupportedOperationException(
                "You cannot put string array to field '" + key + "' (timestamp: " + timestamp + ").");
    }
}
