package ca.ulaval.ift6002.m2.application.validator.dto;

import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;

public class InstrumentDTOValidator implements DTOValidator<InstrumentDTO> {

    @Override
    public void validate(InstrumentDTO dto) throws InvalidDTOException {
        if (!isTypecodeValid(dto)) {
            throw new InvalidDTOException("Typecode must not be empty");
        }
        if (!isStatusValid(dto)) {
            throw new InvalidDTOException("The status value is not valid");
        }
    }

    private boolean isTypecodeValid(InstrumentDTO dto) {
        return !dto.typecode.isEmpty();
    }

    private boolean isStatusValid(InstrumentDTO dto) {
        return (dto.status.equals("SOILED") || dto.status.equals("USED") || dto.status.equals("UNUSED"));
    }

}
