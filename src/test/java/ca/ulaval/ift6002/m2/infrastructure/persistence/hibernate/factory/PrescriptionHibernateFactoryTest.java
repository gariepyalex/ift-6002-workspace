package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.DrugHibernate;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.PrescriptionHibernate;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionHibernateFactoryTest {
    private static final Practitioner PRACTITIONER = new Practitioner("a practitionner");
    private static final Date DATE = new Date();
    private static final int RENEWALS = 1;

    @Mock
    private DrugHibernate drug;

    @Mock
    private DrugHibernateFactory drugFactory;

    private PrescriptionHibernateFactory prescriptionHibernateFactory;

    @Before
    public void setUp() {
        prescriptionHibernateFactory = new PrescriptionHibernateFactory();
        willReturn(true).given(drug).hasDin();

        FactoryLocator factoryLocator = new FactoryLocator();
        factoryLocator.register(DrugFactory.class, drugFactory);
        FactoryLocator.load(factoryLocator);
    }

    @Test
    public void givenPrescriptionHibernateFactoryWhenCreateShouldReturnPrescriptionHibernate() {
        Prescription prescription = prescriptionHibernateFactory.create(PRACTITIONER, DATE, RENEWALS, drug);
        assertEquals(PrescriptionHibernate.class, prescription.getClass());
    }
}
