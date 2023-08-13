package frc.lib.Logging;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.RobotController;
import frc.lib.Logging.logvalues.LogValue;

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
     */
    public void put(String key, LogValue value) {
        LogValue currentValue = table.get(key);
        if (currentValue == null || value.getType() == currentValue.getType())
            table.put(key, value);
    }

    public Map<String, LogValue> getAll() {
        return table;
    }
}
