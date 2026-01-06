package strategy;

import interfaces.IEnergyStrategy;

public class EcoModeStrategy implements IEnergyStrategy {
    @Override
    public boolean shouldActivateAC(double temperature) {
        return temperature > 30.0;
    }
    
    @Override
    public boolean shouldActivateHeater(double temperature) {
        return temperature < 15.0;
    }
    
    @Override
    public String getStrategyName() {
        return "Eco Mode (Ekonomik)";
    }
}
