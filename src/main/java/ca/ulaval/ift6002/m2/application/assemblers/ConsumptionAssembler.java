package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;
import java.util.List;

import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;
import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.ConsumptionFactory;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class ConsumptionAssembler {

    private final DateFormatter dateFormatter;
    private final ConsumptionFactory consumptionFactory;

    public ConsumptionAssembler() {
        this.dateFormatter = new DateFormatter();
        this.consumptionFactory = FactoryLocator.getConsumptionFactory();
    }

    public Consumption fromRequest(ConsumptionRequest request) {
        Date formattedDate = dateFormatter.parse(request.date);
        Pharmacy pharmacy = new Pharmacy(request.pharmacy);
        Integer consumptionsCount = request.consumptions;

        return consumptionFactory.create(formattedDate, pharmacy, consumptionsCount);
    }

    public ConsumptionResponse toResponse(Consumption consumption) {
        String date = dateFormatter.dateToString(consumption.getDate());
        String pharmacy = consumption.getPharmacy().toString();
        Integer count = Integer.valueOf(consumption.getCount());

        return new ConsumptionResponse(date, pharmacy, count);
    }

    public ConsumptionResponse[] toResponses(List<Consumption> consumptions) {
        ConsumptionResponse[] responses = new ConsumptionResponse[consumptions.size()];

        for (int i = 0; i < consumptions.size(); i++) {
            responses[i] = toResponse(consumptions.get(i));
        }
        return responses;
    }

    protected ConsumptionAssembler(DateFormatter dateFormatter, ConsumptionFactory consumptionFactory) {
        this.dateFormatter = dateFormatter;
        this.consumptionFactory = consumptionFactory;
    }
}
