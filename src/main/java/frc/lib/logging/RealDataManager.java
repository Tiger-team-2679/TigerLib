package frc.lib.logging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.DriverStation;
import frc.lib.logging.fields.RealDataField;
import frc.lib.logging.logvalues.LogValue;

public class RealDataManager {
    private static final Map<String, RealDataField> dataFields = new HashMap<>();

    public static void updateTableToNextCycle(LogTable table) {
        dataFields.forEach((key, realDataField) -> {
            table.put(key, realDataField.getLogValue());
        });
    }

    public static void updateFieldsFromTable(LogTable table) {
        dataFields.forEach((key, realDataField) -> {
            LogValue logValue = table.get(key);
            if(logValue == null)
                DriverStation.reportError("the value for field " + key + " was not provided.", new Exception().getStackTrace()); 
            logValue.putInDataReceiver(realDataField, key, table.getTimestamp());
        });
    }

    public static void registerDataField(String key, RealDataField dataField) {
        if (dataFields.containsKey(key))
            throw new IllegalArgumentException("can't register field, key already exists: " + key);
        
        dataFields.put(key, dataField);
    }
}
