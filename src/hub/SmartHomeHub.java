package hub;

import interfaces.*;
import abstract_classes.Sensor;
import devices.*;
import factory.*;
import strategy.*;
import java.util.*;
import java.time.LocalTime;

public class SmartHomeHub implements IObserver {
    private String hubId;
    private List<IDevice> devices;
    private Map<String, IDevice> deviceMap;
    private IEnergyStrategy energyStrategy;
    
    public SmartHomeHub(String hubId) {
        this.hubId = hubId;
        this.devices = new ArrayList<>();
        this.deviceMap = new HashMap<>();
        this.energyStrategy = new ComfortModeStrategy();
    }
    
    public void registerDevice(IDevice device) {
        devices.add(device);
        
        if (device instanceof abstract_classes.AbstractDevice) {
            abstract_classes.AbstractDevice absDevice = (abstract_classes.AbstractDevice) device;
            deviceMap.put(absDevice.getId(), device);
        }
        
        if (device instanceof Sensor) {
            ((Sensor) device).addObserver(this);
        }
        
        System.out.println("Cihaz Hub'a kaydedildi: " + device.getClass().getSimpleName());
    }
    
    public void unregisterDevice(IDevice device) {
        devices.remove(device);
        if (device instanceof Sensor) {
            ((Sensor) device).removeObserver(this);
        }
    }
    
    @Override
    public void update(String sensorId, double value, String location) {
        System.out.println("\n[HUB] Sensör verisi alındı - ID: " + sensorId + 
                          ", Değer: " + String.format("%.2f", value) + 
                          ", Konum: " + location);
        
        IDevice sensor = deviceMap.get(sensorId);
        
        if (sensor instanceof SicaklikSensoru) {
            handleTemperature(value, location);
        }
        else if (sensor instanceof HareketSensoru && value == 1.0) {
            handleMotion(location);
        }
        else if (sensor instanceof DumanSensoru && value == 1.0) {
            handleSmoke(location);
        }
    }
    
    private void handleTemperature(double temperature, String location) {
        for (IDevice device : devices) {
            if (device instanceof Klima) {
                Klima klima = (Klima) device;
                if (klima.getKonum().equals(location)) {
                    if (energyStrategy.shouldActivateAC(temperature)) {
                        if (!klima.isAktif()) {
                            klima.turnOn();
                        }
                        klima.setMod("Soğutma");
                        klima.performAction("22");
                        System.out.println("[KARAR] Sıcaklık yüksek, klima açıldı (Strateji: " + 
                                         energyStrategy.getStrategyName() + ")");
                    } else if (energyStrategy.shouldActivateHeater(temperature)) {
                        if (!klima.isAktif()) {
                            klima.turnOn();
                        }
                        klima.setMod("Isıtma");
                        klima.performAction("24");
                        System.out.println("[KARAR] Sıcaklık düşük, ısıtma açıldı");
                    }
                }
            }
        }
    }
    
    private void handleMotion(String location) {
        LocalTime now = LocalTime.now();
        boolean isNight = now.isAfter(LocalTime.of(22, 0)) || now.isBefore(LocalTime.of(6, 0));
        for (IDevice device : devices) {
            if (device instanceof AkilliLamba) {
                AkilliLamba lamba = (AkilliLamba) device;
                if (lamba.getKonum().equals(location)) {
                    if (!lamba.isAktif()) {
                        lamba.turnOn();
                    }
                    lamba.performAction(isNight ? "50" : "100");
                    System.out.println("[KARAR] Hareket algılandı, lamba açıldı");
                }
            }
        }
        
        if (isNight) {
            for (IDevice device : devices) {
                if (device instanceof Alarm) {
                    Alarm alarm = (Alarm) device;
                    if (!alarm.isAktif()) {
                        alarm.turnOn();
                    }
                    alarm.performAction();
                    System.out.println("[KARAR] Gece hareketi, alarm tetiklendi!");
                }
            }
        }
    }
    
    private void handleSmoke(String location) {
        System.out.println("⚠️ [ACIL] DUMAN ALGILANDI! - Konum: " + location);
        
        for (IDevice device : devices) {
            if (device instanceof Alarm) {
                Alarm alarm = (Alarm) device;
                if (!alarm.isAktif()) {
                    alarm.turnOn();
                }
                alarm.performAction();
            }
        }
    }
    
    public void setEnergyStrategy(IEnergyStrategy strategy) {
        this.energyStrategy = strategy;
        System.out.println("\n[HUB] Enerji stratejisi değiştirildi: " + strategy.getStrategyName());
    }
    
    public void startAllSensors() {
        System.out.println("\n[HUB] Tüm sensörler başlatılıyor...");
        for (IDevice device : devices) {
            device.turnOn();
            if (device instanceof Sensor) {
                device.calistir();
            }
        }
    }
    
    public void stopAllSensors() {
        System.out.println("\n[HUB] Tüm sensörler durduruluyor...");
        for (IDevice device : devices) {
            if (device instanceof Sensor) {
                ((Sensor) device).stopReading();
            }
            device.turnOff();
        }
    }
    
    public void showAllDevices() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║          SMART HOME HUB - CIHAZ LİSTESİ                ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        for (IDevice device : devices) {
            System.out.println("║ " + device.getStatus());
        }
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
    
    public String getHubId() {
        return hubId;
    }
    
    public List<IDevice> getDevices() {
        return new ArrayList<>(devices);
    }
}
