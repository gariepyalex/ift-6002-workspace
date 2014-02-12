package ca.ulaval.ift6002.m2.application.assemblers;

import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

public class InstrumentDTOAssembler {

    public Instrument fromDTO(InstrumentDTO dto) {
        return new Instrument(dto.typecode, InstrumentStatus.valueOf(dto.status), dto.serial);
    }
}
