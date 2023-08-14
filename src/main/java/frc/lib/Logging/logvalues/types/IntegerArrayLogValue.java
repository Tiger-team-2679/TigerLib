package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class IntegerArrayLogValue extends LogValue {
    private final long[] value;

    public IntegerArrayLogValue(long[] value) {
        super(LoggableType.INTEGER_ARRAY);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeIntegerArray(key, value, timestamp);
    }
}