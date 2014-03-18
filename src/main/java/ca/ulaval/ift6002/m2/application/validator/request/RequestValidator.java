package ca.ulaval.ift6002.m2.application.validator.request;

public interface RequestValidator<T> {

    void validate(T request);
}
