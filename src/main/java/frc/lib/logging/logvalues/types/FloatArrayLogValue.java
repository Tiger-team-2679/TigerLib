package frc.lib.logging.logvalues.types;

import java.util.Arrays;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class FloatArrayLogValue extends LogValue {
    private final float[] value;

    public FloatArrayLogValue(float[] value, CycleReceiverOptions[] cycleReceiversOptions) {
        super(LoggableType.FLOAT_ARRAY, cycleReceiversOptions);
        this.value = value;
    }

    @Override
    public void putInDataReceiver(DataReceiver writer, String key, long timestamp, Object options) {
        writer.putFloatArray(key, value, timestamp, options);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FloatArrayLogValue
                && Arrays.equals(this.value, ((FloatArrayLogValue) obj).value);
    }
}