package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;
import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;

@RunWith(MockitoJUnitRunner.class)
public class ConsumptionAssemblerTest {

    private static final Pharmacy PHARMACY = new Pharmacy("pharmacy");
    private static final int COUNT = 1;
    private static final String DATE_AS_STRING = "2001-07-04T12:08:56";
    private static final ConsumptionResponse CONSUMPTION_RESPONSE = new ConsumptionResponse(DATE_AS_STRING,
            PHARMACY.toString(), COUNT);
    private static final ConsumptionRequest CONSUMPTION_REQUEST = new ConsumptionRequest(DATE_AS_STRING,
            PHARMACY.toString(), COUNT);
    private ConsumptionAssembler consumptionAssembler = new ConsumptionAssembler();

    @Mock
    private Consumption consumption;

    @Test
    public void givenConsumptionWhenConvertToResponseShouldReturnGivenConsumptionResponse() {
        ConsumptionResponse responseBuilt = consumptionAssembler.toResponse(consumption);
        assertConsumptionResponseEquals(CONSUMPTION_RESPONSE, responseBuilt);
    }

    @Test
    public void givenConsumptionRequestWhenConvertToConsumptionShouldReturnGivenConsumption() {
        Consumption consumptionBuilt = consumptionAssembler.fromRequest(CONSUMPTION_REQUEST);
        assertEquals(consumption, consumptionBuilt);
    }

    private void assertConsumptionResponseEquals(ConsumptionResponse consumptionResponse,
            ConsumptionResponse responseBuilt) {
        assertEquals(consumptionResponse.consumptions, responseBuilt.consumptions);
        assertEquals(consumptionResponse.date, responseBuilt.date);
        assertEquals(consumptionResponse.pharmacy, responseBuilt.pharmacy);
    }

}
