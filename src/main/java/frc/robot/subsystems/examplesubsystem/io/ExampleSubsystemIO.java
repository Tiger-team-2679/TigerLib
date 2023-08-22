package frc.robot.subsystems.examplesubsystem.io;

import java.util.function.LongSupplier;

import frc.lib.logging.api.IOBase;

public abstract class ExampleSubsystemIO extends IOBase {
    public final LongSupplier someValue = fields.addInteger("someValue", this::getSomeValue);

    protected ExampleSubsystemIO() {
        super("exampleSubsystem");
    }

    protected abstract long getSomeValue();
}
