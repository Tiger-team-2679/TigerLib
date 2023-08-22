package frc.robot.subsystems.examplesubsystem.io;

public class ExampleSubsystemIOCounter extends ExampleSubsystemIO {
    private int counter = 0;
    @Override
    protected long getSomeValue() {
        return ++counter;
    }
}
