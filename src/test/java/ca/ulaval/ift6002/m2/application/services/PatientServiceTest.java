package ca.ulaval.ift6002.m2.application.services;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

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
import ca.ulaval.ift6002.m2.domain.prescription.InvalidPrescriptionException;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    private static final String AN_EMPTY_DIN = "";
    private static final String AN_EMPTY_NAME = "";
    private static final String A_VALID_NAME = "advil";
    private static final String A_VALID_DIN = "1010122";
    private static final String A_DATE = "12-12-12T12:12:12";
    private static final String A_PRACTITIONER = "102032";
    private static final String A_PATIENT_ID = "1212";
    private static final Integer A_NULL_RENEWALS = null;
    private static final Integer AN_INVALID_RENWALS = -1;

    @Mock
    PatientRepository repository;
    @Mock
    PrescriptionDTOAssembler assembler;
    @Mock
    Prescription aPrescription;
    @Mock
    Patient aPatient;
    @InjectMocks
    PatientService aPatientService;

    @Before
    public void givenRepositoryReturns() {
        willReturn(aPatient).given(repository).getPatientById(Integer.valueOf(A_PATIENT_ID));
    }
    
    @Before
    public void givenAssemblerReturns() {
        willReturn(aPrescription).given(assembler).fromDTO(any(PrescriptionDTO.class));
    }
    
    @Test
    public void givenAPatientServiceWhenSavingADtoShouldSaveInRepository() {
       PrescriptionDTO dto = new PrescriptionDTO(A_PRACTITIONER, A_DATE, 10, AN_EMPTY_DIN, A_VALID_NAME);
       aPatientService.savePrescription(A_PATIENT_ID, dto);
       verify(repository).savePrescription(aPatient, aPrescription);
    }

    @Test(expected = InvalidPrescriptionException.class)
    public void givenAPatientServiceWhenSavingPrescriptionWithoutDinOrNameShouldThrow() {
        PrescriptionDTO dto = new PrescriptionDTO(A_PRACTITIONER, A_DATE, 0, AN_EMPTY_DIN, AN_EMPTY_NAME);
        aPatientService.savePrescription(A_PATIENT_ID, dto);
    }
    
    @Test(expected = InvalidPrescriptionException.class)
    public void givenAPatientServiceWhenSavingPrescriptionWithBothNameAndDinShouldThrow() {
        PrescriptionDTO dto = new PrescriptionDTO(A_PRACTITIONER, A_DATE, 12, A_VALID_DIN, A_VALID_NAME);
        aPatientService.savePrescription(A_PATIENT_ID, dto);
    }

    @Test(expected = InvalidPrescriptionException.class)
    public void givenAPatientServiceWhenSavingPrescriptionWithNullRenwalsShouldThrow() {
        PrescriptionDTO dto = new PrescriptionDTO(A_PRACTITIONER, A_DATE, A_NULL_RENEWALS, AN_EMPTY_DIN, A_VALID_NAME);
        aPatientService.savePrescription(A_PATIENT_ID, dto);
    }

    @Test(expected = InvalidPrescriptionException.class)
    public void givenAPatientServiceWhenSavingPrescriptionWithInvalidRenwalsShouldThrow() {
        PrescriptionDTO dto = new PrescriptionDTO(A_PRACTITIONER, A_DATE, AN_INVALID_RENWALS, AN_EMPTY_DIN,
                A_VALID_NAME);
        aPatientService.savePrescription(A_PATIENT_ID, dto);
    }
}
