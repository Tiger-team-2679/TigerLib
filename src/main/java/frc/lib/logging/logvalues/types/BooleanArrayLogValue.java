package frc.lib.logging.logvalues.types;

import java.util.Arrays;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class BooleanArrayLogValue extends LogValue {
    private final boolean[] value;

    public BooleanArrayLogValue(boolean[] value, CycleReceiverOptions[] cycleReceiversOptions) {
        super(LoggableType.BOOLEAN_ARRAY, cycleReceiversOptions);
        this.value = value;
    }

    @Override
    public void putInDataReceiver(DataReceiver writer, String key, long timestamp, Object options) {
        writer.putBooleanArray(key, value, timestamp, options);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanArrayLogValue
                && Arrays.equals(this.value, ((BooleanArrayLogValue) obj).value);
    }
}