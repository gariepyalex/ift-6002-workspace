package ca.ulaval.ift6002.m2.application.validator.dto;

public class InvalidDTOException extends Exception {

    private static final long serialVersionUID = -8996461192147116064L;

    public InvalidDTOException() {
        super();
    }

    public InvalidDTOException(String message) {
        super(message);
    }

}
