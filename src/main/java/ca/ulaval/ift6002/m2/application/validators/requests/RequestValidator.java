package ca.ulaval.ift6002.m2.application.validators.requests;

public interface RequestValidator<T> {

    void validate(T request);
}
