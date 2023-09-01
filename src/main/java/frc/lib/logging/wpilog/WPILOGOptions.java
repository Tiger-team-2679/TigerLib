package frc.lib.logging.wpilog;

import java.util.function.BooleanSupplier;

import frc.lib.logging.CycleReceiverOptions;

public class WPILOGOptions extends CycleReceiverOptions {
    public final static WPILOGOptions ON = new WPILOGOptions(() -> true);
    public final static WPILOGOptions OFF = new WPILOGOptions(() -> false);

    public WPILOGOptions(BooleanSupplier enableSupplier) {
        super(enableSupplier);
    }
}
