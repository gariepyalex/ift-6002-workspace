package ca.ulaval.ift6002.m2.application.validator.request;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.application.validator.request.InstrumentRequestValidator;
import ca.ulaval.ift6002.m2.application.validator.request.InvalidRequestException;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

public class InstrumentRequestValidatorTest {

    private static final String SOILED_STATUS = InstrumentStatus.SOILED.toString();
    private static final String USED_STATUS = InstrumentStatus.USED_PATIENT.toString();
    private static final String UNUSED_STATUS = InstrumentStatus.UNUSED.toString();

    private static final String VALID_SERIAL = "sfsfs3s23";
    private static final String VALID_TYPECODE = "IT313131";

    private static final String EMTPY_TYPECODE = "";
    private static final String EMPTY_SERIAL = "";

    private static final String INVALID_STATUS = "";

    private InstrumentRequestValidator instrumentResponseValidator;

    @Before
    public void setUp() {
        instrumentResponseValidator = new InstrumentRequestValidator();
    }

    @Test
    public void givenSoiledInstrumentShouldNotThrowAnException() {
        InstrumentRequest soiledInstrument = new InstrumentRequest(VALID_TYPECODE, SOILED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(soiledInstrument);
    }

    @Test
    public void givenUsedInstrumentShouldNotThrowAnException() {
        InstrumentRequest usedInstrument = new InstrumentRequest(VALID_TYPECODE, USED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(usedInstrument);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenResponseWithEmptyTypecodeShouldThrowException() {
        InstrumentRequest emptyTypecodeResponse = new InstrumentRequest(EMTPY_TYPECODE, SOILED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(emptyTypecodeResponse);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInstrumentWithInvalidStatusShouldThrowException() {
        InstrumentRequest invalidInstrument = new InstrumentRequest(VALID_TYPECODE, INVALID_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validate(invalidInstrument);
    }

    @Test
    public void givenSoiledInstrumentWhenValidatingNewStatusShouldNotThrowAnException() {
        InstrumentRequest soiledInstrument = new InstrumentRequest(VALID_TYPECODE, SOILED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(soiledInstrument);
    }

    @Test
    public void givenUsedInstrumentWhenValidatingNewStatusShouldNotThrowAnException() {
        InstrumentRequest usedInstrument = new InstrumentRequest(VALID_TYPECODE, USED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(usedInstrument);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInstrumentWithEmptySerialWhenValidatingNewStatusShouldThrowAnException() {
        InstrumentRequest instrument = new InstrumentRequest(VALID_TYPECODE, USED_STATUS, EMPTY_SERIAL);

        instrumentResponseValidator.validateNewStatus(instrument);
    }

    @Test
    public void givenUnusedInstrumentWhenValidatingNewStatusShouldNotThrowAnException() {
        InstrumentRequest unusedInstrument = new InstrumentRequest(VALID_TYPECODE, UNUSED_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(unusedInstrument);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInstrumentWithInvalidStatusWhenValidatingNewStatusShouldThrowAnException() {
        InstrumentRequest invalidInstrument = new InstrumentRequest(VALID_TYPECODE, INVALID_STATUS, VALID_SERIAL);

        instrumentResponseValidator.validateNewStatus(invalidInstrument);
    }
}
