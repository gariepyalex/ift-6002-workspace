package ca.ulaval.ift6002.m2.domain.prescription;

public class InvalidPrescriptionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidPrescriptionException(String string) {
        super(string);
    }

}
