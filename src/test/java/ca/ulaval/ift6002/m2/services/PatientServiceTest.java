package ca.ulaval.ift6002.m2.services;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.assemblers.DetailedPrescriptionAssembler;
import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    private static final String PATIENT_ID_AS_STRING = "1212";

    private static final int PATIENT_ID_AS_INT = 1212;

    private static final Prescription PRESCRIPTION = mock(Prescription.class);
    private static final PrescriptionRequest RESPONSE = new PrescriptionRequest("", "", 0, "", "");
    private static final Collection<Prescription> PRESCRIPTIONS = new ArrayList<Prescription>();

    @Mock
    private Patient patient;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DetailedPrescriptionAssembler detailedPrescriptionAssembler;

    @InjectMocks
    private PatientService patientService;

    @Before
    public void setUp() {
        willReturn(PRESCRIPTION).given(detailedPrescriptionAssembler).fromRequest(RESPONSE);
        willReturn(patient).given(patientRepository).get(PATIENT_ID_AS_INT);
        willReturn(PRESCRIPTIONS).given(patient).getPrescriptions();
    }

    @Test
    public void whenSavePrescriptionToPatientShouldConvertResponseToPrescription() {
        patientService.savePrescription(PATIENT_ID_AS_STRING, RESPONSE);

        verify(detailedPrescriptionAssembler).fromRequest(RESPONSE);
    }

    @Test
    public void whenSavePrescriptionToPatientShouldRetrievePatientFromRepository() {
        patientService.savePrescription(PATIENT_ID_AS_STRING, RESPONSE);

        verify(patientRepository).get(PATIENT_ID_AS_INT);
    }

    @Test
    public void whenSavePrescriptionToPatientShouldStoreInRepository() {
        patientService.savePrescription(PATIENT_ID_AS_STRING, RESPONSE);

        verify(patientRepository).store(patient);
    }

    @Test
    public void whenLoadPrescriptionShouldLoadPatientFromRepository() {
        patientService.getPrescriptions(PATIENT_ID_AS_STRING);

        verify(patientRepository).get(PATIENT_ID_AS_INT);
    }

    @Test
    public void whenLoadPrescriptionShouldConvertPrescriptionsToResponse() {
        patientService.getPrescriptions(PATIENT_ID_AS_STRING);

        verify(detailedPrescriptionAssembler).toResponses(PRESCRIPTIONS);
    }

    @Test
    public void whenLoadDetailedPrescriptionShouldLoadPatientFromRepository() {
        patientService.getDetailedPrescriptions(PATIENT_ID_AS_STRING);

        verify(patientRepository).get(PATIENT_ID_AS_INT);
    }

    @Test
    public void whenLoadDetailedPrescriptionShouldConvertPrescriptionsToDetailedResponse() {
        patientService.getDetailedPrescriptions(PATIENT_ID_AS_STRING);

        verify(detailedPrescriptionAssembler).toDetailedResponses(PRESCRIPTIONS);
    }
}
