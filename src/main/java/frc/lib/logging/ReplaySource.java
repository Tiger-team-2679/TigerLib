package frc.lib.logging;

import java.util.function.Function;

public interface ReplaySource {
    public boolean updateTableToNextCycle(
            LogTable table,
            Function<String, CycleReceiverOptions[]> cycleReceiversOptionsGetter);
}
