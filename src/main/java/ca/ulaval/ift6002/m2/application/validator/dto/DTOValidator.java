package ca.ulaval.ift6002.m2.application.validator.dto;

public interface DTOValidator<T> {

    void validate(T dto) throws InvalidDTOException;
}
