package devices;

import abstract_classes.AbstractDevice;
import interfaces.IActuator;

public class AkilliLamba extends AbstractDevice implements IActuator {
    private int parlaklik; 
    
    public AkilliLamba(String konum) {
        super(konum);
        this.parlaklik = 0;
    }
    
    public AkilliLamba(String konum, String marka, String model) {
        super(konum, marka, model);
        this.parlaklik = 0;
    }
    
    @Override
    public void performAction() {
        parlaklik = 100;
        System.out.println("Lamba tam parlaklikta açıldı: " + konum);
    }
    
    @Override
    public void performAction(String parametre) {
        try {
            parlaklik = Integer.parseInt(parametre);
            if (parlaklik < 0) parlaklik = 0;
            if (parlaklik > 100) parlaklik = 100;
            System.out.println("Lamba parlakliği ayarlandı: %" + parlaklik + " - " + konum);
        } catch (NumberFormatException e) {
            System.err.println("Geçersiz parlaklik değeri: " + parametre);
        }
    }
    
    @Override
    public void calistir() {
        if (aktif) {
            performAction();
        }
    }
    
    public int getParlaklik() {
        return parlaklik;
    }
    
    public void setParlaklik(int parlaklik) {
        this.parlaklik = Math.max(0, Math.min(100, parlaklik));
    }
}
