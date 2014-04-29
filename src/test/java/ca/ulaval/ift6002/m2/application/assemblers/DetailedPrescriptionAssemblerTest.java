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

import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.application.responses.DetailedPrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;

@RunWith(MockitoJUnitRunner.class)
public class DetailedPrescriptionAssemblerTest {

    private static final int AN_AUTHORIZED_RENEWALS = 1;
    private static final int A_REMAINING_RENEWALS = 1;
    private static final Practitioner A_PRACTITIONER = new Practitioner("A random name");
    private static final String A_DATE_AS_STRING = "2014-01-03T12:00:00";
    private static final Date A_DATE = new Date();
    private static final int A_RENEWALS = 1;
    private static final Din A_DIN = new Din("A valid din");
    private static final String A_BRAND_NAME = "A random brand name";
    private static final Drug A_DRUG = mock(Drug.class);

    private static final Prescription PRESCRIPTION = mock(Prescription.class);
    private static final Collection<Prescription> PRESCRIPTIONS = Arrays.asList(PRESCRIPTION);
    private static final Consumption CONSUMPTION = mock(Consumption.class);
    private static final ConsumptionResponse[] CONSUMPTIONS = { mock(ConsumptionResponse.class) };

    private static final DetailedPrescriptionResponse DETAILED_PRESCRIPTION_RESPONSE = new DetailedPrescriptionResponse(
            A_BRAND_NAME, A_PRACTITIONER.toString(), A_DATE_AS_STRING, A_REMAINING_RENEWALS, AN_AUTHORIZED_RENEWALS,
            CONSUMPTIONS, A_DIN.toString());

    private static final DetailedPrescriptionResponse[] DETAILED_PRESCRIPTION_RESPONSES = { DETAILED_PRESCRIPTION_RESPONSE };

    @Mock
    private ConsumptionAssembler consumptionAssembler;

    @Mock
    private DrugRepository drugRepository;

    @Mock
    private DateFormatter dateFormatter;

    @Mock
    private PrescriptionFactory prescriptionFactory;

    @InjectMocks
    private DetailedPrescriptionAssembler detailedPrescriptionAssembler;

    @Before
    public void givenDateFormatterReturns() {
        willReturn(A_DATE_AS_STRING).given(dateFormatter).dateToString(A_DATE);
    }

    private void setupDrugWithDin() {
        willReturn(A_BRAND_NAME).given(A_DRUG).getBrandName();
        willReturn(A_DIN).given(A_DRUG).getDin();
    }

    private void setupPrescription() {
        willReturn(A_DATE).given(PRESCRIPTION).getDate();
        willReturn(A_PRACTITIONER).given(PRESCRIPTION).getPractioner();
        willReturn(A_RENEWALS).given(PRESCRIPTION).getRenewals();
        willReturn(A_REMAINING_RENEWALS).given(PRESCRIPTION).countRemainingRenewals();
        willReturn(A_DRUG).given(PRESCRIPTION).getDrug();
        willReturn(Arrays.asList(CONSUMPTION)).given(PRESCRIPTION).getConsumptions();
        willReturn(CONSUMPTIONS).given(consumptionAssembler).toResponses(Arrays.asList(CONSUMPTION));
    }

    @Test
    public void givenPrescriptionWhenConvertToDetailedResponseShouldReturnGivenDetailedResponse() {
        setupDrugWithDin();
        setupPrescription();

        DetailedPrescriptionResponse responseBuilt = detailedPrescriptionAssembler.toDetailedResponse(PRESCRIPTION);

        assertDetailedResponseEquals(DETAILED_PRESCRIPTION_RESPONSE, responseBuilt);
    }

    @Test
    public void givenCollectionOfPrescriptionsWhenToDetailedResponsesShouldConvertToCollectionOfDetailedPrescriptionResponse() {
        setupDrugWithDin();
        setupPrescription();

        DetailedPrescriptionResponse[] prescriptionsResponseBuilt = detailedPrescriptionAssembler
                .toDetailedResponses(PRESCRIPTIONS);

        assertDetailedResponsesEquals(DETAILED_PRESCRIPTION_RESPONSES, prescriptionsResponseBuilt);
    }

    private void assertDetailedResponsesEquals(DetailedPrescriptionResponse[] expected,
            DetailedPrescriptionResponse[] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertDetailedResponseEquals(expected[i], actual[i]);
        }
    }

    private void assertDetailedResponseEquals(DetailedPrescriptionResponse expectedResponse,
            DetailedPrescriptionResponse actualResponse) {
        assertArrayEquals(expectedResponse.consumptions, actualResponse.consumptions);
        assertEquals(expectedResponse.din, actualResponse.din);
    }
}
