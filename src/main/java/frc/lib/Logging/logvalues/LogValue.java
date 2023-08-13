package frc.lib.Logging.logvalues;

import frc.lib.Logging.LoggableType;
import frc.lib.Logging.Writer;

public abstract class LogValue {
    private final LoggableType type;

    protected LogValue(LoggableType type) {
        this.type = type;
    }

    public LoggableType getType() {
        return type;
    }

    public abstract void log(String key, Writer writer, long timestamp);
}
