package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InstrumentDTO;

public class InstrumentDTOAssembler extends DTOAssembler<Instrument, InstrumentDTO> {

    @Override
    public Instrument fromDTO(InstrumentDTO dto) {
        Typecode typecode = new Typecode(dto.typecode);
        InstrumentStatus status = InstrumentStatus.determineFrom(dto.status);
        Serial serial = new Serial(dto.serial);

        return new Instrument(typecode, status, serial);
    }

    @Override
    public InstrumentDTO toDTO(Instrument element) {
        String status = element.getStatus().toString();
        String typecode = element.getTypecode().toString();
        String serial = element.getSerial().toString();

        return new InstrumentDTO(serial, typecode, status);
    }

}
