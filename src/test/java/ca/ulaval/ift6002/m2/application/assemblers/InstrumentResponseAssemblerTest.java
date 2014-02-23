package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;

public class InstrumentResponseAssemblerTest {

    private static final Serial SERIAL = new Serial("serial");
    private static final Typecode TYPECODE = new Typecode("typecode");
    private static final InstrumentStatus STATUS = InstrumentStatus.SOILED;

    private static final Instrument INSTRUMENT = new Instrument(TYPECODE, STATUS, SERIAL);
    private static final String RESPONSE_TYPECODE = "typecode";
    private static final String RESPONSE_SERIAL = "serial";
    private static final InstrumentResponse INSTRUMENT_RESPONSE = new InstrumentResponse(RESPONSE_TYPECODE,
            STATUS.toString(), RESPONSE_SERIAL);

    private InstrumentResponseAssembler instrumentAssembler;

    @Before
    public void setup() {
        instrumentAssembler = new InstrumentResponseAssembler();
    }

    @Test
    public void givenInstrumentResponseWhenConvertToInstrumentShouldReturnGivenInstrument()
            throws InvalidResponseException {
        Instrument instrumentBuilt = instrumentAssembler.fromResponse(INSTRUMENT_RESPONSE);
        assertEquals(INSTRUMENT, instrumentBuilt);
    }
}
