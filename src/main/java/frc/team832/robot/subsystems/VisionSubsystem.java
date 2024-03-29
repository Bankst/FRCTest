package frc.team832.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team832.lib.driverstation.dashboard.DashboardManager;
import frc.team832.lib.driverstation.dashboard.DashboardUpdatable;
import frc.team832.lib.driverstation.dashboard.DashboardWidget;
import frc.team832.lib.util.OscarMath;
import frc.team832.lib.util.RollingAverage;

public class VisionSubsystem extends SubsystemBase implements DashboardUpdatable {

	public VisionSubsystem() {
		super();
		setName("Vision Subsystem");
		DashboardManager.addTab(this);
	}

	private final String PS3EyeCamName = "USB Camera-B4.04.27.1";
	private final String defaultCameraTableName = "chameleon-vision/" + PS3EyeCamName;
	private final String defaultCameraPublisherTableName = "CameraPublisher/" + PS3EyeCamName;
	private final NetworkTable chameleonVisionTable = NetworkTableInstance.getDefault().getTable(defaultCameraTableName);
	private final NetworkTable cameraPublisherTable = NetworkTableInstance.getDefault().getTable(defaultCameraPublisherTableName);
	private final NetworkTableEntry chamVis_pitchEntry = chameleonVisionTable.getEntry("pitch"); // double
	private final NetworkTableEntry chamVis_yawEntry = chameleonVisionTable.getEntry("yaw"); // double
	private final NetworkTableEntry chamVis_distanceEntry = chameleonVisionTable.getEntry("distance"); // double
	private final NetworkTableEntry chamVis_timestampEntry = chameleonVisionTable.getEntry("timestamp"); // long
	private final NetworkTableEntry chamVis_isValidEntry = chameleonVisionTable.getEntry("is_valid"); // boolean
	private final NetworkTableEntry chamVis_driverModeEntry = chameleonVisionTable.getEntry("driver_mode"); // boolean (settable)
	private final NetworkTableEntry chamVis_camStreamsEntry = cameraPublisherTable.getEntry("streams"); // String[]

	private NetworkTableEntry dashboard_pitchEntry;
	private NetworkTableEntry dashboard_yawEntry;
	private NetworkTableEntry dashboard_distanceEntry;
	private NetworkTableEntry dashboard_isValidEntry;
	private NetworkTableEntry dashboard_driverModeEntry;
	private NetworkTableEntry dashboard_processTimeEntry;

	private RollingAverage processTimeAvg = new RollingAverage(50);

	public boolean init() {
		dashboard_pitchEntry = DashboardManager.addTabItem(this, "CV Pitch", -1.0);
		dashboard_yawEntry = DashboardManager.addTabItem(this, "CV Yaw", -1.0);
		dashboard_distanceEntry = DashboardManager.addTabItem(this, "CV Distance", -1.0);
		dashboard_isValidEntry = DashboardManager.addTabItem(this, "CV IsValid", false);
		dashboard_driverModeEntry = DashboardManager.addTabItem(this, "CV DriverMode", false, DashboardWidget.ToggleButton);
		dashboard_processTimeEntry = DashboardManager.addTabItem(this, "CV Process Time", "0ms");
		return true;
	}

	@Override
	public String getDashboardTabName() {
		return m_name;
	}

	@Override
	public void updateDashboardData() {
		dashboard_pitchEntry.setDouble(OscarMath.round(chamVis_pitchEntry.getDouble(-1.0), 2));
		dashboard_yawEntry.setDouble(OscarMath.round(chamVis_yawEntry.getDouble(-1.0), 2));
		dashboard_distanceEntry.setDouble(OscarMath.round(chamVis_distanceEntry.getDouble(-1.0), 2));
		dashboard_isValidEntry.setBoolean(chamVis_isValidEntry.getBoolean(false));

		var frameTimestamp = chamVis_timestampEntry.getValue().getTime();
		var rioTimestamp = RobotController.getFPGATime();
		var diff = (rioTimestamp - frameTimestamp) / 1000.0;

		processTimeAvg.add(diff);
		dashboard_processTimeEntry.setString(OscarMath.round(processTimeAvg.getAverage(), 2) + "ms");

		var currentDriverModeStatus = chamVis_driverModeEntry.getBoolean(false);
		var newDriverModeStatus = dashboard_driverModeEntry.getBoolean(false);
		if (newDriverModeStatus != currentDriverModeStatus) {
			chamVis_driverModeEntry.setBoolean(newDriverModeStatus);
		}
	}
}
