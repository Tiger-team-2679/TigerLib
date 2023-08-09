package frc.lib.Logging;

public class LogValue {
    private final Object value;
    private final LoggableTypes type;

    public LogValue(byte[] value) {
        type = LoggableTypes.Raw;
        this.value = value;
    }

    public LogValue(boolean value) {
        type = LoggableTypes.Boolean;
        this.value = value;
    }

    public LogValue(long value) {
        type = LoggableTypes.Integer;
        this.value = value;
    }

    public LogValue(float value) {
        type = LoggableTypes.Float;
        this.value = value;
    }

    public LogValue(double value) {
        type = LoggableTypes.Double;
        this.value = value;
    }

    public LogValue(String value) {
        type = LoggableTypes.String;
        this.value = value != null ? value : "";
    }

    public LogValue(boolean[] value) {
        type = LoggableTypes.BooleanArray;
        this.value = value;
    }

    public LogValue(long[] value) {
        type = LoggableTypes.IntegerArray;
        this.value = value;
    }

    public LogValue(float[] value) {
        type = LoggableTypes.FloatArray;
        this.value = value;
    }

    public LogValue(double[] value) {
        type = LoggableTypes.DoubleArray;
        this.value = value;
    }

    public LogValue(String[] value) {
        type = LoggableTypes.StringArray;
        this.value = value;
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

    public LoggableTypes getType() {
        return type;
    }
}