package ca.ulaval.ift6002.m2.application.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionDTO;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.InvalidPrescriptionException;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionRepository;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionServiceTest {

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
    private PrescriptionRepository repository;
    @Mock
    private PrescriptionDTOAssembler assembler;
    @Mock
    private Prescription aPrescription;
    @Mock
    private Patient aPatient;
    @InjectMocks
    private PrescriptionService aPrescriptionService;

    //TODO The class PrescriptionService should not call new Patient; should use a repository. Not testable by now
//    @Before
//    public void givenAPrescriptionService() {
//        when(assembler.fromDTO(any(PrescriptionDTO.class))).thenReturn(aPrescription);
//    }
//
//    @Test
//    public void givenAPrescriptionServiceWhenSavingADtoShouldCallRepository() {
//       PrescriptionDTO dto = new PrescriptionDTO(A_PRACTITIONER, A_DATE, 10, AN_EMPTY_DIN, A_VALID_NAME);
//       aPrescriptionService.savePrescription(A_PATIENT_ID, dto);
//       verify(repository).save();
//    }

    @Test(expected = InvalidPrescriptionException.class)
    public void givenAPrescriptionServiceWhenSavingPrescriptionWithoutDinOrNameShouldThrow() {
        PrescriptionDTO dto = new PrescriptionDTO(A_PRACTITIONER, A_DATE, 0, AN_EMPTY_DIN, AN_EMPTY_NAME);
        aPrescriptionService.savePrescription(A_PATIENT_ID, dto);
    }
    
    @Test(expected = InvalidPrescriptionException.class)
    public void givenAPrescriptionServiceWhenSavingPrescriptionWithBothNameAndDinShouldThrow() {
        PrescriptionDTO dto = new PrescriptionDTO(A_PRACTITIONER, A_DATE, 12, A_VALID_DIN, A_VALID_NAME);
        aPrescriptionService.savePrescription(A_PATIENT_ID, dto);
    }

    @Test(expected = InvalidPrescriptionException.class)
    public void givenAPrescriptionServiceWhenSavingPrescriptionWithNullRenwalsShouldThrow() {
        PrescriptionDTO dto = new PrescriptionDTO(A_PRACTITIONER, A_DATE, A_NULL_RENEWALS, AN_EMPTY_DIN, A_VALID_NAME);
        aPrescriptionService.savePrescription(A_PATIENT_ID, dto);
    }

    @Test(expected = InvalidPrescriptionException.class)
    public void givenAPrescriptionServiceWhenSavingPrescriptionWithInvalidRenwalsShouldThrow() {
        PrescriptionDTO dto = new PrescriptionDTO(A_PRACTITIONER, A_DATE, AN_INVALID_RENWALS, AN_EMPTY_DIN,
                A_VALID_NAME);
        aPrescriptionService.savePrescription(A_PATIENT_ID, dto);
    }
}
