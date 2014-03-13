package ca.ulaval.ift6002.m2.domain.prescription;

public class NotEnoughRenewalsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotEnoughRenewalsException(String message) {
        super(message);
    }
}
