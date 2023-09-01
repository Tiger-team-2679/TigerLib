package frc.lib.logging.api.fields;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

import edu.wpi.first.util.function.FloatSupplier;
import frc.lib.logging.CycleReceiverOptions;
import frc.lib.logging.Logger;
import frc.lib.logging.api.fields.types.*;
import frc.lib.logging.logvalues.types.*;

public class FieldsTable {
    private final String prefix;

    public FieldsTable(String name) {
        prefix = name + "/";
    }

    public FieldsTable getSubTable(String name) {
        return new FieldsTable(prefix + name);
    }

    public Supplier<byte[]> addRaw(String name, Supplier<byte[]> valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new RawField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public BooleanSupplier addBoolean(String name, BooleanSupplier valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new BooleanField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public LongSupplier addInteger(String name, LongSupplier valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new IntegerField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public FloatSupplier addFloat(String name, FloatSupplier valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new FloatField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public DoubleSupplier addDouble(String name, DoubleSupplier valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new DoubleField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public Supplier<String> addString(String name, Supplier<String> valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new StringField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public Supplier<boolean[]> addBooleanArray(String name, Supplier<boolean[]> valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new BooleanArrayField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public Supplier<long[]> addIntegerArray(String name, Supplier<long[]> valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new IntegerArrayField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public Supplier<float[]> addFloatArray(String name, Supplier<float[]> valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new FloatArrayField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public Supplier<double[]> addDoubleArray(String name, Supplier<double[]> valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new DoubleArrayField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public Supplier<String[]> addStringArray(String name, Supplier<String[]> valueSupplier, CycleReceiverOptions ...cycleReceiversOptions) {
        return new StringArrayField(prefix + name, valueSupplier, cycleReceiversOptions);
    }

    public void recordValue(String name, byte[] value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new RawLogValue(value, cycleReceiversOptions));
    }

    public void recordValue(String name, boolean value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new BooleanLogValue(value, cycleReceiversOptions));
    }

    public void recordValue(String name, long value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new IntegerLogValue(value, cycleReceiversOptions));
    }

    public void recordValue(String name, float value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new FloatLogValue(value, cycleReceiversOptions));
    }

    public void recordValue(String name, double value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new DoubleLogValue(value, cycleReceiversOptions));
    }

    public void recordValue(String name, String value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new StringLogValue(value, cycleReceiversOptions));
    }

    public void recordValue(String name, boolean[] value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new BooleanArrayLogValue(value, cycleReceiversOptions));
    }

    public void recordValue(String name, long[] value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new IntegerArrayLogValue(value, cycleReceiversOptions));
    }

    public void recordValue(String name, float[] value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new FloatArrayLogValue(value, cycleReceiversOptions));
    }

    public void recordValue(String name, double[] value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new DoubleArrayLogValue(value, cycleReceiversOptions));
    }

    public void recordValue(String name, String[] value, CycleReceiverOptions ...cycleReceiversOptions) {
        Logger.recordValue(prefix + name, new StringArrayLogValue(value, cycleReceiversOptions));
    }
}
