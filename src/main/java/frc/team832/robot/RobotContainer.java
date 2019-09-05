package frc.team832.robot;

import frc.team832.lib.driverstation.controllers.SWPrecisionPro;
import frc.team832.robot.subsystems.TestSubsystem;

public class RobotContainer {
    public static SWPrecisionPro stick = new SWPrecisionPro(0);

    public static TestSubsystem mTestSubsystem;

    public static boolean init() {
        mTestSubsystem = new TestSubsystem();
        return true;
    }
}