package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.BooleanArrayLogValue;

public class BooleanArrayField extends DataField implements Supplier<boolean[]> {
    private final Supplier<boolean[]> valueSupplier;
    private boolean[] value = {};

    public BooleanArrayField(String key, Supplier<boolean[]> valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new BooleanArrayLogValue(valueSupplier.get(), getCycleReceiversOptions());
    }

    @Override
    public void putBooleanArray(String key, boolean[] value, long timestamp, Object options) {
        this.value = value;
    }

    @Override
    public boolean[] get() {
        return value;
    }
}
