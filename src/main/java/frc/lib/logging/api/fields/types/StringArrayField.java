package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.StringArrayLogValue;

public class StringArrayField extends DataField implements Supplier<String[]> {
    private final Supplier<String[]> valueSupplier;
    private String[] value = {};

    public StringArrayField(String key, Supplier<String[]> valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new StringArrayLogValue(valueSupplier.get(), getCycleReceiversOptions());
    }

    @Override
    public void putStringArray(String key, String[] value, long timestamp, Object options) {
        this.value = value;
    }

    @Override
    public String[] get() {
        return value;
    }
}
