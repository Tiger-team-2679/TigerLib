package frc.lib.logging.logvalues.types;

import java.util.Arrays;

import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;
import frc.lib.logging.logvalues.LogValue;

public class RawLogValue extends LogValue {
    private final byte[] value;

    public RawLogValue(byte[] value) {
        super(LoggableType.RAW);
        this.value = value;
    }

    @Override
    public void log(DataReceiver writer, String key, long timestamp) {
        writer.putRaw(key, value, timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RawLogValue
                && Arrays.equals(this.value, ((RawLogValue) obj).value);
    }
}
