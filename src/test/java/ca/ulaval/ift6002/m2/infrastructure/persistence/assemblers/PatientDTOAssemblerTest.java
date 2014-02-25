package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;

public class PatientDTOAssemblerTest {

    private static final int PATIENT_NUMBER = 12345;

    private static final PatientDTO PATIENT_DTO = new PatientDTO(PATIENT_NUMBER);
    private static final Patient PATIENT = new Patient(PATIENT_NUMBER);

    private PatientDTOAssembler patientAssembler;

    @Before
    public void setUp() {
        patientAssembler = new PatientDTOAssembler();
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
