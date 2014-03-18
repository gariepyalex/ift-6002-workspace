package ca.ulaval.ift6002.m2.factory.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void givenInstrumentHibernateFactoryShouldReturnInstrumentWhenCreate() {
        Instrument instrument = instrumentHibernateFactory.create(A_TYPECODE, AN_INSTRUMENT_STATUS, A_SERIAL);
        assertEquals(instrument.getClass(), InstrumentHibernate.class);
    }

    @Test
    public void givenInstrumentHibernateFactoryShouldReturnInstrumentWhenCreateWithNoSerial() {
        Instrument instrument = instrumentHibernateFactory.create(A_TYPECODE, AN_INSTRUMENT_STATUS);
        assertEquals(instrument.getClass(), InstrumentHibernate.class);
    }

    @Test
    public void givenInstrumentHibernateFactoryShouldReturnAnonymousInstrumentWhenCreateWithNoSerial() {
        Instrument instrument = instrumentHibernateFactory.create(A_TYPECODE, AN_INSTRUMENT_STATUS);
        assertTrue(instrument.isAnonymous());
    }

}
