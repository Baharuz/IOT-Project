package devices;

import abstract_classes.AbstractDevice;
import interfaces.IActuator;

public class Klima extends AbstractDevice implements IActuator {
    private double hedefSicaklik;
    private String mod; 
    
    public Klima(String konum) {
        super(konum);
        this.hedefSicaklik = 24.0;
        this.mod = "Soğutma";
    }
    
    public Klima(String konum, String marka, String model) {
        super(konum, marka, model);
        this.hedefSicaklik = 24.0;
        this.mod = "Soğutma";
    }
    
    @Override
    public void performAction() {
        System.out.println("Klima çalışıyor - Hedef: " + hedefSicaklik + "°C, Mod: " + mod + " - " + konum);
    }
    
    @Override
    public void performAction(String parametre) {
        try {
            hedefSicaklik = Double.parseDouble(parametre);
            System.out.println("Klima hedef sıcaklığı ayarlandı: " + hedefSicaklik + "°C - " + konum);
            performAction();
        } catch (NumberFormatException e) {
            System.err.println("Geçersiz sıcaklık değeri: " + parametre);
        }
    }
    
    @Override
    public void calistir() {
        if (aktif) {
            performAction();
        }
    }
    
    public void setMod(String mod) {
        this.mod = mod;
    }
    
    public String getMod() {
        return mod;
    }
    
    public double getHedefSicaklik() {
        return hedefSicaklik;
    }
    
    public void setHedefSicaklik(double sicaklik) {
        this.hedefSicaklik = sicaklik;
    }
}
