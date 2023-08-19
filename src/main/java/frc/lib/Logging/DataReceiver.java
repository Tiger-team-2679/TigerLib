package frc.lib.logging;

public interface DataReceiver {
    public void putRaw(String key, byte[] value, long timestamp);

    public void putBoolean(String key, boolean value, long timestamp);

    public void putInteger(String key, long value, long timestamp);

    public void putFloat(String key, float value, long timestamp);

    public void putDouble(String key, double value, long timestamp);

    public void putString(String key, String value, long timestamp);

    public void putBooleanArray(String key, boolean[] value, long timestamp);

    public void putIntegerArray(String key, long[] value, long timestamp);

    public void putFloatArray(String key, float[] value, long timestamp);

    public void putDoubleArray(String key, double[] value, long timestamp);

    public void putStringArray(String key, String[] value, long timestamp);
}
