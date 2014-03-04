package ca.ulaval.ift6002.m2.application.validator.response;

public class InvalidResponseException extends RuntimeException {

    private static final long serialVersionUID = -8996461192147116064L;

    private final String code;

    public InvalidResponseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
