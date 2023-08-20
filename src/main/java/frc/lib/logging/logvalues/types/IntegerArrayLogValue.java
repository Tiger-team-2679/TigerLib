package frc.lib.logging.logvalues.types;

import java.util.Arrays;

import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class IntegerArrayLogValue extends LogValue {
    private final long[] value;

    public IntegerArrayLogValue(long[] value) {
        super(LoggableType.INTEGER_ARRAY);
        this.value = value;
    }

    @Override
    public void log(DataReceiver writer, String key, long timestamp) {
        writer.putIntegerArray(key, value, timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerArrayLogValue
                && Arrays.equals(this.value, ((IntegerArrayLogValue) obj).value);
    }
}