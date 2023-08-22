package frc.lib.logging.api.fields;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

import edu.wpi.first.util.function.FloatSupplier;
import frc.lib.logging.api.fields.types.BooleanArrayField;
import frc.lib.logging.api.fields.types.BooleanField;
import frc.lib.logging.api.fields.types.DoubleArrayField;
import frc.lib.logging.api.fields.types.DoubleField;
import frc.lib.logging.api.fields.types.FloatArrayField;
import frc.lib.logging.api.fields.types.FloatField;
import frc.lib.logging.api.fields.types.IntegerArrayField;
import frc.lib.logging.api.fields.types.IntegerField;
import frc.lib.logging.api.fields.types.RawField;
import frc.lib.logging.api.fields.types.StringArrayField;
import frc.lib.logging.api.fields.types.StringField;

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

    public LongSupplier addInteger(String name, LongSupplier valueSupplier){
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
}
