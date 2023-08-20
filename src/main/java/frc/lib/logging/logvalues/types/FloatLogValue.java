package frc.lib.logging.logvalues.types;

import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class FloatLogValue extends LogValue {
    private final float value;

    public FloatLogValue(float value) {
        super(LoggableType.FLOAT);
        this.value = value;
    }

    @Override
    public void log(DataReceiver writer, String key, long timestamp) {
        writer.putFloat(key, value, timestamp);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof FloatLogValue
                && this.value == ((FloatLogValue) obj).value;
    }
}