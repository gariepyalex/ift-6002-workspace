package projectH.application.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import projectH.application.responses.PrescriptionDTO;
import projectH.domain.date.DateFormatter;
import projectH.domain.drug.Din;
import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;
import projectH.domain.prescription.Practitioner;
import projectH.domain.prescription.Prescription;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionDTOAssemblerTest {

    private final static Practitioner PRACTITIONER = new Practitioner("A random name");
    private final static String DATE_AS_STRING = "2014-01-03T12:00:00";
    private final static Date DATE = new Date();
    private final static int RENEWALS = 1;

    private final static Din DIN = new Din("A random din");
    private final static String BRAND_NAME = "A random brand name";
    private final static String DESCRIPTOR = "A random descriptor";
    private final static Drug DRUG = new Drug(DIN, BRAND_NAME, DESCRIPTOR);

    private final static Prescription PRESCRIPTION = new Prescription(PRACTITIONER, DATE, RENEWALS, DRUG);

    private final static PrescriptionDTO PRESCRIPTION_DTO = new PrescriptionDTO(PRACTITIONER.toString(),
            DATE_AS_STRING, RENEWALS, DIN.toString(), BRAND_NAME);

    @Mock
    private DrugRepository drugRepository;

    @Mock
    private DateFormatter dateFormatter;

    @InjectMocks
    private PrescriptionDTOAssembler prescriptionAssembler;

    @Test
    public void givenPrescriptionWhenConvertToDTOShouldReturnGivenPrescriptionDTO() {
        willReturn(DATE_AS_STRING).given(dateFormatter).dateToString(DATE);

        PrescriptionDTO dtoBuilt = prescriptionAssembler.toDTO(PRESCRIPTION);

        assertPrescriptionDTOEquals(PRESCRIPTION_DTO, dtoBuilt);
    }

    @Test
    public void givenPrescriptionDTOWhenConvertToPrescriptionShouldReturnGivenPrescription() {
        willReturn(DRUG).given(drugRepository).get(DIN);
        willReturn(DATE).given(dateFormatter).parse(DATE_AS_STRING);

        Prescription prescriptionBuilt = prescriptionAssembler.fromDTO(PRESCRIPTION_DTO);

        assertEquals(PRESCRIPTION, prescriptionBuilt);
    }

    private void assertPrescriptionDTOEquals(PrescriptionDTO expected, PrescriptionDTO actual) {
        assertEquals(expected.practitioner, actual.practitioner);
        assertEquals(expected.date, actual.date);
        assertEquals(expected.renewals, actual.renewals);
        assertEquals(expected.name, actual.name);
        assertEquals(expected.din, actual.din);
    }
}
