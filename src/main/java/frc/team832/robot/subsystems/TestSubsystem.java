package frc.team832.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestSubsystem extends SubsystemBase {
    public TestSubsystem() {
        setName("Test Subsystem");
        register();
        SmartDashboard.putData(this.m_name, this);  
    }
}