package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.DoubleArrayLogValue;

public class DoubleArrayField extends DataField implements Supplier<double[]> {
    private final Supplier<double[]> valueSupplier;
    private double[] value = {};

    public DoubleArrayField(String key, Supplier<double[]> valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new DoubleArrayLogValue(valueSupplier.get(), getCycleReceiversOptions());
    }

    @Override
    public void putDoubleArray(String key, double[] value, long timestamp) {
        this.value = value;
    }

    @Override
    public double[] get() {
        return value;
    }
}
