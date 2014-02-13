package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;

public class InstrumentDTOAssemblerTest {

    private static final Serial SERIAL = new Serial("serial");
    private static final Typecode TYPECODE = new Typecode("typecode");
    private static final InstrumentStatus STATUS = InstrumentStatus.SOILED;

    private static final Instrument INSTRUMENT = new Instrument(TYPECODE, STATUS, SERIAL);
    private static final String DTO_TYPECODE = "typecode";
    private static final String DTO_SERIAL = "serial";
    private static final InstrumentDTO INSTRUMENT_DTO = new InstrumentDTO(DTO_TYPECODE, STATUS.toString(), DTO_SERIAL);

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
