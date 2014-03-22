package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.PatientHibernate;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

@RunWith(MockitoJUnitRunner.class)
public class PatientHibernateRepositoryTest {

    private static final int PATIENT_ID = 12345;
    private static final int UNEXISTING_PATIENT_ID = -1;

    @Mock
    private PatientHibernate patient;

    @Mock
    private EntityManagerProvider entityManagerProvider;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private PatientHibernateRepository patientRepository;

    @Before
    public void setUp() {
        willReturn(entityManager).given(entityManagerProvider).getEntityManager();
    }

    @Test
    public void givenExistingPatientIdWhenGettingPatientShouldReturnAPatient() {
        willReturn(patient).given(entityManager).find(PatientHibernate.class, PATIENT_ID);
        Patient patientFound = patientRepository.get(PATIENT_ID);
        assertEquals(patient, patientFound);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenUnexistingPatientIdWhenGettingPatientShouldThrowException() {
        willReturn(null).given(entityManager).find(PatientHibernate.class, UNEXISTING_PATIENT_ID);

        patientRepository.get(UNEXISTING_PATIENT_ID);
    }

    @Test
    public void whenStorePatientNotContainInEntityManagerShouldCallEntityManagerPersist() {
        willReturn(false).given(entityManager).contains(any(PatientHibernate.class));
        patientRepository.store(patient);
        verify(entityManager).persist(any(PatientHibernate.class));
    }

    @Test
    public void whenStorePatientContainInEntityManagerShouldNotCallEntityManagerPersist() {
        willReturn(true).given(entityManager).contains(any(PatientHibernate.class));
        patientRepository.store(patient);
        verify(entityManager, never()).persist(any(PatientHibernate.class));
    }
}
