package frc.robot.subsystems.examplesubsystem.io;

import java.util.concurrent.ThreadLocalRandom;

import frc.lib.logging.api.fields.FieldsTable;

public class ExampleSubsystemIORandom extends ExampleSubsystemIO {
    public ExampleSubsystemIORandom(FieldsTable fieldsTable) {
        super(fieldsTable);
    }

    @Override
    protected long getSomeValue() {
        return ThreadLocalRandom.current().nextLong(100);
    }
    
}
