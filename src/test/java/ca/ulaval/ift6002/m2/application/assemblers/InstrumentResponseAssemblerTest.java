package ca.ulaval.ift6002.m2.application.assemblers;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;

@RunWith(MockitoJUnitRunner.class)
public class InstrumentResponseAssemblerTest {

    private static final Serial SERIAL = new Serial("serial");
    private static final Typecode TYPECODE = new Typecode("typecode");
    private static final InstrumentStatus STATUS = InstrumentStatus.SOILED;

    private static final String RESPONSE_TYPECODE = "typecode";
    private static final String RESPONSE_SERIAL = "serial";

    private static final InstrumentResponse INSTRUMENT_RESPONSE = new InstrumentResponse(RESPONSE_TYPECODE,
            STATUS.toString(), RESPONSE_SERIAL);
    private static final InstrumentResponse INSTRUMENT_RESPONSE_WITH_UNEXISTING_STATUS = new InstrumentResponse(
            RESPONSE_TYPECODE, "an unexisting status", RESPONSE_SERIAL);

    @Mock
    private static InstrumentFactory instrumentFactory;

    @Mock
    private static Instrument instrument;

    @InjectMocks
    private InstrumentResponseAssembler instrumentAssembler;

    @Before
    public void setUp() {
        setUpInstrument(TYPECODE, STATUS, SERIAL);
    }

    @Test
    public void givenResponseWhenConvertToInstrumentShouldCallFactory() {
        instrumentAssembler.fromResponse(INSTRUMENT_RESPONSE);

        verify(instrumentFactory).create(any(Typecode.class), any(InstrumentStatus.class), any(Serial.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenResponseWithUnexistingStatusWhenConvertToInstrumentShouldThrowException() {
        instrumentAssembler.fromResponse(INSTRUMENT_RESPONSE_WITH_UNEXISTING_STATUS);
    }

    private void setUpInstrument(Typecode typecode, InstrumentStatus status, Serial serialNumber) {
        willReturn(serialNumber).given(instrument).getSerial();
        willReturn(typecode).given(instrument).getTypecode();
        willReturn(status).given(instrument).getStatus();
    }
}
