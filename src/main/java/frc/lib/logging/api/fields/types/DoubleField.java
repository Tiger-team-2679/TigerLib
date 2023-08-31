package frc.lib.logging.api.fields.types;

import java.util.function.DoubleSupplier;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.DoubleLogValue;

public class DoubleField extends DataField implements DoubleSupplier {
    private final DoubleSupplier valueSupplier;
    private double value = 0;

    public DoubleField(String key, DoubleSupplier valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new DoubleLogValue(valueSupplier.getAsDouble(), getCycleReceiversOptions());
    }

    @Override
    public void putDouble(String key, double value, long timestamp, Object options) {
        this.value = value;
    }

    @Override
    public double getAsDouble() {
        return value;
    }
}
