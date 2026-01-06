package abstract_classes;
import interfaces.ISensor;
import interfaces.IObserver;
import exceptions.SensorArizasiException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Sensor extends AbstractDevice implements ISensor, Runnable {
    
    protected double currentValue;
    protected List<IObserver> observers;
    protected Thread sensorThread;
    protected volatile boolean running; 
    
    protected List<String> veriGecmisi;
    public Sensor(String konum) {
        super(konum);
        this.observers = new ArrayList<>();
        this.running = false;
        this.veriGecmisi = new ArrayList<>();
    }
    
    public Sensor(String konum, double baslangicDegeri) {
        this(konum);
        this.currentValue = baslangicDegeri;
    }
    
    public Sensor(String konum, String marka, String model) {
        super(konum, marka, model);
        this.observers = new ArrayList<>();
        this.running = false;
        this.veriGecmisi = new ArrayList<>();
    }
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }
    
    protected void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(id, currentValue, konum);
        }
    }
    
    @Override 
    public void veriKaydet(double deger) {
        String kayit = String.format("Değer: %.2f", deger);
        veriGecmisi.add(kayit);
    }
    
    @Override
    public void veriKaydet(double deger, String zaman) {
        String kayit = String.format("Değer: %.2f, Zaman: %s", deger, zaman);
        veriGecmisi.add(kayit);
    }
    
    public void veriGecmisiGoster() {
        System.out.println("--- Veri Geçmişi: " + id + " ---");
        for (String kayit : veriGecmisi) {
            System.out.println(kayit);
        }
    }
   
    protected void startReading() {
        if (!running) {
            running = true;
            sensorThread = new Thread(this); 
            sensorThread.start();
        }
    }
    
    public void stopReading() {
        running = false;
        if (sensorThread != null) {
            try {
                sensorThread.join(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void run() {
        while (running && aktif) {
            try {
                double value = readValue();
                
                String zaman = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                veriKaydet(value, zaman);
                
                notifyObservers();
                Thread.sleep(2000); 
                
            } catch (SensorArizasiException e) {
                System.err.println("Sensör Arızası (" + id + "): " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("Sensör durduruldu: " + id);
                break;
            }
        }
    }
    
    public double getCurrentValue() {
        return currentValue;
    }
}