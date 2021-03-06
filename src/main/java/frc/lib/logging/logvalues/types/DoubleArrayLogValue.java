package frc.lib.logging.logvalues.types;

import java.util.Arrays;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class DoubleArrayLogValue extends LogValue {
    private final double[] value;

    public DoubleArrayLogValue(double[] value, CycleReceiverOptions[] cycleReceiversOptions) {
        super(LoggableType.DOUBLE_ARRAY, cycleReceiversOptions);
        this.value = value;
    }

    @Override
    public void putInDataReceiver(DataReceiver writer, String key, long timestamp, Object options) {
        writer.putDoubleArray(key, value, timestamp, options);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DoubleArrayLogValue
                && Arrays.equals(this.value, ((DoubleArrayLogValue) obj).value);
    }
}