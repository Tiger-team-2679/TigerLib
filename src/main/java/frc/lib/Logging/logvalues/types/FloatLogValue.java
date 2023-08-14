package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class FloatLogValue extends LogValue {
    private final float value;

    public FloatLogValue(float value) {
        super(LoggableType.FLOAT);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeFloat(key, value, timestamp);
    }
}