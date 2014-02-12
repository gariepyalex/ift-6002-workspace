package projectH.application.assemblers;

import projectH.application.responses.InstrumentDTO;
import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InstrumentStatus;

public class InstrumentDTOAssembler {

    public Instrument fromDTO(InstrumentDTO dto) {
        return new Instrument(dto.typecode, InstrumentStatus.valueOf(dto.status), dto.serial);
    }
}
