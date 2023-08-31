package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.RawLogValue;

public class RawField extends DataField implements Supplier<byte[]> {
    private final Supplier<byte[]> valueSupplier;
    private byte[] value = {};

    public RawField(String key, Supplier<byte[]> valueSupplier, CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new RawLogValue(valueSupplier.get(), getCycleReceiversOptions());
    }

    @Override
    public void putRaw(String key, byte[] value, long timestamp, Object options) {
        this.value = value;
    }

    @Override
    public byte[] get() {
        return value;
    }
}
