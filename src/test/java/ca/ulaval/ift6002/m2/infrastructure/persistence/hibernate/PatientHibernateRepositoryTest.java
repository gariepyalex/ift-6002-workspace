package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.PatientDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;

@RunWith(MockitoJUnitRunner.class)
public class PatientHibernateRepositoryTest {

    private static final int UNEXISTING_PATIENT_ID = -1;
    private static final int PATIENT_ID = 12345;

    private static final PatientDTO PATIENT_DTO = new PatientDTO(12345);
    private static final Patient PATIENT = new Patient(PATIENT_ID);

    private PatientRepository patientRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private PatientDTOAssembler patientAssembler;

    @Before
    public void setUp() {
        patientRepository = new PatientHibernateRepository(entityManager, patientAssembler);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyRepositoryWhenGetPatientShouldReturnException() {
        willReturn(null).given(entityManager).find(PatientDTO.class, UNEXISTING_PATIENT_ID);

        patientRepository.get(UNEXISTING_PATIENT_ID);
    }

    @Test
    public void givenPatientWhenGetPatientShouldReturnCorrespondingPatient() {
        willReturn(PATIENT_DTO).given(entityManager).find(PatientDTO.class, PATIENT_ID);
        willReturn(PATIENT).given(patientAssembler).fromDTO(PATIENT_DTO);

        Patient patientFound = patientRepository.get(PATIENT_ID);

        assertEquals(PATIENT, patientFound);
    }

    @Test
    public void givenPatientWhenStoringShouldPersist() {
        willReturn(PATIENT_DTO).given(patientAssembler).toDTO(PATIENT);
        patientRepository.store(PATIENT);
        verify(entityManager, times(1)).persist(PATIENT_DTO);
    }
}
