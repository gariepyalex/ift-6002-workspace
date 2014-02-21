package ca.ulaval.ift6002.m2.services;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionDTO;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    private static final String EMPTY_DIN = "";

    private static final String VALID_NAME = "advil";

    private static final Integer VALID_RENEWALS = 15;

    private static final String DATE = "12-12-12T12:12:12";
    private static final String PRACTITIONER = "102032";
    private static final String EXISTING_PATIENT_ID = "1212";

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PrescriptionDTOAssembler prescriptionAssembler;

    // TODO see if we really mock these value objects
    @Mock
    private Prescription prescription;

    @Mock
    private Patient patient;

    @InjectMocks
    private PatientService patientService;

    @Before
    public void givenRepositoryReturns() {
        willReturn(patient).given(patientRepository).get(Integer.valueOf(EXISTING_PATIENT_ID));
    }

    @Before
    public void givenAssemblerReturns() {
        willReturn(prescription).given(prescriptionAssembler).fromDTO(any(PrescriptionDTO.class));
    }

    @Test
    public void givenPatientServiceWhenSavingDTOShouldSaveInRepository() {
        PrescriptionDTO dto = new PrescriptionDTO(PRACTITIONER, DATE, VALID_RENEWALS, EMPTY_DIN, VALID_NAME);

        patientService.savePrescription(EXISTING_PATIENT_ID, dto);

        verify(patientRepository).get(Integer.valueOf(EXISTING_PATIENT_ID));
        verify(patientRepository).savePrescription(patient, prescription);
    }

}
