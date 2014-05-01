package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.PatientHibernate;

@RunWith(MockitoJUnitRunner.class)
public class PatientHibernateRepositoryTest {

    private static final int PATIENT_ID = 12345;

    @Mock
    private PatientHibernate patient;

    @Mock
    private HibernateRepository<PatientHibernate> hibernateRepository;

    private PatientHibernateRepository patientRepository;

    @Before
    public void setUp() {
        patientRepository = new PatientHibernateRepository(hibernateRepository);
    }

    @Test
    public void whenGettingPatientShouldVerifyHibernateRepository() {
        patientRepository.get(PATIENT_ID);

        verify(hibernateRepository).find(PATIENT_ID);
    }

    @Test
    public void whenStoringPatientShouldVerifyHibernateRepository() {
        patientRepository.store(patient);

        verify(hibernateRepository).storeElement(any(PatientHibernate.class));
    }
}
