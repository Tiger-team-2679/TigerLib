package frc.robot.subsystems.examplesubsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.logging.api.fields.FieldsTable;
import frc.lib.logging.networktables.NT4Options;
import frc.robot.subsystems.examplesubsystem.io.ExampleSubsystemIO;
import frc.robot.subsystems.examplesubsystem.io.ExampleSubsystemIOCounter;

public class ExampleSubsystem extends SubsystemBase {
    public final FieldsTable fieldsTable = new FieldsTable("ExampleSubsystem");
    private final ExampleSubsystemIO io = new ExampleSubsystemIOCounter(fieldsTable);

    public ExampleSubsystem() {
    }

    @Override
    public void periodic() {
        System.out.println(io.someValue.getAsLong());

        fieldsTable.recordValue("multiplied", io.someValue.getAsLong() * 2, new NT4Options(true));
    }
}
