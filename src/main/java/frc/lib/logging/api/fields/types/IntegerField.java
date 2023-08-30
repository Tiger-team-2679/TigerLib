package frc.lib.logging.api.fields.types;

import java.util.function.LongSupplier;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.IntegerLogValue;

public class IntegerField extends DataField implements LongSupplier {
    private final LongSupplier valueSupplier;
    private long value = 0; 

    public IntegerField(String key, LongSupplier valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new IntegerLogValue(valueSupplier.getAsLong(), getCycleReceiversOptions());
    }

    @Override
    public void putInteger(String key, long value, long timestamp) {
        this.value = value;
    }

    @Override
    public long getAsLong() {
        return value;
    }
}
