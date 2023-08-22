package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.api.fields.RealDataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.StringArrayLogValue;

public class StringArrayField extends RealDataField implements Supplier<String[]> {
    private final Supplier<String[]> valueSupplier;
    private String[] value = {};

    public StringArrayField(String key, Supplier<String[]> valueSupplier) {
        super(key);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new StringArrayLogValue(valueSupplier.get());
    }

    @Override
    public void putStringArray(String key, String[] value, long timestamp) {
        this.value = value;
    }

    @Override
    public String[] get() {
        return value;
    }
}
