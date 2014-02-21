package ca.ulaval.ift6002.m2.application.assemblers;

import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;
import ca.ulaval.ift6002.m2.application.validator.dto.InstrumentDTOValidator;
import ca.ulaval.ift6002.m2.application.validator.dto.InvalidDTOException;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;

public class InstrumentDTOAssembler {

    public Instrument fromDTO(InstrumentDTO dto) throws InvalidDTOException {
        InstrumentDTOValidator validator = new InstrumentDTOValidator();
        validator.validate(dto);

        InstrumentStatus status = InstrumentStatus.valueOf(dto.status);
        Typecode typecode = new Typecode(dto.typecode);
        Serial serial = new Serial(dto.serial);

        return new Instrument(typecode, status, serial);
    }
}
