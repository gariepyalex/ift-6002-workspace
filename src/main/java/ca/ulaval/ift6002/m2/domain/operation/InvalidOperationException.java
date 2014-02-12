package ca.ulaval.ift6002.m2.domain.operation;

public class InvalidOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidOperationException(String description) {
        super(description);
    }

}
