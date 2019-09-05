package frc.team832.lib.power;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public abstract class SimplePowerConsumer extends PowerConsumerBase {

    private static PowerDistributionPanel mPDP = new PowerDistributionPanel();
    private final int mPDPChannel;
    
    public SimplePowerConsumer(int pdpChannel) {
        mPDPChannel = pdpChannel;
    }

    @Override
    public double getConsumerVoltage() {
        return mPDP.getVoltage();
    }

    @Override
    public double getConsumerCurrent() {
        return mPDP.getCurrent(mPDPChannel);
    }
}