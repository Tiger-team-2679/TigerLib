package frc.robot.subsystems.examplesubsystem.io;

import frc.lib.logging.api.fields.FieldsTable;

public class ExampleSubsystemIOCounter extends ExampleSubsystemIO {
    private int counter = 0;

    public ExampleSubsystemIOCounter(FieldsTable fieldsTable) {
        super(fieldsTable);
    }

    @Override
    protected long getSomeValue() {
        return ++counter;
    }   
}
