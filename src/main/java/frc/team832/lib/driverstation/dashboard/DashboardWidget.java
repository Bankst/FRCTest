package frc.team832.lib.driverstation.dashboard;

public enum ShuffleboardWidget {
	TextView("Text View"),
	BooleanBox("Boolean Box"),
	ToggleButton("Toggle Button"),
	ToggleSwitch("Toggle Switch");

	public final String name;

	ShuffleboardWidget(String name) {
		this.name = name;
	}
}
