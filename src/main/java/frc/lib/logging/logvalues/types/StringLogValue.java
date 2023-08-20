package frc.lib.logging.logvalues.types;

import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class StringLogValue extends LogValue {
    private final String value;

    public StringLogValue(String value) {
        super(LoggableType.STRING);
        this.value = value;
    }

    @Override
    public void log(DataReceiver writer, String key, long timestamp) {
        writer.putString(key, value, timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringLogValue
                && this.value.equals(((StringLogValue) obj).value);
    }
}