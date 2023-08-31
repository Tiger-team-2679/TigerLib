package frc.lib.logging.networktables;

import java.util.function.BooleanSupplier;

import frc.lib.logging.CycleReceiverOptions;

public class NT4Options extends CycleReceiverOptions {
    public NT4Options(BooleanSupplier enableSupplier) {
        super(enableSupplier);
    }

    public NT4Options(boolean enabled) {
        this(() -> enabled);
    }
}
