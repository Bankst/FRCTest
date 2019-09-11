package frc.team832.lib.power;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSRXConsumer implements PowerConsumerBase {

    private final TalonSRX _talon;

    public TalonSRXConsumer(int id) {
        _talon = new TalonSRX(id);
    }

    @Override
    public double getConsumerVoltage() {
        return _talon.getBusVoltage();
    }

    @Override
    public double getConsumerCurrent() {
        return _talon.getOutputCurrent();
    }

    @Override
    public void capConsumption(int powerCap) {
        powerCapMap.put(this, powerCap);
        _talon.configContinuousCurrentLimit(powerCap);
    }

    @Override
    public void uncapConsumption() {
        capConsumption(0);
    }
}