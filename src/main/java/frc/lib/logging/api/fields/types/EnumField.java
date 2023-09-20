package frc.lib.logging.api.fields.types;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DriverStation;
import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.api.fields.DataField;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.logvalues.types.IntegerLogValue;
import frc.lib.logging.logvalues.types.StringLogValue;

public class EnumField<T extends Enum<T>> extends DataField implements Supplier<T> {
    private final Supplier<T> valueSupplier;
    private final boolean asString;
    private final Class<T> enumClass;
    private T value;

    public EnumField(
            String key,
            Supplier<T> valueSupplier,
            Class<T> enumClass,
            T defaultValue,
            boolean asString,
            CycleReceiverOptions[] cycleReceiversOptions) {
        super(key, cycleReceiversOptions);
        this.valueSupplier = valueSupplier;
        this.asString = asString;
        value = defaultValue;
        this.enumClass = enumClass;
    }

    @Override
    public LogValue getLogValue() {
        T enumValue = valueSupplier.get();
        return asString
                ? new StringLogValue(enumValue.name(), getCycleReceiversOptions())
                : new IntegerLogValue(enumValue.ordinal(), getCycleReceiversOptions());
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void putInteger(String key, long value, long timestamp, Object options) {
        for (T enumValue : enumClass.getEnumConstants()) {
            if (value == enumValue.ordinal()) {
                this.value = enumValue;
                return;
            }
        }
        DriverStation.reportWarning(
                "Tried to set illegal integer (ordinal) value to enum field `" + key + "`, (timestamp: " + timestamp + ").",
                new Exception().getStackTrace());
    }

    @Override
    public void putString(String key, String value, long timestamp, Object options) {
        for (T enumValue : enumClass.getEnumConstants()) {
            if (value.equals(enumValue.name())) {
                this.value = enumValue;
                return;
            }
        }
        DriverStation.reportWarning(
                "Tried to set illegal string (name) value '" + value + "' to enum field `" + key + "`, (timestamp: " + timestamp + ").",
                new Exception().getStackTrace());
    }
}
