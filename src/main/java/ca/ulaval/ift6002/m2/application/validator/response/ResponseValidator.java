package ca.ulaval.ift6002.m2.application.validator.response;

public interface ResponseValidator<T> {

    void validate(T response) throws InvalidResponseException;
}
