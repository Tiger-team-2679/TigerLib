package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class FloatArrayLogValue extends LogValue {
    private final float[] value;

    public FloatArrayLogValue(float[] value) {
        super(LoggableType.FLOAT_ARRAY);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeFloatArray(key, value, timestamp);
    }
}