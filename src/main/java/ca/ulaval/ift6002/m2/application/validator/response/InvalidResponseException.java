package ca.ulaval.ift6002.m2.application.validator.response;

public class InvalidResponseException extends Exception {

    private static final long serialVersionUID = -8996461192147116064L;

    public InvalidResponseException(String message) {
        super(message);
    }

}
