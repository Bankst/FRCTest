package frc.team832.robot;

import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.team832.lib.driverstation.controllers.Xbox360Controller;
import frc.team832.robot.subsystems.VisionSubsystem;

public class RobotContainer {
    private RobotContainer() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final Xbox360Controller xb360 = new Xbox360Controller(0);

    public static final VisionSubsystem visionSubsystem = new VisionSubsystem();

    private static boolean initStatus = true;

    public static boolean init() {
        Shuffleboard.addEventMarker("CONTAINER", "INIT - BEGIN", EventImportance.kLow);

        initStatus = visionSubsystem.init();

        Shuffleboard.addEventMarker("CONTAINER", "INIT - COMPLETE", EventImportance.kLow);
        return true;
    }
}