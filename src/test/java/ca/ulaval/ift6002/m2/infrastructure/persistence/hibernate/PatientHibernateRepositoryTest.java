package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
    private Patient patient;

    @Mock
    private PatientHibernate patientHibernate;

    @Mock
    private EntityManagerProvider entityManagerProvider;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private PatientHibernateRepository patientRepository;

    @Before
    public void setUp() {
        willReturn(entityManager).given(entityManagerProvider).getEntityManager();
        willReturn(transaction).given(entityManager).getTransaction();
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
    public void whenStorePatientShouldBeginTransaction() {
        patientRepository.store(patientHibernate);

        verify(transaction).begin();
    }

    @Test
    public void whenStorePatientShouldMergePatient() {
        patientRepository.store(patientHibernate);

        verify(entityManager).merge(patientHibernate);
    }

    @Test
    public void whenStorePatientShouldCommitTransaction() {
        patientRepository.store(patientHibernate);

        verify(transaction).commit();
    }

}
