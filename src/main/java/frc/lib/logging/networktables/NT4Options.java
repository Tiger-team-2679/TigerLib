package frc.lib.logging.networktables;

import java.util.function.BooleanSupplier;

import frc.lib.logging.CycleReceiverOptions;

public class NT4Options extends CycleReceiverOptions {
    public final static NT4Options ON = new NT4Options(() -> true);
    public final static NT4Options OFF = new NT4Options(() -> false);

    public NT4Options(BooleanSupplier enableSupplier) {
        super(enableSupplier);
    }
}
