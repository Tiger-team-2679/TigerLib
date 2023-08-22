package frc.robot.subsystems.examplesubsystem.io;

import java.util.concurrent.ThreadLocalRandom;

public class ExampleSubsystemIORandom extends ExampleSubsystemIO {
    @Override
    protected long getSomeValue() {
        return ThreadLocalRandom.current().nextLong(100);
    }
    
}
