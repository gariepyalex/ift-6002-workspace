package ca.ulaval.ift6002.m2.application.validator.response;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;

public class InstrumentResponseValidatorTest {

    private static final String SOILED_STATUS = "SOILED";
    private static final String USED_STATUS = "USED_PATIENT";
    private static final String UNUSED_STATUS = "UNUSED";

    private static final String VALID_SERIAL = "sfsfs3s23";
    private static final String VALID_TYPECODE = "IT313131";

    private static final String EMTPY_TYPECODE = "";
    private static final String EMPTY_SERIAL = "";

    private static final String INVALID_STATUS = "";

    private InstrumentResponseValidator instrumentResponseValidator;

    @Before
    public void setUp() {
        instrumentResponseValidator = new InstrumentResponseValidator();
    }

    @Test
    public void givenSoiledInstrumentShouldNotThrowAnException() throws InvalidResponseException {
        InstrumentResponse soiledInstrument = new InstrumentResponse(VALID_TYPECODE, SOILED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(soiledInstrument);
    }

    @Test
    public void givenUsedInstrumentShouldNotThrowAnException() throws InvalidResponseException {
        InstrumentResponse usedInstrument = new InstrumentResponse(VALID_TYPECODE, USED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(usedInstrument);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInstrumentResponseWithEmptyTypecodeShouldThrowException() throws InvalidResponseException {
        InstrumentResponse emptyTypecodeResponse = new InstrumentResponse(EMTPY_TYPECODE, SOILED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(emptyTypecodeResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInstrumentWithInvalidStatusShouldThrowException() throws InvalidResponseException {
        InstrumentResponse invalidInstrument = new InstrumentResponse(VALID_TYPECODE, INVALID_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(invalidInstrument);
    }

    @Test
    public void givenSoiledInstrumentWhenValidatingNewStatusShouldNotThrowAnException() throws InvalidResponseException {
        InstrumentResponse soiledInstrument = new InstrumentResponse(VALID_TYPECODE, SOILED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(soiledInstrument);
    }

    @Test
    public void givenUsedInstrumentWhenValidatingNewStatusShouldNotThrowAnException() throws InvalidResponseException {
        InstrumentResponse usedInstrument = new InstrumentResponse(VALID_TYPECODE, USED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(usedInstrument);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInstrumentWithEmptySerialWhenValidatingNewStatusShouldThrowAnException()
            throws InvalidResponseException {
        InstrumentResponse instrument = new InstrumentResponse(VALID_TYPECODE, USED_STATUS, EMPTY_SERIAL);

        instrumentResponseValidator.validateNewStatus(instrument);
    }

    @Test
    public void givenUnusedInstrumentWhenValidatingNewStatusShouldNotThrowAnException() throws InvalidResponseException {
        InstrumentResponse unusedInstrument = new InstrumentResponse(VALID_TYPECODE, UNUSED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(unusedInstrument);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInstrumentWithInvalidStatusWhenValidatingNewStatusShouldThrowAnException()
            throws InvalidResponseException {
        InstrumentResponse invalidInstrument = new InstrumentResponse(VALID_TYPECODE, INVALID_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(invalidInstrument);
    }
}
