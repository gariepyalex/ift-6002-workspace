package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PrescriptionDTO;

@RunWith(MockitoJUnitRunner.class)
public class PatientDTOAssemblerTest {

    private static final int PATIENT_NUMBER = 12345;
    private static final Collection<PrescriptionDTO> prescriptionDTOs = new ArrayList<PrescriptionDTO>();
    private static final PatientDTO PATIENT_DTO = new PatientDTO(PATIENT_NUMBER, prescriptionDTOs);
    private static final Patient PATIENT = new Patient(PATIENT_NUMBER);

    @Mock
    private PrescriptionDTOAssembler prescriptionAssembler;

    private PatientDTOAssembler patientAssembler;

    @Before
    public void setUp() {
        patientAssembler = new PatientDTOAssembler(prescriptionAssembler);
    }

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

    private void assertPatientDTOEquals(PatientDTO expected, PatientDTO actual) {
        assertEquals(expected.number, actual.number);
    }
}
