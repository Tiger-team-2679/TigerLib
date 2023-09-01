package frc.lib.logging;

import java.util.ArrayList;
import java.util.List;

public class CycleReceiversManager {
    private final List<CycleReceiverWithOptions> cycleReceiverPairs = new ArrayList<>();
    private LogTable prevLogTable = null;

    /**
     * Put table in all data receivers.
     * 
     * @param cycleReceiver the table to put, each call should be a refrence to a
     *                      new instence.
     */
    public void putTable(LogTable logTable) {
        for (CycleReceiverWithOptions cycleReceiverWithOptions : cycleReceiverPairs) {
            CycleReceiver cycleReceiver = cycleReceiverWithOptions.getCycleReceiver();
            CycleReceiverOptions defaultOptions = cycleReceiverWithOptions.getDefaultOptions();
            CycleReceiverOptions cycleTimestampOptions = cycleReceiverWithOptions.getCycleTimestampOptions();

            if (cycleTimestampOptions.getIfEnabled())
                cycleReceiver.putInteger(
                        LogConstants.CYCLE_TIMESTAMP_KEY,
                        logTable.getTimestamp(),
                        logTable.getTimestamp(),
                        cycleTimestampOptions);

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

    public void registerCycleReceiver(
            CycleReceiver cycleReceiver,
            CycleReceiverOptions defaultOptions,
            CycleReceiverOptions cycleTimestampOptions) {
        if (!cycleReceiver.isOptionsForThisCycleReceiver(defaultOptions))
            throw new IllegalArgumentException("cycle receiver " + cycleReceiver.getClass().getSimpleName()
                    + " cannot have default options of type " + defaultOptions.getClass().getSimpleName() + ".");

        if (!cycleReceiver.isOptionsForThisCycleReceiver(cycleTimestampOptions))
            throw new IllegalArgumentException("cycle receiver " + cycleReceiver.getClass().getSimpleName()
                    + " cannot have cycle timestamp options of type " + cycleTimestampOptions.getClass().getSimpleName()
                    + ".");

        cycleReceiverPairs.add(
                new CycleReceiverWithOptions(cycleReceiver, defaultOptions, cycleTimestampOptions));
    }

    private static class CycleReceiverWithOptions {
        private final CycleReceiver cycleReceiver;
        private final CycleReceiverOptions defaultOptions;
        private final CycleReceiverOptions cycleTimestampOptions;

        public CycleReceiverWithOptions(
                CycleReceiver cycleReceiver,
                CycleReceiverOptions defaultOptions,
                CycleReceiverOptions cycleTimestampOptions) {
            this.cycleReceiver = cycleReceiver;
            this.defaultOptions = defaultOptions;
            this.cycleTimestampOptions = cycleTimestampOptions;
        }

        public CycleReceiver getCycleReceiver() {
            return cycleReceiver;
        }

        public CycleReceiverOptions getDefaultOptions() {
            return defaultOptions;
        }

        public CycleReceiverOptions getCycleTimestampOptions() {
            return cycleTimestampOptions;
        }
    }
}
