package devices;

import abstract_classes.AbstractDevice;
import interfaces.IActuator;

public class Alarm extends AbstractDevice implements IActuator {
    private boolean calıyor;
    
    public Alarm(String konum) {
        super(konum);
        this.calıyor = false;
    }
    
    public Alarm(String konum, String marka, String model) {
        super(konum, marka, model);
        this.calıyor = false;
    }
    
    @Override
    public void performAction() {
        alarmCal();
    }
    
    @Override
    public void performAction(String parametre) {
        if ("durdur".equalsIgnoreCase(parametre)) {
            alarmDurdur();
        } else {
            alarmCal();
        }
    }
    
    public void alarmCal() {
        calıyor = true;
        System.out.println("🚨 ALARM ÇALIYOR! - Konum: " + konum + " 🚨");
    }
    
    public void alarmDurdur() {
        calıyor = false;
        System.out.println("Alarm durduruldu - " + konum);
    }
    
    @Override
    public void calistir() {
        if (aktif && calıyor) {
            System.out.println("Alarm sistemi aktif ve beklemede - " + konum);
        }
    }
    
    public boolean isCalıyor() {
        return calıyor;
    }
}
