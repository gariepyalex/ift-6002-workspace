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

import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.operation.patient.Patient;
import ca.ulaval.ift6002.m2.domain.operation.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    private static final String EXISTING_PATIENT_ID_AS_STRING = "1212";
    private static final int EXISTING_PATIENT_ID_AS_INT = 1212;

    @Mock
    private PrescriptionResponse prescriptionDTO;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PrescriptionResponseAssembler prescriptionAssembler;

    @InjectMocks
    private PatientService patientService;

    private Prescription prescription;

    private Patient patient;

    @Before
    public void setUp() {
        patient = new Patient(EXISTING_PATIENT_ID_AS_INT);

        willReturn(patient).given(patientRepository).get(EXISTING_PATIENT_ID_AS_INT);
        willReturn(prescription).given(prescriptionAssembler).fromResponse(any(PrescriptionResponse.class));
    }

    @Test
    public void givenPatientServiceWhenSavingDTOShouldSaveInRepository() {
        patientService.savePrescription(EXISTING_PATIENT_ID_AS_STRING, prescriptionDTO);

        verify(patientRepository).get(EXISTING_PATIENT_ID_AS_INT);
        verify(patientRepository).store(any(Patient.class));
    }

}
