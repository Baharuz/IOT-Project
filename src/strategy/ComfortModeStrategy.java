package strategy;

import interfaces.IEnergyStrategy;

public class ComfortModeStrategy implements IEnergyStrategy {
    @Override
    public boolean shouldActivateAC(double temperature) {
        return temperature > 24.0;
    }
    
    @Override
    public boolean shouldActivateHeater(double temperature) {
        return temperature < 20.0;
    }
    
    @Override
    public String getStrategyName() {
        return "Comfort Mode (Konfor)";
    }
}