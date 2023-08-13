package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class BooleanArrayLogValue extends LogValue {
    private final boolean[] value;

    public BooleanArrayLogValue(boolean[] value) {
        super(LoggableType.BOOLEAN_ARRAY);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeBooleanArray(key, value, timestamp);
    }
}

