package frc.lib.logging.logvalues;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.DataReceiver;
import frc.lib.logging.LoggableType;

public abstract class LogValue {
    private final LoggableType type;
    private final CycleReceiverOptions[] cycleReceiversOptions;

    protected LogValue(LoggableType type, CycleReceiverOptions[] cycleReceiversOptions) {
        this.type = type;
        this.cycleReceiversOptions = cycleReceiversOptions;
    }

    public CycleReceiverOptions[] getCycleReceiversOptions() {
        return cycleReceiversOptions;
    }

    public LoggableType getType() {
        return type;
    }

    public abstract void putInDataReceiver(DataReceiver dataReceiver, String key, long timestamp);

    @Override
    public abstract boolean equals(Object obj);
}
