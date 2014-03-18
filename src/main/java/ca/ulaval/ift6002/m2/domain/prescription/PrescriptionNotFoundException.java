package ca.ulaval.ift6002.m2.domain.prescription;

public class PrescriptionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PrescriptionNotFoundException(String message) {
        super(message);
    }
}
