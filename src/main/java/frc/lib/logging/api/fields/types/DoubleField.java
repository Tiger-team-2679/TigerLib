package frc.lib.logging.api.fields.types;

import java.util.function.DoubleSupplier;

import frc.lib.logging.api.fields.RealDataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.DoubleLogValue;

public class DoubleField extends RealDataField implements DoubleSupplier {
    private final DoubleSupplier valueSupplier;
    private double value = 0;

    public DoubleField(String key, DoubleSupplier valueSupplier) {
        super(key);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new DoubleLogValue(valueSupplier.getAsDouble());
    }

    @Override
    public void putDouble(String key, double value, long timestamp) {
        this.value = value;
    }

    @Override
    public double getAsDouble() {
        return value;
    }
}
