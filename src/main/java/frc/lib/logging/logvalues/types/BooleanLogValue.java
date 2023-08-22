package frc.lib.logging.logvalues.types;

import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class BooleanLogValue extends LogValue {
    private final boolean value;

    public BooleanLogValue(boolean value) {
        super(LoggableType.BOOLEAN);
        this.value = value;
    }

    @Override
    public void putInDataReceiver(DataReceiver writer, String key, long timestamp) {
        writer.putBoolean(key, value, timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanLogValue
                && this.value == ((BooleanLogValue) obj).value;
    }
}
