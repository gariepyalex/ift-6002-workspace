package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;

public class ConsumptionResponseAssemblerTest {

    private static final DateFormatter DATE_FORMATTER = new DateFormatter();
    private static final Pharmacy PHARMACY = new Pharmacy("pharmacy");
    private static final int COUNT = 1;
    private static final String DATE_AS_STRING = "2001-07-04T12:08:56";
    private static final Date DATE = DATE_FORMATTER.parse(DATE_AS_STRING);
    private static final Consumption CONSUMPTION = new Consumption(DATE, PHARMACY, COUNT);
    private static final ConsumptionRequest CONSUMPTION_RESPONSE = new ConsumptionRequest(DATE_AS_STRING,
            PHARMACY.toString(), COUNT);
    private ConsumptionResponseAssembler consumptionResponseAssembler = new ConsumptionResponseAssembler();

    @Test
    public void givenConsumptionWhenConvertToResponseShouldReturnGivenConsumptionResponse() {
        ConsumptionRequest responseBuilt = consumptionResponseAssembler.toResponse(CONSUMPTION);
        assertConsumptionResponseEquals(CONSUMPTION_RESPONSE, responseBuilt);
    }

    @Test
    public void givenConsumptionResponseWhenConvertToConsumptionShouldReturnGivenConsumption() {
        Consumption consumptionBuilt = consumptionResponseAssembler.fromResponse(CONSUMPTION_RESPONSE);
        assertEquals(CONSUMPTION, consumptionBuilt);
    }

    private void assertConsumptionResponseEquals(ConsumptionRequest expected, ConsumptionRequest actual) {
        assertEquals(expected.consumptions, actual.consumptions);
        assertEquals(expected.date, actual.date);
        assertEquals(expected.pharmacy, actual.pharmacy);
    }

}
