package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.api.fields.RealDataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.FloatArrayLogValue;

public class FloatArrayField extends RealDataField implements Supplier<float[]> {
    private final Supplier<float[]> valueSupplier;
    private float[] value = {};

    public FloatArrayField(String key, Supplier<float[]> valueSupplier) {
        super(key);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new FloatArrayLogValue(valueSupplier.get());
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
