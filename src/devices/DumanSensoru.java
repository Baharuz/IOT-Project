package devices;

import abstract_classes.Sensor;
import exceptions.SensorArizasiException;

public class DumanSensoru extends Sensor {
    public DumanSensoru(String konum) {
        super(konum);
        this.currentValue = 0.0;
    }
    
    public DumanSensoru(String konum, String marka, String model) {
        super(konum, marka, model);
        this.currentValue = 0.0;
    }
    
    @Override
    public double readValue() throws SensorArizasiException {
        currentValue = Math.random() < 0.05 ? 1.0 : 0.0;
        
        if (currentValue < 0) {
            throw new SensorArizasiException("Duman sensörü arızalı: " + id);
        }
        
        return currentValue;
    }
    
    @Override
    public void calistir() {
        System.out.println(getClass().getSimpleName() + " çalışıyor. Duman seviyesi: " + currentValue);
        startReading();
    }
}
