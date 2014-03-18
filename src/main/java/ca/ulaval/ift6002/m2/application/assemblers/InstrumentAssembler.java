package ca.ulaval.ift6002.m2.application.assemblers;

import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;
import ca.ulaval.ift6002.m2.factory.hibernate.InstrumentHibernateFactory;

public class InstrumentAssembler {

    private final InstrumentFactory instrumentFactory;

    public InstrumentAssembler() {
        // TODO CHANGE THIS WITH LOCATOR
        instrumentFactory = new InstrumentHibernateFactory();
    }

    public InstrumentAssembler(InstrumentFactory instrumentFactory) {
        this.instrumentFactory = instrumentFactory;
    }

    public Instrument fromRequest(InstrumentRequest request) {
        InstrumentStatus status = InstrumentStatus.determineFrom(request.status);
        Typecode typecode = new Typecode(request.typecode);
        Serial serial = new Serial(request.serial);

        return instrumentFactory.create(typecode, status, serial);
    }
}
