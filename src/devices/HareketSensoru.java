package devices;
import abstract_classes.Sensor; 
import exceptions.PilBittiException;
import interfaces.ISarjOlabilir;
import abstract_classes.AbstractDevice;
import interfaces.ISensor;     
import exceptions.SensorArizasiException;

public class HareketSensoru extends Sensor implements ISarjOlabilir {
    private int pilSeviyesi;
    private double currentValue;
    
    public HareketSensoru(String konum) {
        super(konum);
        this.pilSeviyesi = 100;
        this.currentValue = 0.0;
    }
    
    public HareketSensoru(String konum, String marka, String model) {
        super(konum, marka, model);
        this.pilSeviyesi = 100;
        this.currentValue = 0.0;
    }
    
    @Override
    public double readValue() throws SensorArizasiException {
        try {
            pilTuket(2);
        } catch (PilBittiException e) {
            throw new SensorArizasiException("Hareket sensörü pil bitti: " + id, e);
        }
        
        currentValue = Math.random() < 0.2 ? 1.0 : 0.0;
        return currentValue;
    }
    
    @Override
    public void calistir() {
        System.out.println(getClass().getSimpleName() + " çalışıyor. Pil: %" + pilSeviyesi);
        startReading();
    }
    
    @Override
    public void sarjEt() {
        pilSeviyesi = 100;
        System.out.println("Hareket sensörü şarj edildi: " + id);
    }
    
    @Override
    public int pilDurumuGoster() {
        return pilSeviyesi;
    }
    
    @Override
    public void pilTuket(int miktar) throws PilBittiException {
        this.pilSeviyesi -= miktar;
        if (this.pilSeviyesi <= 0) {
            this.pilSeviyesi = 0;
            throw new PilBittiException("Hareket sensörü pili bitti: " + id);
        }
    }
}
