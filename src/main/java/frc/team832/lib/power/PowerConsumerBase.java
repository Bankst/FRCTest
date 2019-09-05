package frc.team832.lib.power;

public abstract class PowerConsumerBase {

    private PowerPriority mPriority;
    private int mPowerCap;

    public PowerConsumerBase() {
        mPriority = PowerPriority.LOW;
    }

    public void setPriority(PowerPriority priority) { mPriority = priority; }

    public PowerPriority getPriority() { return mPriority; }

    public double getPowerConsumption() { return getConsumerVoltage() * getConsumerCurrent(); }

    public abstract double getConsumerVoltage();
    public abstract double getConsumerCurrent();

    protected void setMaxConsumption(int powerCap) {
        mPowerCap = powerCap;
        capConsumption(powerCap);
    }

    protected void unsetMaxConsumption() {
        mPowerCap = 0;
        uncapConsumption();
    }

    public abstract void capConsumption(int powerCap);
    public abstract void uncapConsumption();

    public int getPowerCap() { return mPowerCap; }
}