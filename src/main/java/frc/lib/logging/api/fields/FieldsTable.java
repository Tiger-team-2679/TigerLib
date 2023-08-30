package frc.lib.logging.api.fields;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

import edu.wpi.first.util.function.FloatSupplier;
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

    public Supplier<byte[]> addRaw(String name, Supplier<byte[]> valueSupplier) {
        return new RawField(prefix + name, valueSupplier);
    }

    public BooleanSupplier addBoolean(String name, BooleanSupplier valueSupplier) {
        return new BooleanField(prefix + name, valueSupplier);
    }

    public LongSupplier addInteger(String name, LongSupplier valueSupplier) {
        return new IntegerField(prefix + name, valueSupplier);
    }

    public FloatSupplier addFloat(String name, FloatSupplier valueSupplier) {
        return new FloatField(prefix + name, valueSupplier);
    }

    public DoubleSupplier addDouble(String name, DoubleSupplier valueSupplier) {
        return new DoubleField(prefix + name, valueSupplier);
    }

    public Supplier<String> addString(String name, Supplier<String> valueSupplier) {
        return new StringField(prefix + name, valueSupplier);
    }

    public Supplier<boolean[]> addBooleanArray(String name, Supplier<boolean[]> valueSupplier) {
        return new BooleanArrayField(prefix + name, valueSupplier);
    }

    public Supplier<long[]> addIntegerArray(String name, Supplier<long[]> valueSupplier) {
        return new IntegerArrayField(prefix + name, valueSupplier);
    }

    public Supplier<float[]> addFloatArray(String name, Supplier<float[]> valueSupplier) {
        return new FloatArrayField(prefix + name, valueSupplier);
    }

    public Supplier<double[]> addDoubleArray(String name, Supplier<double[]> valueSupplier) {
        return new DoubleArrayField(prefix + name, valueSupplier);
    }

    public Supplier<String[]> addStringArray(String name, Supplier<String[]> valueSupplier) {
        return new StringArrayField(prefix + name, valueSupplier);
    }

    public void recordValue(String name, byte[] value) {
        Logger.putLogValue(prefix + name, new RawLogValue(value));
    }

    public void recordValue(String name, boolean value) {
        Logger.putLogValue(prefix + name, new BooleanLogValue(value));
    }

    public void recordValue(String name, long value) {
        Logger.putLogValue(prefix + name, new IntegerLogValue(value));
    }

    public void recordValue(String name, float value) {
        Logger.putLogValue(prefix + name, new FloatLogValue(value));
    }

    public void recordValue(String name, double value) {
        Logger.putLogValue(prefix + name, new DoubleLogValue(value));
    }

    public void recordValue(String name, String value) {
        Logger.putLogValue(prefix + name, new StringLogValue(value));
    }

    public void recordValue(String name, boolean[] value) {
        Logger.putLogValue(prefix + name, new BooleanArrayLogValue(value));
    }

    public void recordValue(String name, long[] value) {
        Logger.putLogValue(prefix + name, new IntegerArrayLogValue(value));
    }

    public void recordValue(String name, float[] value) {
        Logger.putLogValue(prefix + name, new FloatArrayLogValue(value));
    }

    public void recordValue(String name, double[] value) {
        Logger.putLogValue(prefix + name, new DoubleArrayLogValue(value));
    }

    public void recordValue(String name, String[] value) {
        Logger.putLogValue(prefix + name, new StringArrayLogValue(value));
    }
}
