package frc.lib.logging.logvalues.types;

import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class IntegerLogValue extends LogValue {
    private final long value;

    public IntegerLogValue(long value) {
        super(LoggableType.INTEGER);
        this.value = value;
    }

    @Override
    public void putInDataReceiver(DataReceiver writer, String key, long timestamp) {
        writer.putInteger(key, value, timestamp);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerLogValue
                && this.value == ((IntegerLogValue) obj).value;
    }
}