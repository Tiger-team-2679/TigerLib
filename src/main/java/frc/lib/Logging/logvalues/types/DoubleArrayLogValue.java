package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class DoubleArrayLogValue extends LogValue {
    private final double[] value;

    public DoubleArrayLogValue(double[] value) {
        super(LoggableType.DOUBLE_ARRAY);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeDoubleArray(key, value, timestamp);
    }
}