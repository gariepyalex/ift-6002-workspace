package ca.ulaval.ift6002.m2.domain.date;

public class DateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DateException(String description) {
        super(description);
    }
}
