package ca.ulaval.ift6002.m2.application.validator.response;

public class InvalidResponseException extends Exception {

    private static final long serialVersionUID = -8996461192147116064L;
    private String errorType;

    public InvalidResponseException(String errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType;
    }

}
