package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class StringArrayLogValue extends LogValue {
    private final String[] value;

    public StringArrayLogValue(String[] value) {
        super(LoggableType.STRING_ARRAY);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeStringArray(key, value, timestamp);
    }
}