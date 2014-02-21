package ca.ulaval.ift6002.m2.application.validator.dto;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;

public class InstrumentDTOValidatorTest {

    private static final String VALID_STATUS_SOILED = "SOILED";
    private static final String VALID_STATUS_USED = "USED";
    private static final String VALID_STATUS_UNUSED = "UNUSED";

    private static final String VALID_SERIAL = "sfsfs3s23";
    private static final String VALID_TYPECODE = "IT313131";

    private static final String EMTPY_TYPECODE = "";
    private static final String EMPTY_SERIAL = "";

    private static final String INVALID_STATUS = "";

    private InstrumentDTOValidator instrumentDTOValidator;

    @Before
    public void setup() {
        instrumentDTOValidator = new InstrumentDTOValidator();
    }

    @Test
    public void givenValidSoiledInstrumentDtoValidateShouldNotThrowAnException() throws InvalidDTOException {
        InstrumentDTO validSoiledInstrumentDto = new InstrumentDTO(VALID_TYPECODE, VALID_STATUS_SOILED, VALID_SERIAL);

        instrumentDTOValidator.validate(validSoiledInstrumentDto);
    }

    @Test
    public void givenValidUsedInstrumentDtoValidateShouldNotThrowAnException() throws InvalidDTOException {
        InstrumentDTO validUsedInstrumentDto = new InstrumentDTO(VALID_TYPECODE, VALID_STATUS_USED, VALID_SERIAL);

        instrumentDTOValidator.validate(validUsedInstrumentDto);
    }

    @Test
    public void givenValidInstrumentDtoWithEmptySerialValidateShouldNotThrowAnException() throws InvalidDTOException {
        InstrumentDTO validUsedInstrumentDto = new InstrumentDTO(VALID_TYPECODE, VALID_STATUS_USED, EMPTY_SERIAL);

        instrumentDTOValidator.validate(validUsedInstrumentDto);
    }

    @Test
    public void givenValidUnusedInstrumentDtoValidateShouldNotThrowAnException() throws InvalidDTOException {
        InstrumentDTO validUnusedInstrumentDto = new InstrumentDTO(VALID_TYPECODE, VALID_STATUS_UNUSED, VALID_SERIAL);

        instrumentDTOValidator.validate(validUnusedInstrumentDto);
    }

    @Test(expected = InvalidDTOException.class)
    public void givenDtoWithEmptyTypecodeShouldThrowException() throws InvalidDTOException {
        InstrumentDTO emptyTypecodeDto = new InstrumentDTO(EMTPY_TYPECODE, VALID_STATUS_SOILED, VALID_SERIAL);

        instrumentDTOValidator.validate(emptyTypecodeDto);
    }

    @Test(expected = InvalidDTOException.class)
    public void givenDtoWithInvalidStatusShouldThrowException() throws InvalidDTOException {
        InstrumentDTO invalidStatusDto = new InstrumentDTO(VALID_TYPECODE, INVALID_STATUS, VALID_SERIAL);

        instrumentDTOValidator.validate(invalidStatusDto);
    }
}
