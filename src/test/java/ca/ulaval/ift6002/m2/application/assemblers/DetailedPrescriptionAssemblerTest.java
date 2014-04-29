package ca.ulaval.ift6002.m2.application.assemblers;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Date;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.application.responses.DetailedPrescriptionResponse;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;

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
    private static final Consumption CONSUMPTION = mock(Consumption.class);
    private static final ConsumptionResponse[] CONSUMPTIONS = { mock(ConsumptionResponse.class) };

    private static final DetailedPrescriptionResponse DETAILED_PRESCRIPTION_RESPONSE = new DetailedPrescriptionResponse(
            A_BRAND_NAME, A_PRACTITIONER.toString(), A_DATE_AS_STRING, A_REMAINING_RENEWALS, AN_AUTHORIZED_RENEWALS,
            CONSUMPTIONS, A_DIN.toString());

    private static final PrescriptionResponse[] DETAILED_PRESCRIPTION_RESPONSES = { DETAILED_PRESCRIPTION_RESPONSE };

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

    private void setupPrescription() {
        willReturn(A_DATE).given(PRESCRIPTION).getDate();
        willReturn(A_PRACTITIONER).given(PRESCRIPTION).getPractioner();
        willReturn(A_RENEWALS).given(PRESCRIPTION).getRenewals();
        willReturn(A_REMAINING_RENEWALS).given(PRESCRIPTION).countRemainingRenewals();
        willReturn(A_DRUG).given(PRESCRIPTION).getDrug();
        willReturn(Arrays.asList(CONSUMPTION)).given(PRESCRIPTION).getConsumptions();
    }
}
