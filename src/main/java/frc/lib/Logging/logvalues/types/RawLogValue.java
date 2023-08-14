package frc.lib.Logging.logvalues.types;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;
import frc.lib.Logging.logvalues.LogValue;

public class RawLogValue extends LogValue {
    private final byte[] value;

    public RawLogValue(byte[] value) {
        super(LoggableType.RAW);
        this.value = value;
    }

    @Override
    public void log(String key, Writer writer, long timestamp) {
        writer.writeRaw(key, value, timestamp);
    }
}
