package frc.lib.logging.api;

import frc.lib.logging.api.fields.FieldsTable;

public abstract class IOBase {
    protected final FieldsTable fields;

    protected IOBase(String name){
        fields = new FieldsTable(name);
    }

    public FieldsTable getFieldsTable() {
        return fields;
    }
}
