package interfaces;

public interface IEnergyStrategy {
	boolean shouldActivateAC(double temperature);
    boolean shouldActivateHeater(double temperature);
    String getStrategyName();

}
