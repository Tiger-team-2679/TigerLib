package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class IntegerLogValue extends LogValue {
    private final long value;

    public IntegerLogValue(long value) {
        super(LoggableType.INTEGER);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeInteger(key, value, timestamp);
    }
}