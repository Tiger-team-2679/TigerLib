package frc.lib.logging;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.RobotController;
import frc.lib.logging.logvalues.LogValue;

public class LogTable {
    private final Map<String, LogValue> table = new HashMap<>();
    private long timestamp = RobotController.getFPGATime();

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    /**
     * puts a value into the log table. will skip if the type of the new value does
     * not match the type of the current value.
     * 
     * @param key the string key in the log.
     * @param value the new value.
     * @throws IllegalArgumentException when the new type is
     */
    public void put(String key, LogValue value) throws IllegalArgumentException {
        LogValue currentValue = table.get(key);
        if (currentValue == null || value.getType() == currentValue.getType())
            table.put(key, value);
        else
            throw new IllegalArgumentException("Conflicting log value types for key '" + key +
                    "': Existing type is " + currentValue.getType() + ", while new type is " + value.getType());
    }

    public Map<String, LogValue> getAll() {
        return table;
    }

    public LogValue get(String key) {
        return table.get(key);
    }

    public LogTable clone() {
        LogTable newTable = new LogTable();
        newTable.timestamp = this.timestamp;
        newTable.table.putAll(this.table);
        return newTable;
    }
}
