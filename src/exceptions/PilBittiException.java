package exceptions;

public class PilBittiException extends Exception {
    public PilBittiException(String message) {
        super(message);
    }
    
    public PilBittiException(String message, Throwable cause) {
        super(message, cause);
    }

} 
