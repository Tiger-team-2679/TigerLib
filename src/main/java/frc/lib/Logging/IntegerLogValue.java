// package frc.lib.Logging;

// import edu.wpi.first.util.datalog.DataLogRecord;

// public class IntegerWPILogValue extends WPILogValue {
//     private long value;

//     public IntegerWPILogValue(DataLogRecord record)
//     {
//         value = record.getInteger();
//     }

//     @Override
//     public LoggableTypes getLoggableType()
//     {
//         return LoggableTypes.INTEGER;
//     }

//     @Override
//     public void Log(DataLog log, int entryId, long timestamp)
//     {
//         log.(entryId, value, timestamp);
//     }
// }