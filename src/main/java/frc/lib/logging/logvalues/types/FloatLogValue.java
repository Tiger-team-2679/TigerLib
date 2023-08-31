package frc.lib.logging.logvalues.types;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class FloatLogValue extends LogValue {
    private final float value;

    public FloatLogValue(float value, CycleReceiverOptions[] cycleReceiversOptions) {
        super(LoggableType.FLOAT, cycleReceiversOptions);
        this.value = value;
    }

    @Override
    public void putInDataReceiver(DataReceiver writer, String key, long timestamp, Object options) {
        writer.putFloat(key, value, timestamp, options);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof FloatLogValue
                && this.value == ((FloatLogValue) obj).value;
    }
}