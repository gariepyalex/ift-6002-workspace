package ca.ulaval.ift6002.m2.factory.hibernate;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;

public class InstrumentHibernateFactory implements InstrumentFactory {

    @Override
    public Instrument create(Typecode typecode, InstrumentStatus status, Serial serialNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Instrument create(Typecode typecode, InstrumentStatus status) {
        // TODO Auto-generated method stub
        return null;
    }

}
