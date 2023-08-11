package frc.lib.Logging;

import java.util.List;

public enum LoggableTypes {
  Raw, Boolean, Integer, Float, Double, String, BooleanArray, IntegerArray, FloatArray, DoubleArray, StringArray;

  // https://github.com/wpilibsuite/allwpilib/blob/main/wpiutil/doc/datalog.adoc#data-types
  private static final List<String> wpilogTypes = List.of("raw", "boolean", "int64", "float", "double", "string",
      "boolean[]", "int64[]", "float[]", "double[]", "string[]");

  // https://github.com/wpilibsuite/allwpilib/blob/main/ntcore/doc/networktables4.adoc#supported-data-types
  private static final List<String> nt4Types = List.of("raw", "boolean", "int", "float", "double", "string",
      "boolean[]", "int[]", "float[]", "double[]", "string[]");

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
   */
  public static LoggableTypes fromWPILOGType(String type) {
    int index = wpilogTypes.indexOf(type);
    return index != -1 ? LoggableTypes.values()[index] : null;
  }

  /**
   * Returns the type based on a standard string type for NT4.
   */
  public static LoggableTypes fromNT4Type(String type) {
    int index = nt4Types.indexOf(type);
    return index != -1 ? LoggableTypes.values()[index] : null;

  }
}