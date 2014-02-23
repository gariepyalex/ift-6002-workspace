package ca.ulaval.ift6002.m2.application.validator.response;

public interface ResponseValidator<RESPONSE> {

    void validate(RESPONSE response) throws InvalidResponseException;
}
