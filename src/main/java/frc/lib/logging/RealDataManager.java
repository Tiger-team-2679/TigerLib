package frc.lib.logging;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;

import frc.lib.logging.api.fields.RealDataField;
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
            try{
                LogValue logValue = table.get(key);
                if(logValue == null)
                    throw new UnexpectedException("the value for field " + key + " was not provided.");
                logValue.putInDataReceiver(realDataField, key, table.getTimestamp());    
            } catch(UnexpectedException e){
                e.printStackTrace();
            }
        });
    }

    public static void registerDataField(String key, RealDataField dataField) {
        if (dataFields.containsKey(key))
            throw new IllegalArgumentException("can't register field, key already exists: " + key);
        if(key.equals(LogConstants.CYCLE_TIMESTAMP_KEY))
            throw new IllegalArgumentException("can't register field with the cycle timestamp key.");
        
        dataFields.put(key, dataField);
    }
}
