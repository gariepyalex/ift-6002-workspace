package projectH.domain.instrument;

public class InvalidInstrumentException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidInstrumentException(String message) {
        super(message);
    }
}
