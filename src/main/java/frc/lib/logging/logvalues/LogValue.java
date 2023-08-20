package frc.lib.logging.logvalues;

import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;

public abstract class LogValue {
    private final LoggableType type;

    protected LogValue(LoggableType type) {
        this.type = type;
    }

    public LoggableType getType() {
        return type;
    }

    public abstract void log(DataReceiver writer, String key, long timestamp);

    @Override
    public abstract boolean equals(Object obj);
}
