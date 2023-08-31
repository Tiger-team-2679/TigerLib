package frc.lib.logging.logvalues.types;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class StringLogValue extends LogValue {
    private final String value;

    public StringLogValue(String value, CycleReceiverOptions[] cycleReceiversOptions) {
        super(LoggableType.STRING, cycleReceiversOptions);
        this.value = value;
    }

    @Override
    public void putInDataReceiver(DataReceiver writer, String key, long timestamp, Object options) {
        writer.putString(key, value, timestamp, options);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringLogValue
                && this.value.equals(((StringLogValue) obj).value);
    }
}