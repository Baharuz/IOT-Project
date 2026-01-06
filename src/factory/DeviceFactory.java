package factory;

import interfaces.IDevice;
import devices.*;

public class DeviceFactory {
    
    public static IDevice createDevice(DeviceType type, String konum) {
        return createDevice(type, konum, null, null);
    }
    
    public static IDevice createDevice(DeviceType type, String konum, String marka, String model) {
        switch (type) {
            case TEMPERATURE_SENSOR:
                return marka != null ? 
                    new SicaklikSensoru(konum, marka, model) : 
                    new SicaklikSensoru(konum);
                
            case MOTION_SENSOR:
                return marka != null ? 
                    new HareketSensoru(konum, marka, model) : 
                    new HareketSensoru(konum);
                
            case SMOKE_SENSOR:
                return marka != null ? 
                    new DumanSensoru(konum, marka, model) : 
                    new DumanSensoru(konum);
                
            case SMART_LAMP:
                return marka != null ? 
                    new AkilliLamba(konum, marka, model) : 
                    new AkilliLamba(konum);
                
            case AIR_CONDITIONER:
                return marka != null ? 
                    new Klima(konum, marka, model) : 
                    new Klima(konum);
                
            case ALARM:
                return marka != null ? 
                    new Alarm(konum, marka, model) : 
                    new Alarm(konum);
                
            default:
                throw new IllegalArgumentException("Bilinmeyen cihaz tipi: " + type);
        }
    }
}
