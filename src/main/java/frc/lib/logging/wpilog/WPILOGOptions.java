package frc.lib.logging.wpilog;

import java.util.function.BooleanSupplier;

import frc.lib.logging.CycleReceiverOptions;

public class WPILOGOptions extends CycleReceiverOptions {
    public WPILOGOptions(BooleanSupplier enableSupplier) {
        super(WPILOGWriter.key, enableSupplier);
    }

    public WPILOGOptions(boolean enabled) {
        this(() -> enabled);
    }
}
