package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;

//TODO Refactor this test to handle null in reponse / request
@RunWith(MockitoJUnitRunner.class)
public class PrescriptionAssemblerTest {

    private static final int AN_AUTHORIZED_RENEWALS = 1;
    private static final int A_REMAINING_RENEWALS = 1;
    private static final Practitioner A_PRACTITIONER = new Practitioner("A random name");
    private static final String A_DATE_AS_STRING = "2014-01-03T12:00:00";
    private static final Date A_DATE = new Date();
    private static final int A_RENEWALS = 1;

    private static final Din A_DIN = new Din("A valid din");
    private static final String A_BRAND_NAME = "A random brand name";;

    private static final Drug DRUG = mock(Drug.class);
    private static final Prescription PRESCRIPTION = mock(Prescription.class);

    private static final PrescriptionRequest PRESCRIPTION_REQUEST = new PrescriptionRequest(A_PRACTITIONER.toString(),
            A_DATE_AS_STRING, A_RENEWALS, A_DIN.toString(), A_BRAND_NAME);

    private static final PrescriptionRequest PRESCRIPTION_REQUEST_WITHOUT_DIN = new PrescriptionRequest(
            A_PRACTITIONER.toString(), A_DATE_AS_STRING, A_RENEWALS, "", A_BRAND_NAME);

    // TODO WHY NULL HERE ???
    private static final PrescriptionResponse PRESCRIPTION_RESPONSE = new PrescriptionResponse(A_BRAND_NAME,
            A_PRACTITIONER.toString(), A_DATE_AS_STRING, A_REMAINING_RENEWALS, AN_AUTHORIZED_RENEWALS,
            A_DIN.toString(), null);

    private static final Collection<Prescription> PRESCRIPTIONS = Arrays.asList(PRESCRIPTION);
    private static final PrescriptionResponse[] PRESCRIPTION_RESPONSES = { PRESCRIPTION_RESPONSE };

    @Mock
    private DrugRepository drugRepository;

    @Mock
    private DateFormatter dateFormatter;

    @Mock
    private PrescriptionFactory prescriptionFactory;

    @InjectMocks
    private PrescriptionAssembler prescriptionAssembler;

    @Before
    public void givenDateFormatterReturns() {
        willReturn(A_DATE_AS_STRING).given(dateFormatter).dateToString(A_DATE);
        willReturn(A_DATE).given(dateFormatter).parse(A_DATE_AS_STRING);
    }

    @Test
    public void givenPrescriptionWhenConvertToResponseShouldReturnGivenResponse() {
        setupDrugWithDin();
        setupPrescription();

        PrescriptionResponse responseBuilt = prescriptionAssembler.toResponse(PRESCRIPTION);

        assertResponseEquals(PRESCRIPTION_RESPONSE, responseBuilt);
    }

    @Test
    public void givenRequestWhenConvertToPrescriptionShouldCallPrescriptionFactoryCreate() {
        prescriptionAssembler.fromRequest(PRESCRIPTION_REQUEST);

        verify(prescriptionFactory).create(any(Practitioner.class), any(Date.class), anyInt(), any(Drug.class));
    }

    @Test
    public void givenRequestWithDinWhenConvertToPrescriptionShouldCallDrugRepositoryGet() {
        prescriptionAssembler.fromRequest(PRESCRIPTION_REQUEST);

        verify(drugRepository).get(any(Din.class));
    }

    @Test
    public void givenRequestWithDrugBrandNameWhenConvertToPrescriptionShouldCallDrugRepositoryGet() {
        prescriptionAssembler.fromRequest(PRESCRIPTION_REQUEST_WITHOUT_DIN);

        verify(drugRepository).get(anyString());
    }

    @Test
    public void givenCollectionOfPrescriptionsWhenToResponsesShouldConvertToCollectionOfPrescriptionResponse() {
        setupDrugWithDin();
        setupPrescription();

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

    private void setupDrugWithDin() {
        willReturn(A_BRAND_NAME).given(DRUG).getBrandName();
        willReturn(A_DIN).given(DRUG).getDin();
    }

    private void setupPrescription() {

        willReturn(A_DATE).given(PRESCRIPTION).getDate();
        willReturn(A_PRACTITIONER).given(PRESCRIPTION).getPractioner();
        willReturn(A_RENEWALS).given(PRESCRIPTION).getRenewals();
        willReturn(A_REMAINING_RENEWALS).given(PRESCRIPTION).remainingRenewals();
        willReturn(DRUG).given(PRESCRIPTION).getDrug();
    }
}
