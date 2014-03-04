package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InstrumentDTO;

public class InstrumentDTOAssemblerTest {

    private static final Serial SERIAL = new Serial("A Serial");
    private static final Typecode TYPECODE = new Typecode("A typecode");
    private static final InstrumentStatus STATUS = InstrumentStatus.UNUSED;

    private static final Instrument INSTRUMENT = new Instrument(TYPECODE, STATUS, SERIAL);
    private static final InstrumentDTO INSTRUMENT_DTO = new InstrumentDTO(SERIAL.toString(), TYPECODE.toString(),
            STATUS.toString());

    private InstrumentDTOAssembler instrumentDTOAssembler;

    @Before
    public void setUp() {
        instrumentDTOAssembler = new InstrumentDTOAssembler();
    }

    @Test
    public void givenInstrumentWhenConvertToDTOShouldReturnGivenInstrumentDTO() {
        InstrumentDTO dtoBuilt = instrumentDTOAssembler.toDTO(INSTRUMENT);

        assertInstrumentDTOEquals(INSTRUMENT_DTO, dtoBuilt);
    }

    @Test
    public void givenInstrumentDTOWhenConvertFromInstrumentShouldReturnGivenInstrument() {
        Instrument instrument = instrumentDTOAssembler.fromDTO(INSTRUMENT_DTO);

        assertEquals(INSTRUMENT, instrument);
    }

    private void assertInstrumentDTOEquals(InstrumentDTO expected, InstrumentDTO actual) {
        assertEquals(expected.serial, actual.serial);
        assertEquals(expected.status, actual.status);
        assertEquals(expected.typecode, actual.typecode);
    }

}
