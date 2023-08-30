package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.FloatArrayLogValue;

public class FloatArrayField extends DataField implements Supplier<float[]> {
    private final Supplier<float[]> valueSupplier;
    private float[] value = {};

    public FloatArrayField(String key, Supplier<float[]> valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new FloatArrayLogValue(valueSupplier.get(), getCycleReceiversOptions());
    }

    @Override
    public void putFloatArray(String key, float[] value, long timestamp) {
        this.value = value;
    }

    @Override
    public float[] get() {
        return value;
    }
}
