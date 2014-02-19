package ca.ulaval.ift6002.m2.application.validator.dto;

import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;

public class InstrumentDTOValidator {

    public static void validate(InstrumentDTO dto) throws InvalidDTOException {
        if (typecodeDtoIsInvalid(dto) || statusDtoIsInvalid(dto)) {
            throw new InvalidDTOException(null);
        }
    }

    private static boolean statusDtoIsInvalid(InstrumentDTO dto) {
        return !(dto.status.equals("SOILED") || dto.status.equals("USED") || dto.status.equals("UNUSED"));
    }

    private static boolean typecodeDtoIsInvalid(InstrumentDTO dto) {
        return dto.typecode.equals("");
    }
}
