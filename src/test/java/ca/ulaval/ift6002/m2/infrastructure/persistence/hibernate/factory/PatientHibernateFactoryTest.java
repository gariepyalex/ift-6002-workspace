package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.PatientHibernate;

public class PatientHibernateFactoryTest {

    private static final int PATIENT_NUMBER = 1;

    private PatientHibernateFactory patientHibernateFactory;

    @Before
    public void setUp() {
        patientHibernateFactory = new PatientHibernateFactory();
    }

    @Test
    public void whenCreatingPatientShouldReturnPatientHibernate() {
        Patient patient = patientHibernateFactory.create(PATIENT_NUMBER);
        assertEquals(PatientHibernate.class, patient.getClass());
    }
}
