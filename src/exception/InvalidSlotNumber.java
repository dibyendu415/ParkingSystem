package exception;

public class InvalidSlotNumber extends RuntimeException {
    public InvalidSlotNumber(String message) {
        super(message);
    }
}