package ca.ulaval.ift6002.m2.application.assemblers;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;
import ca.ulaval.ift6002.m2.factory.hibernate.InstrumentHibernateFactory;

public class InstrumentResponseAssembler {

    private final InstrumentFactory instrumentFactory;

    public InstrumentResponseAssembler() {
        // TODO CHANGE THIS WITH LOCATOR
        instrumentFactory = new InstrumentHibernateFactory();
    }

    public InstrumentResponseAssembler(InstrumentFactory instrumentFactory) {
        this.instrumentFactory = instrumentFactory;
    }

    public Instrument fromResponse(InstrumentResponse response) {
        InstrumentStatus status = InstrumentStatus.determineFrom(response.status);
        Typecode typecode = new Typecode(response.typecode);
        Serial serial = new Serial(response.serial);

        return instrumentFactory.create(typecode, status, serial);
    }
}
