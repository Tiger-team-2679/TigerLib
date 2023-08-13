package frc.lib.Logging;

import java.util.List;

public enum LoggableType {
    RAW, BOOLEAN, INTEGER, FLOAT, DOUBLE, STRING, BOOLEAN_ARRAY, INTEGER_ARRAY, FLOAT_ARRAY, DOUBLE_ARRAY, STRING_ARRAY;

    // https://github.com/wpilibsuite/allwpilib/blob/main/wpiutil/doc/datalog.adoc#data-types
    private static final List<String> wpilogTypes = List.of("raw", "boolean", "int64", "float", "double", "string",
            "boolean[]", "int64[]", "float[]", "double[]", "string[]");

    // https://github.com/wpilibsuite/allwpilib/blob/main/ntcore/doc/networktables4.adoc#supported-data-types
    private static final List<String> nt4Types = List.of("raw", "boolean", "int", "float", "double", "string",
            "boolean[]", "int[]", "float[]", "double[]", "string[]");

    private LoggableType(){

    }
    /**
     * Returns the standard string type for WPILOGs.
     */
    public String asWPILOGType() {
        return wpilogTypes.get(this.ordinal());
    }

    /**
     * Returns the standard string type for NT4.
     */
    public String asNT4Type() {
        return nt4Types.get(this.ordinal());
    }

    /**
     * Returns the type based on a standard string type for WPILOGs.
     * Throws IllegalArgumentException if the type is not recognized.
     */
    public static LoggableType fromWPILOGType(String type) {
        int index = wpilogTypes.indexOf(type);
        if (index == -1)
            throw new IllegalArgumentException("Unknown WPILOG type: " + type);
        return LoggableType.values()[index];
    }

    /**
     * Returns the type based on a standard string type for NT4.
     * Throws IllegalArgumentException if the type is not recognized.
     */
    public static LoggableType fromNT4Type(String type) {
        int index = nt4Types.indexOf(type);
        if (index == -1)
            throw new IllegalArgumentException("Unknown NT4 type: " + type);
        return LoggableType.values()[index];
    }
}