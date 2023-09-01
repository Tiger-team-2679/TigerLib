package frc.lib.logging;

import java.util.function.BooleanSupplier;

public abstract class CycleReceiverOptions {
    private final BooleanSupplier enabledSupplier;
    
    public CycleReceiverOptions(BooleanSupplier enableSupplier) {
        this.enabledSupplier = enableSupplier;
    }

    public final boolean getIfEnabled() {
        return enabledSupplier.getAsBoolean();
    }
}
