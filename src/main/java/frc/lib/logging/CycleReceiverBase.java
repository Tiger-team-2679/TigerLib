package frc.lib.logging;

public abstract class CycleReceiverBase<T extends CycleReceiverOptions> implements CycleReceiver {
    private final Class<T> optionsClass;

    protected CycleReceiverBase(Class<T> optionsClass) {
        this.optionsClass = optionsClass;
    }

    public final boolean isOptionsForThisCycleReceiver(CycleReceiverOptions options) {
        return optionsClass.isInstance(options);
    }

    private final T validateAndCastOptions(Object options) {
        if (optionsClass.isInstance(options)) {
            return optionsClass.cast(options);
        }
        throw new IllegalArgumentException(
                "expected options of type " + optionsClass.getSimpleName() +
                        " but got options of type " + options.getClass().getSimpleName());
    }

    @Override
    public final void putRaw(String key, byte[] value, long timestamp, Object options) {
        putRaw(key, value, timestamp, validateAndCastOptions(options));
    }

    @Override
    public final void putBoolean(String key, boolean value, long timestamp, Object options) {
        putBoolean(key, value, timestamp, validateAndCastOptions(options));
    }

    @Override
    public final void putInteger(String key, long value, long timestamp, Object options) {
        putInteger(key, value, timestamp, validateAndCastOptions(options));
    }

    @Override
    public final void putFloat(String key, float value, long timestamp, Object options) {
        putFloat(key, value, timestamp, validateAndCastOptions(options));
    }

    @Override
    public final void putDouble(String key, double value, long timestamp, Object options) {
        putDouble(key, value, timestamp, validateAndCastOptions(options));
    }

    @Override
    public final void putString(String key, String value, long timestamp, Object options) {
        putString(key, value, timestamp, validateAndCastOptions(options));
    }

    @Override
    public final void putBooleanArray(String key, boolean[] value, long timestamp, Object options) {
        putBooleanArray(key, value, timestamp, validateAndCastOptions(options));
    }

    @Override
    public final void putIntegerArray(String key, long[] value, long timestamp, Object options) {
        putIntegerArray(key, value, timestamp, validateAndCastOptions(options));
    }

    @Override
    public final void putFloatArray(String key, float[] value, long timestamp, Object options) {
        putFloatArray(key, value, timestamp, validateAndCastOptions(options));
    }

    @Override
    public final void putDoubleArray(String key, double[] value, long timestamp, Object options) {
        putDoubleArray(key, value, timestamp, validateAndCastOptions(options));
    }

    @Override
    public final void putStringArray(String key, String[] value, long timestamp, Object options) {
        putStringArray(key, value, timestamp, validateAndCastOptions(options));
    }

    public abstract void putRaw(String key, byte[] value, long timestamp, T options);

    public abstract void putBoolean(String key, boolean value, long timestamp, T options);

    public abstract void putInteger(String key, long value, long timestamp, T options);

    public abstract void putFloat(String key, float value, long timestamp, T options);

    public abstract void putDouble(String key, double value, long timestamp, T options);

    public abstract void putString(String key, String value, long timestamp, T options);

    public abstract void putBooleanArray(String key, boolean[] value, long timestamp, T options);

    public abstract void putIntegerArray(String key, long[] value, long timestamp, T options);

    public abstract void putFloatArray(String key, float[] value, long timestamp, T options);

    public abstract void putDoubleArray(String key, double[] value, long timestamp, T options);

    public abstract void putStringArray(String key, String[] value, long timestamp, T options);
}
