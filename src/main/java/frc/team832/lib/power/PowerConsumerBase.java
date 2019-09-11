package frc.team832.lib.power;

import java.util.IdentityHashMap;

public interface PowerConsumerBase {

    public static IdentityHashMap<Object, PowerPriority> priorityMap = new IdentityHashMap<Object, PowerPriority>();
    public static IdentityHashMap<Object, Integer> powerCapMap = new IdentityHashMap<Object, Integer>();

    default public void setPriority(PowerPriority priority) { 
        priorityMap.put(this, PowerPriority.LOW);
    }

    default public PowerPriority getPriority() { return priorityMap.getOrDefault(this, PowerPriority.LOW); }

    default public double getPowerConsumption() { return getConsumerVoltage() * getConsumerCurrent(); }

    public abstract double getConsumerVoltage();
    public abstract double getConsumerCurrent();

    public void capConsumption(int powerCap);
    public abstract void uncapConsumption();

    default public int getPowerCap() { return powerCapMap.getOrDefault(this, 0); }
}