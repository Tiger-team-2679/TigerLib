package frc.lib.logging;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.math.Pair;

public class CycleReceiversManager {
    private final List<Pair<CycleReceiver, CycleReceiverOptions>> cycleReceiverPairs = new ArrayList<>();
    private LogTable prevLogTable = null;

    /**
     * Put table in all data receivers.
     * 
     * @param cycleReceiver the table to put, each call should be a refrence to a
     *                      new instence.
     */
    public void putTable(LogTable logTable) {
        for (Pair<CycleReceiver, CycleReceiverOptions> cycleReceiverPair : cycleReceiverPairs) {
            CycleReceiver cycleReceiver = cycleReceiverPair.getFirst();
            CycleReceiverOptions defaultOptions = cycleReceiverPair.getSecond();

            cycleReceiver.putInteger(
                    LogConstants.CYCLE_TIMESTAMP_KEY,
                    logTable.getTimestamp(),
                    logTable.getTimestamp(),
                    defaultOptions);

            logTable.getAll().forEach((key, logValue) -> {
                CycleReceiverOptions options = getCycleReceiverOptions(
                        cycleReceiver,
                        logValue.getCycleReceiversOptions(),
                        defaultOptions);

                if (options.getIfEnabled() && (prevLogTable == null || !logValue.equals(prevLogTable.get(key))))
                    logValue.putInDataReceiver(cycleReceiver, key, logTable.getTimestamp(), options);
            });
        }

        prevLogTable = logTable;
    }

    private CycleReceiverOptions getCycleReceiverOptions(
            CycleReceiver cycleReceiver,
            CycleReceiverOptions[] cycleReceiversOptions,
            CycleReceiverOptions defaultOptions) {
        for (CycleReceiverOptions options : cycleReceiversOptions) {
            if (cycleReceiver.isOptionsForThisCycleReceiver(options))
                return options;
        }
        return defaultOptions;
    }

    public void registerCycleReceiver(CycleReceiver cycleReceiver, CycleReceiverOptions defaultOptions) {
        if (!cycleReceiver.isOptionsForThisCycleReceiver(defaultOptions))
            throw new IllegalArgumentException("cycle receiver " + cycleReceiver.getClass().getSimpleName()
                    + " cannot have default options of type " + defaultOptions.getClass().getSimpleName() + ".");
        cycleReceiverPairs.add(new Pair<>(cycleReceiver, defaultOptions));
    }
}
