package frc.robot.subsystems.examplesubsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.examplesubsystem.io.ExampleSubsystemIO;
import frc.robot.subsystems.examplesubsystem.io.ExampleSubsystemIOCounter;

public class ExampleSubsystem extends SubsystemBase {
    private final ExampleSubsystemIO io = new ExampleSubsystemIOCounter();

    public ExampleSubsystem() {
    }

    @Override
    public void periodic() {
        System.out.println(io.someValue.getAsLong());
    }
}
