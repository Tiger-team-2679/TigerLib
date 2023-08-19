package frc.lib.logging.logvalues.types;

import java.util.Arrays;

import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class DoubleArrayLogValue extends LogValue {
    private final double[] value;

    public DoubleArrayLogValue(double[] value) {
        super(LoggableType.DOUBLE_ARRAY);
        this.value = value;
    }

    @Override
    public void log(DataReceiver writer, String key, long timestamp) {
        writer.putDoubleArray(key, value, timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DoubleArrayLogValue
                && Arrays.equals(this.value, ((DoubleArrayLogValue) obj).value);
    }
}