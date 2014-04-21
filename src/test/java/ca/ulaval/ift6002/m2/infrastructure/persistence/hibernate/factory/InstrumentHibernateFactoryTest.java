package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.InstrumentHibernate;

public class InstrumentHibernateFactoryTest {

    private static final Typecode A_TYPECODE = new Typecode("111111");
    private static final InstrumentStatus AN_INSTRUMENT_STATUS = InstrumentStatus.UNUSED;
    private static final Serial A_SERIAL = new Serial("12345");

    private InstrumentHibernateFactory instrumentHibernateFactory;

    @Before
    public void setUp() {
        instrumentHibernateFactory = new InstrumentHibernateFactory();
    }

    @Test
    public void givenInstrumentHibernateFactoryWhenCreateShouldReturnInstrumentHibernate() {
        Instrument instrument = instrumentHibernateFactory.create(A_TYPECODE, AN_INSTRUMENT_STATUS, A_SERIAL);
        assertEquals(InstrumentHibernate.class, instrument.getClass());
    }

}
