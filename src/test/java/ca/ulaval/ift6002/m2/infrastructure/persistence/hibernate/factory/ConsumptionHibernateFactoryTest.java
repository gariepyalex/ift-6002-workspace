package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.ConsumptionHibernate;

public class ConsumptionHibernateFactoryTest {

    private static final Date DATE = new Date();
    private static final Pharmacy PHARMACY = new Pharmacy("description");
    private static final int COUNT = 1;

    private ConsumptionHibernateFactory consumptionHibernateFactory;

    @Before
    public void setUp() {
        consumptionHibernateFactory = new ConsumptionHibernateFactory();
    }

    @Test
    public void whenCreatingConsumptionShouldReturnConsumptionHibernate() {
        Consumption consumption = consumptionHibernateFactory.create(DATE, PHARMACY, COUNT);
        assertEquals(ConsumptionHibernate.class, consumption.getClass());
    }
}
