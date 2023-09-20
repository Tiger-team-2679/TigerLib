package frc.lib.logging.api;

import edu.wpi.first.hal.DriverStationJNI;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.NotifierJNI;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Watchdog;
import frc.lib.logging.Logger;

import java.util.ConcurrentModificationException;

public abstract class LoggedRobot extends RobotBase {
    private final long periodMicroseconds;
    private long nextCycleMicroseconds = 0;
    private final Watchdog watchdog;
    private final int notifierId = NotifierJNI.initializeNotifier();
    private boolean isNT4FlushEnabled = true;
    private boolean isLiveWindowEnabledInTest = false;

    protected LoggedRobot() {
        this(0.02);
    }

    protected LoggedRobot(double periodSeconds) {
        this.periodMicroseconds = (long) (periodSeconds * 1e6);
        watchdog = new Watchdog(periodSeconds, this::printLoopOverrunMessage);

        NotifierJNI.setNotifierName(notifierId, "LoggedRobot");
        HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_Timed);
    }

    @Override
    public void close() {
        NotifierJNI.stopNotifier(notifierId);
        NotifierJNI.cleanNotifier(notifierId);
    }

    @Override
    public void startCompetition() {
        robotInit();

        if (isSimulation()) {
            simulationInit();
        }

        // Tell the DS that the robot is ready to be enabled
        System.out.println("********** Robot program startup complete **********");
        DriverStationJNI.observeUserProgramStarting();

        while (true) {
            NotifierJNI.updateNotifierAlarm(notifierId, nextCycleMicroseconds);
            NotifierJNI.waitForNotifierAlarm(notifierId);

            Logger.beforePeriodic();
            loopFunc();
            Logger.afterPeriodic();

            nextCycleMicroseconds += periodMicroseconds;
        }
    }

    public abstract void robotInit();

    public abstract void simulationInit();

    public abstract void disabledInit();

    public abstract void autonomousInit();

    public abstract void teleopInit();

    public abstract void testInit();

    public abstract void robotPeriodic();

    public abstract void simulationPeriodic();

    public abstract void disabledPeriodic();

    public abstract void autonomousPeriodic();

    public abstract void teleopPeriodic();

    public abstract void testPeriodic();

    public abstract void disabledExit();

    public abstract void autonomousExit();

    public abstract void teleopExit();

    public abstract void testExit();

    protected void loopFunc() {
        DriverStation.refreshData();
        watchdog.reset();

        runUserModeSpecificFunctions();

        watchdog.disable();

        // Flush NetworkTables
        if (isNT4FlushEnabled) {
            NetworkTableInstance.getDefault().flushLocal();
        }

        // Warn on loop time overruns
        if (watchdog.isExpired()) {
            watchdog.printEpochs();
        }
    }

    public void runUserModeSpecificFunctions() {
        
    }

    public void setNetworkTablesFlushEnabled(boolean enabled) {
        isNT4FlushEnabled = enabled;
    }

    public void setIfLiveWindowEnabledInTest(boolean testLW) {
        if (isTestEnabled())
            throw new ConcurrentModificationException("Can't configure test mode while in test mode!");
        isLiveWindowEnabledInTest = testLW;
    }

    public boolean getIfLiveWindowIsEnabledInTest() {
        return isLiveWindowEnabledInTest;
    }

    private void printLoopOverrunMessage() {
        DriverStation.reportWarning("Loop time of " + periodMicroseconds + "s overrun\n", false);
    }
}
