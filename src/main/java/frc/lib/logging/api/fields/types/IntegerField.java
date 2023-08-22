package frc.lib.logging.api.fields.types;

import java.util.function.LongSupplier;

import frc.lib.logging.api.fields.RealDataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.IntegerLogValue;

public class IntegerField extends RealDataField implements LongSupplier {
    private final LongSupplier valueSupplier;
    private long value = 0; 

    public IntegerField(String key, LongSupplier valueSupplier) {
        super(key);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new IntegerLogValue(valueSupplier.getAsLong());
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
