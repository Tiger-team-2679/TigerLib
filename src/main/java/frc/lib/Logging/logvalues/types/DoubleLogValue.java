package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class DoubleLogValue extends LogValue {
    private final double value;

    public DoubleLogValue(double value) {
        super(LoggableType.DOUBLE);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeDouble(key, value, timestamp);
    }
}