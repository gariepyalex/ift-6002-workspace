package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
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
    private static final ConsumptionResponse CONSUMPTION_RESPONSE = new ConsumptionResponse(DATE_AS_STRING,
            PHARMACY.toString(), COUNT);
    private ConsumptionResponseAssembler consumptionResponseAssembler = new ConsumptionResponseAssembler();

    @Before
    public void setUpConsumptionResponseAssemblerTest() {

    }

    @Test
    public void givenConsumptionWhenConvertToResponseShouldReturnGivenConsumptionResponse() {
        ConsumptionResponse responseBuilt = consumptionResponseAssembler.toResponse(CONSUMPTION);
        assertConsumptionResponseEquals(CONSUMPTION_RESPONSE, responseBuilt);
    }

    @Test
    public void givenConsumptionResponseWhenConvertToConsumptionShouldReturnGivenConsumption() {
        Consumption consumptionBuilt = consumptionResponseAssembler.fromResponse(CONSUMPTION_RESPONSE);
        assertConsumptionEquals(CONSUMPTION, consumptionBuilt);
    }

    private void assertConsumptionEquals(Consumption expected, Consumption actual) {
        assertEquals(expected.getCount(), actual.getCount());
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getPharmacy(), actual.getPharmacy());
    }

    private void assertConsumptionResponseEquals(ConsumptionResponse expected, ConsumptionResponse actual) {
        assertEquals(expected.consumptions, actual.consumptions);
        assertEquals(expected.date, actual.date);
        assertEquals(expected.pharmacy, actual.pharmacy);
    }

}
