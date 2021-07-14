package exception;

public class VehicleNotFound extends RuntimeException {
    public VehicleNotFound(String format) {
        super(format);
    }
}