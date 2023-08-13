package frc.lib.Logging;

public class LogValue {
    private final Object value;
    private final LoggableType type;

    private LogValue(Object value, LoggableType type) {
        this.value = value;
        this.type = type;
    }

    public LogValue(byte[] value) {
        this(value, LoggableType.RAW);
    }

    public LogValue(boolean value) {
        this(value, LoggableType.BOOLEAN);
    }

    public LogValue(long value) {
        this(value, LoggableType.INTEGER);
    }

    public LogValue(float value) {
        this(value, LoggableType.FLOAT);
    }

    public LogValue(double value) {
        this(value, LoggableType.DOUBLE);
    }

    public LogValue(String value) {
        this(value != null ? value : "", LoggableType.STRING);
    }

    public LogValue(boolean[] value) {
        this(value, LoggableType.BOOLEAN_ARRAY);
    }

    public LogValue(long[] value) {
        this(value, LoggableType.INTEGER_ARRAY);
    }

    public LogValue(float[] value) {
        this(value, LoggableType.FLOAT_ARRAY);
    }

    public LogValue(double[] value) {
        this(value, LoggableType.DOUBLE_ARRAY);
    }

    public LogValue(String[] value) {
        this(value, LoggableType.STRING_ARRAY);
    }

    public byte[] asRaw() {
        return (byte[]) value;
    }

    public boolean asBoolean() {
        return (boolean) value;
    }

    public long asInteger() {
        return (long) value;
    }

    public float asFloat() {
        return (float) value;
    }

    public double asDouble() {
        return (double) value;
    }

    public String asString() {
        return (String) value;
    }

    public boolean[] asBooleanArray() {
        return (boolean[]) value;
    }

    public long[] asIntegerArray() {
        return (long[]) value;
    }

    public float[] asFloatArray() {
        return (float[]) value;
    }
    
    public double[] asDoubleArray() {
        return (double[]) value;
    }

    public String[] asStringArray() {
        return (String[]) value;
    }

    public LoggableType getType() {
        return type;
    }
}