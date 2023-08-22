package frc.lib.logging.api.fields.types;

import java.util.function.BooleanSupplier;

import frc.lib.logging.api.fields.RealDataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.BooleanLogValue;

public class BooleanField extends RealDataField implements BooleanSupplier {
    private final BooleanSupplier valueSupplier;
    private boolean value = false;

    public BooleanField(String key, BooleanSupplier valueSupplier) {
        super(key);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new BooleanLogValue(valueSupplier.getAsBoolean());
    }

    @Override
    public void putBoolean(String key, boolean value, long timestamp) {
        this.value = value;
    }

    @Override
    public boolean getAsBoolean() {
        return value;
    }
}
