package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;
import ca.ulaval.ift6002.m2.factory.FactoryLocator;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InstrumentDTO;

public class InstrumentDTOAssembler extends DTOAssembler<Instrument, InstrumentDTO> {

    private final InstrumentFactory instrumentFactory;

    public InstrumentDTOAssembler() {
        instrumentFactory = FactoryLocator.getInstrumentFactory();
    }

    public InstrumentDTOAssembler(InstrumentFactory instrumentFactory) {
        this.instrumentFactory = instrumentFactory;
    }

    @Override
    public Instrument fromDTO(InstrumentDTO dto) {
        Typecode typecode = new Typecode(dto.typecode);
        InstrumentStatus status = InstrumentStatus.determineFrom(dto.status);
        Serial serial = new Serial(dto.serial);

        return instrumentFactory.create(typecode, status, serial);
    }

    @Override
    public InstrumentDTO toDTO(Instrument element) {
        String status = element.getStatus().toString();
        String typecode = element.getTypecode().toString();
        String serial = element.getSerial().toString();

        return new InstrumentDTO(serial, typecode, status);
    }

}
