package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.StringLogValue;

public class StringField extends DataField implements Supplier<String> {
    private final Supplier<String> valueSupplier;
    private String value = "";

    public StringField(String key, Supplier<String> valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new StringLogValue(valueSupplier.get(), getCycleReceiversOptions());
    }

    @Override
    public void putString(String key, String value, long timestamp, Object options) {
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }
}
