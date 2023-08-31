package frc.lib.logging.networktables;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.networktables.GenericPublisher;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.lib.logging.CycleReceiverBase;
import frc.lib.logging.LoggableType;

public class NT4Publisher extends CycleReceiverBase<NT4Options> {
    public NT4Publisher() {
        super(NT4Options.class);
    }

    private final NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
    private final Map<String, GenericPublisher> publishers = new HashMap<>();

    private GenericPublisher getOrCreateEntry(String key, LoggableType type) {
        return publishers.computeIfAbsent(
                key,
                k -> networkTableInstance.getTopic(key).genericPublish(type.asNT4Type()));
    }

    @Override
    public void putRaw(String key, byte[] value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.RAW).setRaw(value);
    }

    @Override
    public void putBoolean(String key, boolean value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.BOOLEAN).setBoolean(value);
    }

    @Override
    public void putInteger(String key, long value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.INTEGER).setInteger(value);
    }

    @Override
    public void putFloat(String key, float value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.FLOAT).setFloat(value);
    }

    @Override
    public void putDouble(String key, double value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.DOUBLE).setDouble(value);
    }

    @Override
    public void putString(String key, String value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.STRING).setString(value);
    }

    @Override
    public void putBooleanArray(String key, boolean[] value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.BOOLEAN_ARRAY).setBooleanArray(value);
    }

    @Override
    public void putIntegerArray(String key, long[] value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.INTEGER_ARRAY).setIntegerArray(value);
    }

    @Override
    public void putFloatArray(String key, float[] value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.FLOAT_ARRAY).setFloatArray(value);
    }

    @Override
    public void putDoubleArray(String key, double[] value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.DOUBLE_ARRAY).setDoubleArray(value);
    }

    @Override
    public void putStringArray(String key, String[] value, long timestamp, NT4Options options) {
        getOrCreateEntry(key, LoggableType.STRING_ARRAY).setStringArray(value);
    }
}
