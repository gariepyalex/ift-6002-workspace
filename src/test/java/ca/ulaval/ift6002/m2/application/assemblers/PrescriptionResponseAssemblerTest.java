package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionResponseAssemblerTest {

    private static final Practitioner A_PRACTITIONER = new Practitioner("A random name");
    private static final String A_DATE_AS_STRING = "2014-01-03T12:00:00";
    private static final Date A_DATE = new Date();
    private static final int A_RENEWALS = 1;

    private static final Din A_VALID_DIN = new Din("A valid din");
    private static final Din AN_EMPTY_DIN = new Din("");
    private static final String A_BRAND_NAME = "A random brand name";
    private static final String A_DESCRIPTOR = "A random descriptor";
    private static final String AN_EMPTY_DESCRIPTOR = "";
    private static final Drug A_COMPLETE_DRUG = new Drug(A_VALID_DIN, A_BRAND_NAME, A_DESCRIPTOR);
    private static final Drug A_DRUG_WITH_ONLY_A_NAME = new Drug(AN_EMPTY_DIN, A_BRAND_NAME, AN_EMPTY_DESCRIPTOR);

    private static final Prescription PRESCRIPTION = new Prescription(A_PRACTITIONER, A_DATE, A_RENEWALS,
            A_COMPLETE_DRUG);
    private static final Prescription PRESCRIPTION_WITH_INCOMPLETE_DRUG = new Prescription(A_PRACTITIONER, A_DATE,
            A_RENEWALS, A_DRUG_WITH_ONLY_A_NAME);

    private static final PrescriptionResponse PRESCRIPTION_RESPONSE = new PrescriptionResponse(
            A_PRACTITIONER.toString(), A_DATE_AS_STRING, A_RENEWALS, A_VALID_DIN.toString(), A_BRAND_NAME);

    @Mock
    private DrugRepository drugRepository;

    @Mock
    private DateFormatter dateFormatter;

    @InjectMocks
    private PrescriptionResponseAssembler prescriptionAssembler;

    @Before
    public void givenDrugRepositoryReturns() {
        willReturn(A_COMPLETE_DRUG).given(drugRepository).get(A_VALID_DIN);
        willReturn(A_DRUG_WITH_ONLY_A_NAME).given(drugRepository).get(A_BRAND_NAME);
    }

    @Before
    public void givenDateFormatterReturns() {
        willReturn(A_DATE_AS_STRING).given(dateFormatter).dateToString(A_DATE);
        willReturn(A_DATE).given(dateFormatter).parse(A_DATE_AS_STRING);
    }

    @Test
    public void givenPrescriptionWhenConvertToResponseShouldReturnGivenResponse() {
        PrescriptionResponse responseBuilt = prescriptionAssembler.toResponse(PRESCRIPTION);

        assertResponseEquals(PRESCRIPTION_RESPONSE, responseBuilt);
    }

    @Test
    public void givenResponseWhenConvertToPrescriptionShouldReturnGivenPrescription() {
        Prescription prescriptionBuilt = prescriptionAssembler.fromResponse(PRESCRIPTION_RESPONSE);

        assertEquals(PRESCRIPTION, prescriptionBuilt);
    }

    @Test
    public void givenResponseWithNameAndNoDinWhenConvertToPrescriptionShouldReturnGivenPrescription() {
        PrescriptionResponse response = new PrescriptionResponse(A_PRACTITIONER.toString(), A_DATE_AS_STRING,
                A_RENEWALS, AN_EMPTY_DIN.toString(), A_BRAND_NAME);
        Prescription returnedPrescription = prescriptionAssembler.fromResponse(response);
        assertEquals(PRESCRIPTION_WITH_INCOMPLETE_DRUG, returnedPrescription);
    }

    private void assertResponseEquals(PrescriptionResponse expected, PrescriptionResponse actual) {
        assertEquals(expected.practitioner, actual.practitioner);
        assertEquals(expected.date, actual.date);
        assertEquals(expected.renewals, actual.renewals);
        assertEquals(expected.name, actual.name);
        assertEquals(expected.din, actual.din);
    }
}
