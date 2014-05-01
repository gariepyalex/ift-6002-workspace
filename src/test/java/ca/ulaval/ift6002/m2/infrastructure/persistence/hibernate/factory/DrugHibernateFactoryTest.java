package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.DrugHibernate;

public class DrugHibernateFactoryTest {

    private static final Din DIN = new Din("a din");
    private static final String BRAND_NAME = "a brand name";
    private static final String DESCRIPTOR = "A descriptor";

    private DrugHibernateFactory drugHibernateFactory;

    @Before
    public void setUp() {
        drugHibernateFactory = new DrugHibernateFactory();
    }

    @Test
    public void whenCreatingDrugWithDinShouldReturnDrugHibernate() {
        Drug drug = drugHibernateFactory.create(DIN, BRAND_NAME, DESCRIPTOR);
        assertEquals(DrugHibernate.class, drug.getClass());
    }

    @Test
    public void whenCreatingDrugWithNameShouldReturnDrugHibernate() {
        Drug drug = drugHibernateFactory.create(BRAND_NAME);
        assertEquals(DrugHibernate.class, drug.getClass());
    }
}
