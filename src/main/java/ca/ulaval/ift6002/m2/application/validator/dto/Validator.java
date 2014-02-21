package ca.ulaval.ift6002.m2.application.validator.dto;

public interface Validator<DTO> {

    void validate(DTO dto) throws InvalidDTOException;
}
