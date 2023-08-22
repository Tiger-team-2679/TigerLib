package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import frc.lib.logging.api.fields.RealDataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.RawLogValue;

public class RawField extends RealDataField implements Supplier<byte[]> {
    private final Supplier<byte[]> valueSupplier;
    private byte[] value = {};

    public RawField(String key, Supplier<byte[]> valueSupplier) {
        super(key);
        this.valueSupplier = valueSupplier;
    }

    @Override
    public LogValue getLogValue() {
        return new RawLogValue(valueSupplier.get());
    }

    @Override
    public void putRaw(String key, byte[] value, long timestamp) {
        this.value = value;
    }

    @Override
    public byte[] get() {
        return value;
    }
}
