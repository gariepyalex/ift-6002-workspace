package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;
import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.ConsumptionFactory;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;

@RunWith(MockitoJUnitRunner.class)
public class ConsumptionAssemblerTest {

    private static final Pharmacy PHARMACY = new Pharmacy("pharmacy");
    private static final int COUNT = 1;
    private static final Date DATE = new Date();
    private static final String DATE_AS_STRING = DATE.toString();
    private static final ConsumptionResponse CONSUMPTION_RESPONSE = new ConsumptionResponse(DATE_AS_STRING,
            PHARMACY.toString(), COUNT);
    private static final ConsumptionRequest CONSUMPTION_REQUEST = new ConsumptionRequest(DATE_AS_STRING,
            PHARMACY.toString(), COUNT);

    @Mock
    private Consumption consumption;

    @Mock
    private ConsumptionFactory consumptionFactory;

    @Mock
    private DateFormatter dateFormatter;

    @InjectMocks
    private ConsumptionAssembler consumptionAssembler;

    @Before
    public void setupDateFormatter() {
        willReturn(DATE_AS_STRING).given(dateFormatter).dateToString(DATE);
    }

    @Before
    public void setupConsumption() {
        willReturn(COUNT).given(consumption).getCount();
        willReturn(PHARMACY).given(consumption).getPharmacy();
        willReturn(DATE).given(consumption).getDate();
    }

    @Test
    public void givenConsumptionWhenConvertToResponseShouldReturnGivenConsumptionResponse() {
        ConsumptionResponse responseBuilt = consumptionAssembler.toResponse(consumption);
        assertConsumptionResponseEquals(CONSUMPTION_RESPONSE, responseBuilt);
    }

    @Test
    public void givenConsumptionRequestWhenConvertToConsumptionShouldCallConsumptionFactoryCreate() {
        consumptionAssembler.fromRequest(CONSUMPTION_REQUEST);
        verify(consumptionFactory).create(any(Date.class), any(Pharmacy.class), anyInt());
    }

    private void assertConsumptionResponseEquals(ConsumptionResponse consumptionResponse,
            ConsumptionResponse responseBuilt) {
        assertEquals(consumptionResponse.consumptions, responseBuilt.consumptions);
        assertEquals(consumptionResponse.date, responseBuilt.date);
        assertEquals(consumptionResponse.pharmacy, responseBuilt.pharmacy);
    }
}
