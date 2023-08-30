package frc.lib.logging.api.fields.types;

import edu.wpi.first.util.function.FloatSupplier;
import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.FloatLogValue;

public class FloatField extends DataField implements FloatSupplier {
    private final FloatSupplier valueSupplier;
    private float value = 0;

    public FloatField(String key, FloatSupplier valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new FloatLogValue(valueSupplier.getAsFloat(), getCycleReceiversOptions());
    }

    @Override
    public void putFloat(String key, float value, long timestamp) {
        this.value = value;
    }

    @Override
    public float getAsFloat() {
        return value;
    }
}
