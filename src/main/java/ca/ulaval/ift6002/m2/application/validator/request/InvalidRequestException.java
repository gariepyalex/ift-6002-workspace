package ca.ulaval.ift6002.m2.application.validator.request;

public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = -8996461192147116064L;

    private final String code;

    public InvalidRequestException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
