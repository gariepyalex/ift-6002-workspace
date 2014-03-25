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

import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;

@RunWith(MockitoJUnitRunner.class)
public class InstrumentAssemblerTest {

    private static final Serial SERIAL = new Serial("serial");

    private static final InstrumentRequest INSTRUMENT_REQUEST = new InstrumentRequest("typecode",
            InstrumentStatus.SOILED.toString(), "serial");

    private static final InstrumentRequest INSTRUMENT_REQUEST_WITH_UNEXISTING_STATUS = new InstrumentRequest(
            "typecode", "an unexisting status", "serial");

    @Mock
    private InstrumentFactory instrumentFactory;

    @Mock
    private Instrument instrument;

    @InjectMocks
    private InstrumentAssembler instrumentAssembler;

    @Before
    public void setUp() {
        willReturn(SERIAL).given(instrument).getSerial();
    }

    @Test
    public void givenRequestWhenConvertToInstrumentShouldCallFactory() {
        instrumentAssembler.fromRequest(INSTRUMENT_REQUEST);

        verify(instrumentFactory).create(any(Typecode.class), any(InstrumentStatus.class), any(Serial.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenRequestWithUnexistingStatusWhenConvertToInstrumentShouldThrowException() {
        instrumentAssembler.fromRequest(INSTRUMENT_REQUEST_WITH_UNEXISTING_STATUS);
    }
}
