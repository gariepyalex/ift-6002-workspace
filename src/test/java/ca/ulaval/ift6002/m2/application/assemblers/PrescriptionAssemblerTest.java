package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionAssemblerTest {

    private static final int AN_AUTHORIZED_RENEWALS = 1;
    private static final int A_REMAINING_RENEWALS = 1;
    private static final Practitioner A_PRACTITIONER = new Practitioner("A random name");
    private static final String A_DATE_AS_STRING = "2014-01-03T12:00:00";
    private static final Date A_DATE = new Date();
    private static final int A_RENEWALS = 1;

    private static final Din A_VALID_DIN = new Din("A valid din");
    private static final Din AN_EMPTY_DIN = new Din("");
    private static final String A_BRAND_NAME = "A random brand name";
    private static final String A_DESCRIPTOR = "A random descriptor";
    private static final String AN_EMPTY_DESCRIPTOR = "";
    private static final Drug A_COMPLETE_DRUG = mock(Drug.class);
    private static final Drug A_DRUG_WITH_ONLY_A_NAME = mock(Drug.class);

    private static final Prescription PRESCRIPTION = new Prescription(A_PRACTITIONER, A_DATE, A_RENEWALS,
            A_COMPLETE_DRUG);
    private static final Prescription PRESCRIPTION_WITH_INCOMPLETE_DRUG = new Prescription(A_PRACTITIONER, A_DATE,
            A_RENEWALS, A_DRUG_WITH_ONLY_A_NAME);

    private static final PrescriptionRequest PRESCRIPTION_REQUEST = new PrescriptionRequest(A_PRACTITIONER.toString(),
            A_DATE_AS_STRING, A_RENEWALS, A_VALID_DIN.toString(), A_BRAND_NAME);

    private static final PrescriptionResponse PRESCRIPTION_RESPONSE = new PrescriptionResponse(A_BRAND_NAME,
            A_PRACTITIONER.toString(), A_DATE_AS_STRING, A_REMAINING_RENEWALS, AN_AUTHORIZED_RENEWALS, null, null);

    private static final Collection<Prescription> PRESCRIPTIONS = Arrays.asList(PRESCRIPTION);
    private static final PrescriptionResponse[] PRESCRIPTION_RESPONSES = { PRESCRIPTION_RESPONSE };

    @Mock
    private DrugRepository drugRepository;

    @Mock
    private DateFormatter dateFormatter;

    @InjectMocks
    private PrescriptionAssembler prescriptionAssembler;

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
    public void givenRequestWhenConvertToPrescriptionShouldReturnGivenPrescription() {
        Prescription prescriptionBuilt = prescriptionAssembler.fromRequest(PRESCRIPTION_REQUEST);

        assertEquals(PRESCRIPTION, prescriptionBuilt);
    }

    @Test
    public void givenRequestWithNameAndNoDinWhenConvertToPrescriptionShouldReturnGivenPrescription() {
        PrescriptionRequest request = new PrescriptionRequest(A_PRACTITIONER.toString(), A_DATE_AS_STRING, A_RENEWALS,
                AN_EMPTY_DIN.toString(), A_BRAND_NAME);
        Prescription returnedPrescription = prescriptionAssembler.fromRequest(request);
        assertEquals(PRESCRIPTION_WITH_INCOMPLETE_DRUG, returnedPrescription);
    }

    @Test
    public void whenGivenCollectionOfPrescriptionsShouldConvertToCollectionOfPrescriptionResponse() {
        PrescriptionResponse[] prescriptionsResponseBuilt = prescriptionAssembler.toResponses(PRESCRIPTIONS);

        assertResponsesEquals(PRESCRIPTION_RESPONSES, prescriptionsResponseBuilt);
    }

    private void assertResponsesEquals(PrescriptionResponse[] expected, PrescriptionResponse[] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertResponseEquals(expected[i], actual[i]);
        }
    }

    private void assertResponseEquals(PrescriptionResponse expectedResponse, PrescriptionResponse responseBuilt) {
        assertEquals(expectedResponse.practitioner, responseBuilt.practitioner);
        assertEquals(expectedResponse.date, responseBuilt.date);
        assertEquals(expectedResponse.remainingRenewals, responseBuilt.remainingRenewals);
        assertEquals(expectedResponse.autorizedRenewals, responseBuilt.autorizedRenewals);
        assertEquals(expectedResponse.name, responseBuilt.name);
        assertEquals(expectedResponse.din, responseBuilt.din);
        assertArrayEquals(expectedResponse.consumptionResponses, responseBuilt.consumptionResponses);
    }
}
