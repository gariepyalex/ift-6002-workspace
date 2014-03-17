package ca.ulaval.ift6002.m2.domain.patient;

public class DeadPatientException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DeadPatientException(String message) {
        super(message);
    }

}
