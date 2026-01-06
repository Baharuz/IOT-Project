package abstract_classes;
import java.util.UUID;
import interfaces.IDevice;

public abstract class AbstractDevice implements IDevice {
    protected String id;
    protected String marka;
    protected String model;
    protected String konum;
    protected boolean aktif;
    protected Baglanti baglanti;
  
    protected class Baglanti {
        private String ipAdresi;
        private int port;
        private boolean bagli;
        
        public Baglanti(String ipAdresi, int port) {
            this.ipAdresi = ipAdresi;
            this.port = port;
            this.bagli = false;
        }
        
        public void baglan() {
            this.bagli = true;
            System.out.println(id + " bağlandı: " + ipAdresi + ":" + port);
        }
        
        public void kopar() {
            this.bagli = false;
            System.out.println(id + " bağlantısı kesildi.");
        }
        
        public String getBaglantiDurumu() {
            return bagli ? "Bağlı" : "Bağlı Değil";
        }
        
        public String getIpAdresi() {
            return ipAdresi;
        }
        
        public int getPort() {
            return port;
        }
    }
    
    public AbstractDevice(String konum) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.konum = konum;
        this.aktif = false;
        this.baglanti = new Baglanti("192.168.1." + (int)(Math.random() * 255), 8080);
    }
    
    public AbstractDevice(String konum, String marka, String model) {
        this(konum);
        this.marka = marka;
        this.model = model;
    }
    
    @Override
    public void turnOn() {
        aktif = true;
        baglanti.baglan();
        System.out.println(getClass().getSimpleName() + " (" + id + ") açıldı.");
    }
    
    @Override
    public void turnOff() {
        aktif = false;
        baglanti.kopar();
        System.out.println(getClass().getSimpleName() + " (" + id + ") kapatıldı.");
    }
    
    @Override
    public String getStatus() {
        return String.format("%s [%s] - Durum: %s, Konum: %s, Bağlantı: %s", 
            getClass().getSimpleName(), id, aktif ? "Aktif" : "Pasif", 
            konum, baglanti.getBaglantiDurumu());
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getMarka() {
        return marka;
    }
    
    public void setMarka(String marka) {
        this.marka = marka;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getKonum() {
        return konum;
    }
    
    public void setKonum(String konum) {
        this.konum = konum;
    }
    
    public boolean isAktif() {
        return aktif;
    }
    
    public void cihazBilgisiGoster() {
        System.out.println("═══════════════════════════════════");
        System.out.println("Cihaz Bilgileri:");
        System.out.println("ID: " + id);
        System.out.println("Marka: " + (marka != null ? marka : "Bilinmiyor"));
        System.out.println("Model: " + (model != null ? model : "Bilinmiyor"));
        System.out.println("Konum: " + konum);
        System.out.println("Durum: " + (aktif ? "Aktif" : "Pasif"));
        System.out.println("IP: " + baglanti.getIpAdresi());
        System.out.println("Port: " + baglanti.getPort());
        System.out.println("═══════════════════════════════════");
    }
    
    @Override
    public abstract void calistir();
}
