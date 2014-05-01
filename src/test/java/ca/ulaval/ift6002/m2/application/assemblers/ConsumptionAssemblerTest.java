package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    private static final Consumption CONSUMPTION = mock(Consumption.class);

    private static final List<Consumption> CONSUMPTIONS_LIST = Arrays.asList(CONSUMPTION);
    private static final ConsumptionResponse[] CONSUMPTION_RESPONSES = { CONSUMPTION_RESPONSE };

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
        willReturn(COUNT).given(CONSUMPTION).getCount();
        willReturn(PHARMACY).given(CONSUMPTION).getPharmacy();
        willReturn(DATE).given(CONSUMPTION).getDate();
    }

    @Test
    public void givenConsumptionWhenConvertToResponseShouldReturnGivenConsumptionResponse() {
        ConsumptionResponse responseBuilt = consumptionAssembler.toResponse(CONSUMPTION);
        assertConsumptionResponseEquals(CONSUMPTION_RESPONSE, responseBuilt);
    }

    @Test
    public void givenConsumptionListWhenConvertToResponsesShouldReturnGivenConsumptionResponseArray() {
        ConsumptionResponse[] responsesBuilt = consumptionAssembler.toResponses(CONSUMPTIONS_LIST);
        assertConsumptionResponsesEquals(CONSUMPTION_RESPONSES, responsesBuilt);
    }

    @Test
    public void givenConsumptionRequestWhenConvertToConsumptionShouldCallConsumptionFactoryCreate() {
        consumptionAssembler.fromRequest(CONSUMPTION_REQUEST);
        verify(consumptionFactory).create(any(Date.class), any(Pharmacy.class), anyInt());
    }

    private void assertConsumptionResponsesEquals(ConsumptionResponse[] expected, ConsumptionResponse[] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertConsumptionResponseEquals(expected[i], actual[i]);
        }
    }

    private void assertConsumptionResponseEquals(ConsumptionResponse consumptionResponse,
            ConsumptionResponse responseBuilt) {
        assertEquals(consumptionResponse.consumptions, responseBuilt.consumptions);
        assertEquals(consumptionResponse.date, responseBuilt.date);
        assertEquals(consumptionResponse.pharmacy, responseBuilt.pharmacy);
    }
}
