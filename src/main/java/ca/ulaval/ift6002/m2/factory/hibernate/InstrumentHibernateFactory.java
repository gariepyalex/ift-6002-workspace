package ca.ulaval.ift6002.m2.factory.hibernate;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.InstrumentHibernate;

public class InstrumentHibernateFactory implements InstrumentFactory {

    @Override
    public Instrument create(Typecode typecode, InstrumentStatus status, Serial serialNumber) {
        return new InstrumentHibernate(typecode, status, serialNumber);
    }

    @Override
    public Instrument create(Typecode typecode, InstrumentStatus status) {
        return create(typecode, status, new Serial(""));
    }

}
