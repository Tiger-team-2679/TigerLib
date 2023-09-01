package frc.lib.logging.api.fields.types;

import java.util.function.BooleanSupplier;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.BooleanLogValue;

public class BooleanField extends DataField implements BooleanSupplier {
    private final BooleanSupplier valueSupplier;
    private boolean value = false;

    public BooleanField(String key, BooleanSupplier valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new BooleanLogValue(valueSupplier.getAsBoolean(), getCycleReceiversOptions());
    }

    @Override
    public void putBoolean(String key, boolean value, long timestamp, Object options) {
        this.value = value;
    }

    @Override
    public boolean getAsBoolean() {
        return value;
    }
}
