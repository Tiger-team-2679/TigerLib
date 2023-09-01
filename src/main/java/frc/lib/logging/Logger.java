package frc.lib.logging;

import java.io.IOException;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.math.MathShared;
import edu.wpi.first.math.MathSharedStore;
import edu.wpi.first.math.MathUsageId;
import edu.wpi.first.wpilibj.DriverStation;
import frc.lib.logging.logvalues.LogValue;
import frc.lib.logging.wpilog.WPILOGReader;

public class Logger {
    private static ReplaySource replaySource = null;
    private static final LogTable logTable = new LogTable();
    private static final CycleReceiversManager cycleReceiversManager = new CycleReceiversManager();

    private Logger() {
    }

    public static void setReplayLog(String filename) {
        try {
            setReplayLog(new WPILOGReader(filename));
        } catch (IOException e) {
            DriverStation.reportError("Failed to open log file. (" + e.getMessage() + ")", e.getStackTrace());
        }
    }

    public static void setReplayLog(ReplaySource replaySource) {
        Logger.replaySource = replaySource;
    }

    public static void addCycleReceiver(CycleReceiver dataReceiver, CycleReceiverOptions defaultOptions) {
        cycleReceiversManager.registerCycleReceiver(dataReceiver, defaultOptions, defaultOptions);
    }

    public static void addCycleReceiver(CycleReceiver dataReceiver, CycleReceiverOptions defaultOptions,
            CycleReceiverOptions cycleTimestampOptions) {
        cycleReceiversManager.registerCycleReceiver(dataReceiver, defaultOptions, cycleTimestampOptions);
    }

    public static void onStart(){
        setMathShared();
    }

    public static void beforePeriodic() {
        if (replaySource != null) {
            boolean isSuccess = replaySource.updateTableToNextCycle(
                    logTable,
                    RealFieldsManager::getFieldCycleReceiversOptions);
            if (!isSuccess)
                System.exit(0);
        } else {
            RealFieldsManager.updateTableToNextCycle(logTable);
        }

        RealFieldsManager.updateFieldsFromTable(logTable);
    }

    public static void afterPeriodic() {
        cycleReceiversManager.putTable(logTable.clone());
    }

    public static long getTimestamp() {
        return logTable.getTimestamp();
    }

    public static void recordValue(String key, LogValue value) {
        String prefix = replaySource == null ? "realOutputs/" : "replayOutputs/";
        logTable.put(prefix + key, value);
    }

    /**
     * Updates the MathShared object for wpimath to the Logger timestamps.
     */
    private static void setMathShared() {
        MathSharedStore.setMathShared(
                new MathShared() {
                    @Override
                    public void reportError(String error, StackTraceElement[] stackTrace) {
                        DriverStation.reportError(error, stackTrace);
                    }

                    @Override
                    public void reportUsage(MathUsageId id, int count) {
                        switch (id) {
                            case kKinematics_DifferentialDrive:
                                HAL.report(
                                        tResourceType.kResourceType_Kinematics,
                                        tInstances.kKinematics_DifferentialDrive);
                                break;
                            case kKinematics_MecanumDrive:
                                HAL.report(
                                        tResourceType.kResourceType_Kinematics, tInstances.kKinematics_MecanumDrive);
                                break;
                            case kKinematics_SwerveDrive:
                                HAL.report(
                                        tResourceType.kResourceType_Kinematics, tInstances.kKinematics_SwerveDrive);
                                break;
                            case kTrajectory_TrapezoidProfile:
                                HAL.report(tResourceType.kResourceType_TrapezoidProfile, count);
                                break;
                            case kFilter_Linear:
                                HAL.report(tResourceType.kResourceType_LinearFilter, count);
                                break;
                            case kOdometry_DifferentialDrive:
                                HAL.report(
                                        tResourceType.kResourceType_Odometry, tInstances.kOdometry_DifferentialDrive);
                                break;
                            case kOdometry_SwerveDrive:
                                HAL.report(tResourceType.kResourceType_Odometry, tInstances.kOdometry_SwerveDrive);
                                break;
                            case kOdometry_MecanumDrive:
                                HAL.report(tResourceType.kResourceType_Odometry, tInstances.kOdometry_MecanumDrive);
                                break;
                            case kController_PIDController2:
                                HAL.report(tResourceType.kResourceType_PIDController2, count);
                                break;
                            case kController_ProfiledPIDController:
                                HAL.report(tResourceType.kResourceType_ProfiledPIDController, count);
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public double getTimestamp() {
                        return Logger.getTimestamp();
                    }
                });
    }
}
