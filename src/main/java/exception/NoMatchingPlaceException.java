package exception;

public class NoMatchingPlaceException extends RuntimeException {
    public NoMatchingPlaceException(String message) {
        super(message);
    }
}
