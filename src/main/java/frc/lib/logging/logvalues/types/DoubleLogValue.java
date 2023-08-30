package frc.lib.logging.logvalues.types;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class DoubleLogValue extends LogValue {
    private final double value;

    public DoubleLogValue(double value, CycleReceiverOptions[] cycleReceiversOptions) {
        super(LoggableType.DOUBLE, cycleReceiversOptions);
        this.value = value;
    }

    @Override
    public void putInDataReceiver(DataReceiver writer, String key, long timestamp) {
        writer.putDouble(key, value, timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DoubleLogValue
                && this.value == ((DoubleLogValue) obj).value;
    }
}