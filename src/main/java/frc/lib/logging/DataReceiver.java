package frc.lib.logging;

public interface DataReceiver {
    public void putRaw(String key, byte[] value, long timestamp, Object options);

    public void putBoolean(String key, boolean value, long timestamp, Object options);

    public void putInteger(String key, long value, long timestamp, Object options);

    public void putFloat(String key, float value, long timestamp, Object options);

    public void putDouble(String key, double value, long timestamp, Object options);

    public void putString(String key, String value, long timestamp, Object options);

    public void putBooleanArray(String key, boolean[] value, long timestamp, Object options);

    public void putIntegerArray(String key, long[] value, long timestamp, Object options);

    public void putFloatArray(String key, float[] value, long timestamp, Object options);

    public void putDoubleArray(String key, double[] value, long timestamp, Object options);

    public void putStringArray(String key, String[] value, long timestamp, Object options);
}
