package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class BooleanLogValue extends LogValue {
    private final boolean value;

    public BooleanLogValue(boolean value) {
        super(LoggableType.BOOLEAN);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeBoolean(key, value, timestamp);
    }
}

