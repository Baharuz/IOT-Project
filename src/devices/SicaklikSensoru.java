package devices;

import abstract_classes.Sensor;
import exceptions.SensorArizasiException;

public class SicaklikSensoru extends Sensor {
    private static final double MIN_SICAKLIK = -50.0;
    private static final double MAX_SICAKLIK = 60.0;
    
    public SicaklikSensoru(String konum) {
        super(konum);
        this.currentValue = 20.0 + (Math.random() * 10);
    }
    
    public SicaklikSensoru(String konum, double baslangicSicaklik) {
        super(konum, baslangicSicaklik);
    }
    
    public SicaklikSensoru(String konum, String marka, String model) {
        super(konum, marka, model);
        this.currentValue = 20.0;
    }
    
    @Override
    public double readValue() throws SensorArizasiException {
        currentValue += (Math.random() - 0.5) * 2;
        
        if (currentValue < MIN_SICAKLIK || currentValue > MAX_SICAKLIK) {
            throw new SensorArizasiException(
                String.format("Anormal sıcaklık değeri: %.2f°C - Sensör ID: %s", currentValue, id)
            );
        }
        
        return currentValue;
    }
    
    @Override
    public void calistir() {
        System.out.println(getClass().getSimpleName() + " çalışıyor. Mevcut sıcaklık: " + 
                          String.format("%.2f°C", currentValue));
        startReading();
    }
}
