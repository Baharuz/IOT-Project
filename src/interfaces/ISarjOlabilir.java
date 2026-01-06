package interfaces;
import exceptions.PilBittiException;
public interface ISarjOlabilir {
	void sarjEt();
	int pilDurumuGoster();
	void pilTuket(int miktar) throws PilBittiException;

}
