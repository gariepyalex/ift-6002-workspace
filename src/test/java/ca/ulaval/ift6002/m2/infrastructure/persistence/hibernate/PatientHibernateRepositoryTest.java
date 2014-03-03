package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
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
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.PatientDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PrescriptionDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

@RunWith(MockitoJUnitRunner.class)
public class PatientHibernateRepositoryTest {

    private static final int UNEXISTING_PATIENT_ID = -1;

    private static final int PATIENT_ID = 12345;

    private static final Collection<PrescriptionDTO> prescriptionDTOs = new ArrayList<PrescriptionDTO>();
    private static final Collection<Prescription> prescriptions = new ArrayList<Prescription>();

    private static final PatientDTO PATIENT_DTO = new PatientDTO(12345, prescriptionDTOs);
    private static final Patient PATIENT = new Patient(PATIENT_ID, prescriptions);

    @Mock
    private EntityManagerProvider entityManagerProvider;

    @Mock
    private EntityManager entityManager;

    @Mock
    private PatientDTOAssembler patientAssembler;

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
    public void whenStorePatientShouldCallPatientDTOAssemblerToDTO() {
        patientRepository.store(PATIENT);

        verify(patientAssembler).toDTO(PATIENT);
    }

    @Test
    public void whenStorePatientShouldCallEntityManagerMerge() {
        patientRepository.store(PATIENT);

        verify(entityManager).merge(any(PatientDTO.class));
    }

    @Test
    public void whenGettingPatientShouldCallPatientDTOAssemblerFromDTO() {
        willReturn(PATIENT_DTO).given(entityManager).find(PatientDTO.class, PATIENT_ID);
        patientRepository.get(PATIENT_ID);
        verify(patientAssembler).fromDTO(PATIENT_DTO);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGettingUnknownPatientShouldThowException() {
        willReturn(null).given(entityManager).find(PatientDTO.class, UNEXISTING_PATIENT_ID);

        patientRepository.get(UNEXISTING_PATIENT_ID);
    }

    @Test
    public void whenGettingPatientShouldCallEntityManagerFind() {
        willReturn(PATIENT_DTO).given(entityManager).find(PatientDTO.class, PATIENT_ID);
        patientRepository.get(PATIENT_ID);
        verify(entityManager).find(PatientDTO.class, PATIENT_ID);
    }

}
