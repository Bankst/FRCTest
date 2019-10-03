package frc.team832.lib.driverstation.dashboard;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
public class ShuffleboardManager {

	private static final String WIDGET_TEXTBOX = "Text Box";
	private static final String WIDGET_BOOLEAN = "Boolean Box";

	private ShuffleboardManager() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	private static HashMap<DashboardUpdatable, ShuffleboardTab> shuffleboardTabs = new HashMap<>();

	public static void addTab(DashboardUpdatable updatable) {
		if (!shuffleboardTabs.containsKey(updatable)) {
			shuffleboardTabs.put(updatable, Shuffleboard.getTab(updatable.getShuffleboardTabName()));
		}
	}

	public static NetworkTableEntry addTabItem(DashboardUpdatable updatable, String itemName, Object defaultValue, ShuffleboardWidget widget) {
		if (!shuffleboardTabs.containsKey(updatable)) return null;
		return shuffleboardTabs.get(updatable).add(itemName, defaultValue).withWidget(widget.name).getEntry();
	}

	public static NetworkTableEntry addTabItem(DashboardUpdatable updatable, String itemName, Object defaultValue) {
		return addTabItem(updatable, itemName, defaultValue, ShuffleboardWidget.TextView);
	}

	public static void updateAllTabs() {
		shuffleboardTabs.forEach((key, value) -> key.updateShuffleboardData());
	}
}
