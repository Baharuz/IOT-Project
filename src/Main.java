import hub.SmartHomeHub;
import interfaces.IDevice;
import factory.*;
import strategy.*;
import devices.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║     AKILLI EV IoT HUB SİMÜLASYONU VE OTOMASYON SİSTEMİ       ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        
        SmartHomeHub hub = new SmartHomeHub("MAIN-HUB-001");
        System.out.println("=== CIHAZLAR OLUŞTURULUYOR (Factory Pattern) ===\n");
        
        IDevice sicaklikSensor1 = DeviceFactory.createDevice(
            DeviceType.TEMPERATURE_SENSOR, "Salon", "Samsung", "TempPro-X1"
        );
        IDevice sicaklikSensor2 = DeviceFactory.createDevice(
            DeviceType.TEMPERATURE_SENSOR, "Yatak Odası"
        );
        IDevice hareketSensor = DeviceFactory.createDevice(
            DeviceType.MOTION_SENSOR, "Salon", "Xiaomi", "Motion-S2"
        );
        IDevice dumanSensor = DeviceFactory.createDevice(
            DeviceType.SMOKE_SENSOR, "Mutfak", "Nest", "Protect-V3"
        );
        IDevice lamba1 = DeviceFactory.createDevice(
            DeviceType.SMART_LAMP, "Salon", "Philips", "Hue-White"
        );
        IDevice lamba2 = DeviceFactory.createDevice(
            DeviceType.SMART_LAMP, "Yatak Odası"
        );
        IDevice klima = DeviceFactory.createDevice(
            DeviceType.AIR_CONDITIONER, "Salon", "LG", "Dual-Cool"
        );
        IDevice alarm = DeviceFactory.createDevice(
            DeviceType.ALARM, "Giriş", "Ajax", "Hub-2"
        );
        System.out.println("\n=== CIHAZLAR HUB'A KAYDEDILYOR (Observer Pattern) ===\n");
        hub.registerDevice(sicaklikSensor1);
        hub.registerDevice(sicaklikSensor2);
        hub.registerDevice(hareketSensor);
        hub.registerDevice(dumanSensor);
        hub.registerDevice(lamba1);
        hub.registerDevice(lamba2);
        hub.registerDevice(klima);
        hub.registerDevice(alarm);
        
        System.out.println("\n=== CİHAZ DETAY BİLGİLERİ (Inner Class Örneği) ===\n");
        if (sicaklikSensor1 instanceof abstract_classes.AbstractDevice) {
            ((abstract_classes.AbstractDevice) sicaklikSensor1).cihazBilgisiGoster();
        }
        hub.showAllDevices();
        System.out.println("\n=== STRATEJİ: COMFORT MODE (Strategy Pattern) ===");
        hub.setEnergyStrategy(new ComfortModeStrategy());
        System.out.println("\n=== SENSÖRLER BAŞLATILIYOR (Multithreading) ===");
        hub.startAllSensors();
        
        try {
            System.out.println("\n[SİSTEM] 15 saniye çalışacak...\n");
            Thread.sleep(15000);
            System.out.println("\n=== STRATEJİ DEĞİŞTİRİLİYOR: ECO MODE ===");
            hub.setEnergyStrategy(new EcoModeStrategy());
            
            Thread.sleep(10000);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n=== EXCEPTION HANDLING ÖRNEĞİ ===");
        try {
            if (hareketSensor instanceof HareketSensoru) {
                HareketSensoru hs = (HareketSensoru) hareketSensor;
                System.out.println("Mevcut pil seviyesi: %" + hs.pilDurumuGoster());
                
                for (int i = 0; i < 55; i++) {
                    hs.pilTuket(2);
                }
                System.out.println("Pil tüketildi. Yeni seviye: %" + hs.pilDurumuGoster());
            }
        } catch (exceptions.PilBittiException e) {
            System.err.println("❌ HATA: " + e.getMessage());
            if (hareketSensor instanceof HareketSensoru) {
                ((HareketSensoru) hareketSensor).sarjEt();
                System.out.println("✓ Sensör şarj edildi");
            }
        }
        
        hub.stopAllSensors();
        
        System.out.println("\n=== FINAL DURUM ===");
        hub.showAllDevices();
        
        System.out.println("\n=== VERİ GEÇMİŞİ ÖRNEĞİ ===");
        if (sicaklikSensor1 instanceof SicaklikSensoru) {
            ((SicaklikSensoru) sicaklikSensor1).veriGecmisiGoster();
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                   SİMÜLASYON TAMAMLANDI                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println("\n--- SIMULASYON BITTI ---");
        System.out.println("Pencereyi kapatmak icin ENTER tusuna basin...");
        new java.util.Scanner(System.in).nextLine();
    }
    
}
