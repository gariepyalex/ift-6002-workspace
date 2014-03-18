package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InstrumentDTO;

@RunWith(MockitoJUnitRunner.class)
public class InstrumentDTOAssemblerTest {

    private static final Serial SERIAL = new Serial("A Serial");
    private static final Typecode TYPECODE = new Typecode("A typecode");
    private static final InstrumentStatus STATUS = InstrumentStatus.UNUSED;

    private static final InstrumentDTO INSTRUMENT_DTO = new InstrumentDTO(SERIAL.toString(), TYPECODE.toString(),
            STATUS.toString());

    @Mock
    private static InstrumentFactory instrumentFactory;

    @Mock
    private static Instrument instrument;

    @InjectMocks
    private InstrumentDTOAssembler instrumentDTOAssembler;

    @Before
    public void setUp() {
        setUpInstrument(TYPECODE, STATUS, SERIAL);
    }

    @Test
    public void givenInstrumentWhenConvertToDTOShouldReturnGivenInstrumentDTO() {
        InstrumentDTO dtoBuilt = instrumentDTOAssembler.toDTO(instrument);

        assertInstrumentDTOEquals(INSTRUMENT_DTO, dtoBuilt);
    }

    @Test
    public void givenInstrumentDTOWhenConvertFromInstrumentShouldCallInstrumentFactory() {
        instrumentDTOAssembler.fromDTO(INSTRUMENT_DTO);

        verify(instrumentFactory).create(any(Typecode.class), any(InstrumentStatus.class), any(Serial.class));
    }

    private void assertInstrumentDTOEquals(InstrumentDTO expected, InstrumentDTO actual) {
        assertEquals(expected.serial, actual.serial);
        assertEquals(expected.status, actual.status);
        assertEquals(expected.typecode, actual.typecode);
    }

    private void setUpInstrument(Typecode typecode, InstrumentStatus status, Serial serialNumber) {
        willReturn(serialNumber).given(instrument).getSerial();
        willReturn(typecode).given(instrument).getTypecode();
        willReturn(status).given(instrument).getStatus();
    }

}
