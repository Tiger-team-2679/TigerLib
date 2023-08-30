package frc.robot.subsystems.examplesubsystem.io;

import java.util.function.LongSupplier;

import frc.lib.logging.api.IOBase;
import frc.lib.logging.api.fields.FieldsTable;

public abstract class ExampleSubsystemIO extends IOBase {
    public final LongSupplier someValue = fields.addInteger("someValue", this::getSomeValue);

    protected ExampleSubsystemIO(FieldsTable fieldsTable) {
        super(fieldsTable);
    }

    protected abstract long getSomeValue();
}
