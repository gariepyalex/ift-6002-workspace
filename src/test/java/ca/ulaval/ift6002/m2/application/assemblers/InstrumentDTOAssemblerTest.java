package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

public class InstrumentDTOAssemblerTest {

    private final static String SERIAL = "serial";
    private final static String TYPECODE = "typecode";
    private final static InstrumentStatus STATUS = InstrumentStatus.SOILED;

    private final static Instrument INSTRUMENT = new Instrument(TYPECODE, STATUS, SERIAL);
    private final static InstrumentDTO INSTRUMENT_DTO = new InstrumentDTO(TYPECODE, STATUS.toString(), SERIAL);

    private InstrumentDTOAssembler instrumentAssembler;

    @Before
    public void setup() {
        instrumentAssembler = new InstrumentDTOAssembler();
    }

    @Test
    public void givenInstrumentDTOWhenConvertToInstrumentShouldReturnGivenInstrument() {
        Instrument instrumentBuilt = instrumentAssembler.fromDTO(INSTRUMENT_DTO);

        assertEquals(INSTRUMENT, instrumentBuilt);
    }
}
