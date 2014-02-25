package ca.ulaval.ift6002.m2.application.validator.response;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;

public class InstrumentResponseValidatorTest {

    private static final String VALID_STATUS_SOILED = "SOILED";
    private static final String VALID_STATUS_USED = "USED";
    private static final String VALID_STATUS_UNUSED = "UNUSED";

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
    public void givenValidSoiledInstrumentResponseValidateShouldNotThrowAnException() throws InvalidResponseException {
        InstrumentResponse validSoiledInstrumentResponse = new InstrumentResponse(VALID_TYPECODE, VALID_STATUS_SOILED,
                VALID_SERIAL);

        instrumentResponseValidator.validate(validSoiledInstrumentResponse);
    }

    @Test
    public void givenValidUsedInstrumentResponseValidateShouldNotThrowAnException() throws InvalidResponseException {
        InstrumentResponse validUsedInstrumentResponse = new InstrumentResponse(VALID_TYPECODE, VALID_STATUS_USED,
                VALID_SERIAL);

        instrumentResponseValidator.validate(validUsedInstrumentResponse);
    }

    @Test
    public void givenValidInstrumentResponseWithEmptySerialValidateShouldNotThrowAnException()
            throws InvalidResponseException {
        InstrumentResponse validUsedInstrumentResponse = new InstrumentResponse(VALID_TYPECODE, VALID_STATUS_USED,
                EMPTY_SERIAL);

        instrumentResponseValidator.validate(validUsedInstrumentResponse);
    }

    @Test
    public void givenValidUnusedInstrumentResponseValidateShouldNotThrowAnException() throws InvalidResponseException {
        InstrumentResponse validUnusedInstrumentResponse = new InstrumentResponse(VALID_TYPECODE, VALID_STATUS_UNUSED,
                VALID_SERIAL);

        instrumentResponseValidator.validate(validUnusedInstrumentResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenResponseWithEmptyTypecodeShouldThrowException() throws InvalidResponseException {
        InstrumentResponse emptyTypecodeResponse = new InstrumentResponse(EMTPY_TYPECODE, VALID_STATUS_SOILED,
                VALID_SERIAL);

        instrumentResponseValidator.validate(emptyTypecodeResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenResponseWithInvalidStatusShouldThrowException() throws InvalidResponseException {
        InstrumentResponse invalidStatusResponse = new InstrumentResponse(VALID_TYPECODE, INVALID_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(invalidStatusResponse);
    }
}
