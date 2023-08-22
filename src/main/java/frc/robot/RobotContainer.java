// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.concurrent.ThreadLocalRandom;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.lib.logging.fields.types.IntegerField;

public class RobotContainer {
  IntegerField randomNumber = new IntegerField("randomNum", () -> ThreadLocalRandom.current().nextLong(100));

  public RobotContainer() {
    configureBindings();
  }

  public void periodic() {
    if(randomNumber.get() % 30 == 3)
      System.out.println(randomNumber.get());
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
