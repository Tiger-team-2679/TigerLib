package frc.lib.Logging;

public interface Writer {
    public void writeRaw(String key, byte[] value, long timestamp);

    public void writeBoolean(String key, boolean value, long timestamp);

    public void writeInteger(String key, long value, long timestamp);

    public void writeFloat(String key, float value, long timestamp);

    public void writeDouble(String key, double value, long timestamp);

    public void writeString(String key, String value, long timestamp);

    public void writeBooleanArray(String key, boolean[] value, long timestamp);

    public void writeIntegerArray(String key, long[] value, long timestamp);

    public void writeFloatArray(String key, float[] value, long timestamp);

    public void writeDoubleArray(String key, double[] value, long timestamp);

    public void writeStringArray(String key, String[] value, long timestamp);
}
