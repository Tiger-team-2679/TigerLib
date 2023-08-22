package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.api.fields.RealDataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.BooleanArrayLogValue;

public class BooleanArrayField extends RealDataField implements Supplier<boolean[]> {
    private final Supplier<boolean[]> valueSupplier;
    private boolean[] value = {};

    public BooleanArrayField(String key, Supplier<boolean[]> valueSupplier) {
        super(key);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new BooleanArrayLogValue(valueSupplier.get());
    }

    @Override
    public void putBooleanArray(String key, boolean[] value, long timestamp) {
        this.value = value;
    }

    @Override
    public boolean[] get() {
        return value;
    }
}
