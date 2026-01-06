package interfaces;
import exceptions.SensorArizasiException;
public interface ISensor extends IDevice {
	double readValue() throws SensorArizasiException;
	void veriKaydet(double deger);
	void veriKaydet(double deger, String zaman);

}
