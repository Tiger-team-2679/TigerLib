package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class StringLogValue extends LogValue {
    private final String value;

    public StringLogValue(String value) {
        super(LoggableType.STRING);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeString(key, value, timestamp);
    }
}