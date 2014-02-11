package projectH.application.assemblers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import projectH.application.responses.InstrumentDTO;
import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InstrumentStatus;

public class InstrumentDTOAssemblerTest {

	private final static String SERIAL = "serial";
	private final static String TYPECODE = "typecode";
	private final static InstrumentStatus STATUS = InstrumentStatus.SOILED;

	private final static Instrument INSTRUMENT = new Instrument(TYPECODE, STATUS, SERIAL);
	private final static InstrumentDTO INSTRUMENT_DTO = new InstrumentDTO(TYPECODE, STATUS.toString(), SERIAL);

	private InstrumentDTOAssembler assembler;

	@Before
	public void setup() {
		assembler = new InstrumentDTOAssembler();
	}

	@Test
	public void givenInstrumentDTOWhenConvertToInstrumentShouldReturnGivenInstrument() {
		Instrument instrumentBuilt = assembler.fromDTO(INSTRUMENT_DTO);

		assertEquals(INSTRUMENT, instrumentBuilt);
	}
}
