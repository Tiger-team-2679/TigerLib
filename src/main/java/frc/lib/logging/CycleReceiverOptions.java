package frc.lib.logging;

import java.util.function.BooleanSupplier;

public abstract class CycleReceiverOptions {
    private final String key;
    private final BooleanSupplier enabledSupplier;
    
    public CycleReceiverOptions(String key, BooleanSupplier enableSupplier) {
        this.key = key;
        this.enabledSupplier = enableSupplier;
    }

    public String getKey() {
        return key;
    }

    public boolean getIfEnabled() {
        return enabledSupplier.getAsBoolean();
    }
}
