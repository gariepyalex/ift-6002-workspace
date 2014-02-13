package ca.ulaval.ift6002.m2.application.assemblers;

import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;

public class InstrumentDTOAssembler {

    public Instrument fromDTO(InstrumentDTO dto) {
        Typecode typecode = new Typecode(dto.typecode);
        Serial serialNumber = new Serial(dto.serial);
        return new Instrument(typecode, InstrumentStatus.valueOf(dto.status), serialNumber);
    }
}
