package frc.lib.logging.logvalues.types;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class BooleanLogValue extends LogValue {
    private final boolean value;

    public BooleanLogValue(boolean value, CycleReceiverOptions[] cycleReceiversOptions) {
        super(LoggableType.BOOLEAN, cycleReceiversOptions);
        this.value = value;
    }

    @Override
    public void putInDataReceiver(DataReceiver writer, String key, long timestamp, Object options) {
        writer.putBoolean(key, value, timestamp, options);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanLogValue
                && this.value == ((BooleanLogValue) obj).value;
    }
}
