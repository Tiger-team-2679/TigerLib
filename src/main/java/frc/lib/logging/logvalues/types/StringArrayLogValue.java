package frc.lib.logging.logvalues.types;

import java.util.Arrays;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class StringArrayLogValue extends LogValue {
    private final String[] value;

    public StringArrayLogValue(String[] value, CycleReceiverOptions[] cycleReceiversOptions) {
        super(LoggableType.STRING_ARRAY, cycleReceiversOptions);
        this.value = value;
    }

    @Override
    public void putInDataReceiver(DataReceiver writer, String key, long timestamp, Object options) {
        writer.putStringArray(key, value, timestamp, options);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringArrayLogValue
                && Arrays.equals(this.value, ((StringArrayLogValue) obj).value);
    }
}