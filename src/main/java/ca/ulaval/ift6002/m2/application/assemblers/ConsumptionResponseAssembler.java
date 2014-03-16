package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;

public class ConsumptionResponseAssembler {

    private final DateFormatter dateFormatter;

    protected ConsumptionResponseAssembler(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public ConsumptionResponseAssembler() {
        this.dateFormatter = new DateFormatter();
    }

    public Consumption fromResponse(ConsumptionResponse response) {
        Date formattedDate = dateFormatter.parse(response.date);
        Pharmacy pharmacy = new Pharmacy(response.pharmacy);
        Integer consumptionsCount = response.consumptions;

        return new Consumption(formattedDate, pharmacy, consumptionsCount);
    }

    public ConsumptionResponse toResponse(Consumption consumption) {
        String date = dateFormatter.dateToString(consumption.getDate());
        String pharmacy = consumption.getPharmacy().toString();
        Integer count = Integer.valueOf(consumption.getCount());

        return new ConsumptionResponse(date, pharmacy, count);
    }
}
