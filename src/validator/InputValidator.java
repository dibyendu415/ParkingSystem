package validator;

public class InputValidator {
    public static boolean isValidSlotNumber(int number) {
        if (number <= 0) {
            return false;
        }
        return true;
    }

}
