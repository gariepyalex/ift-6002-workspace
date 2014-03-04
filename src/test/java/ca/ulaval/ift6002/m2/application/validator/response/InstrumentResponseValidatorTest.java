package ca.ulaval.ift6002.m2.application.validator.response;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

public class InstrumentResponseValidatorTest {

    private static final String SOILED_STATUS = InstrumentStatus.SOILED.toString();
    private static final String USED_STATUS = InstrumentStatus.USED_PATIENT.toString();
    private static final String UNUSED_STATUS = InstrumentStatus.UNUSED.toString();

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
    public void givenSoiledInstrumentShouldNotThrowAnException() {
        InstrumentResponse soiledInstrument = new InstrumentResponse(VALID_TYPECODE, SOILED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(soiledInstrument);
    }

    @Test
    public void givenUsedInstrumentShouldNotThrowAnException() {
        InstrumentResponse usedInstrument = new InstrumentResponse(VALID_TYPECODE, USED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(usedInstrument);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenResponseWithEmptyTypecodeShouldThrowException() {
        InstrumentResponse emptyTypecodeResponse = new InstrumentResponse(EMTPY_TYPECODE, SOILED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(emptyTypecodeResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInstrumentWithInvalidStatusShouldThrowException() {
        InstrumentResponse invalidInstrument = new InstrumentResponse(VALID_TYPECODE, INVALID_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(invalidInstrument);
    }

    @Test
    public void givenSoiledInstrumentWhenValidatingNewStatusShouldNotThrowAnException() {
        InstrumentResponse soiledInstrument = new InstrumentResponse(VALID_TYPECODE, SOILED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(soiledInstrument);
    }

    @Test
    public void givenUsedInstrumentWhenValidatingNewStatusShouldNotThrowAnException() {
        InstrumentResponse usedInstrument = new InstrumentResponse(VALID_TYPECODE, USED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(usedInstrument);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInstrumentWithEmptySerialWhenValidatingNewStatusShouldThrowAnException() {
        InstrumentResponse instrument = new InstrumentResponse(VALID_TYPECODE, USED_STATUS, EMPTY_SERIAL);

        instrumentResponseValidator.validateNewStatus(instrument);
    }

    @Test
    public void givenUnusedInstrumentWhenValidatingNewStatusShouldNotThrowAnException() {
        InstrumentResponse unusedInstrument = new InstrumentResponse(VALID_TYPECODE, UNUSED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(unusedInstrument);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInstrumentWithInvalidStatusWhenValidatingNewStatusShouldThrowAnException() {
        InstrumentResponse invalidInstrument = new InstrumentResponse(VALID_TYPECODE, INVALID_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(invalidInstrument);
    }
}
