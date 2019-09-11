package frc.team832.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team832.lib.power.PowerConsumerBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestSubsystem extends SubsystemBase {
    public TestSubsystem() {
        setName("Test Subsystem");
        register();
        SmartDashboard.putData(this.m_name, this);  
    }

    private PowerConsumerBase consumer = new PowerConsumerBase(){
    
        @Override
        public void uncapConsumption() {
            
        }
    
        @Override
        public double getConsumerVoltage() {
            return 0;
        }
    
        @Override
        public double getConsumerCurrent() {
            return 0;
        }
    
        @Override
        public void capConsumption(int powerCap) {
            
        }
    };
}