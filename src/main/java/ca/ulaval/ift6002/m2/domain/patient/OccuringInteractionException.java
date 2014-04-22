package ca.ulaval.ift6002.m2.domain.patient;

public class OccuringInteractionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OccuringInteractionException() {
        super("An interaction with an already assigned prescription occuring");
    }

}
