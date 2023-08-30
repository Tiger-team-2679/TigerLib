package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.IntegerArrayLogValue;

public class IntegerArrayField extends DataField implements Supplier<long[]> {
    private final Supplier<long[]> valueSupplier;
    private long[] value = {};

    public IntegerArrayField(String key, Supplier<long[]> valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new IntegerArrayLogValue(valueSupplier.get(), getCycleReceiversOptions());
    }

    @Override
    public void putIntegerArray(String key, long[] value, long timestamp) {
        this.value = value;
    }

    @Override
    public long[] get() {
        return value;
    }
}
