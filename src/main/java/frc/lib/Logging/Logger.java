package frc.lib.Logging;

import edu.wpi.first.wpilibj.RobotController;
import frc.lib.Logging.wpilog.WPILOGWriter;

public class Logger {
    private WPILOGWriter wpilogWriter = new WPILOGWriter("C:\\TigerTeam");
    private long counter = 0;

    public void periodic() {
        LogTable table = new LogTable();
        table.put("hey", ++counter);
        table.put("boool", counter % 2 == 0);
        wpilogWriter.writeTable(table, RobotController.getFPGATime());
    }
}
