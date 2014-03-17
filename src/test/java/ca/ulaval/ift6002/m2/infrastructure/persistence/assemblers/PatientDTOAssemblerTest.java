package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PrescriptionDTO;

@RunWith(MockitoJUnitRunner.class)
public class PatientDTOAssemblerTest {

    private static final int PATIENT_NUMBER = 12345;
    private static final Collection<PrescriptionDTO> PRESCRIPTION_DTOS = new ArrayList<PrescriptionDTO>();
    private static final Collection<Prescription> PRESCRIPTIONS = new ArrayList<Prescription>();
    private static final PatientDTO PATIENT_DTO = new PatientDTO(PATIENT_NUMBER, PRESCRIPTION_DTOS);
    private static final Patient PATIENT = new Patient(PATIENT_NUMBER);

    @Mock
    private PrescriptionDTOAssembler prescriptionAssembler;

    @InjectMocks
    private PatientDTOAssembler patientAssembler;

    @Test
    public void givenDTOWhenAssemblingPatientShouldReturnCorrespondingPatient() {
        Patient patientBuilt = patientAssembler.fromDTO(PATIENT_DTO);

        assertEquals(PATIENT, patientBuilt);
    }

    @Test
    public void givenPatientWhenAssemblingToDTOShouldReturnCorrespondingPatientDTO() {
        PatientDTO dtoBuilt = patientAssembler.toDTO(PATIENT);

        assertPatientDTOEquals(PATIENT_DTO, dtoBuilt);
    }

    @Test
    public void givenPatientWhenAssemblingToDTOShouldCallPrescriptionDTOAssembler() {
        patientAssembler.toDTO(PATIENT);

        verify(prescriptionAssembler).toDTOs(PRESCRIPTIONS);
    }

    private void assertPatientDTOEquals(PatientDTO expected, PatientDTO actual) {
        assertEquals(expected.number, actual.number);
    }
}
