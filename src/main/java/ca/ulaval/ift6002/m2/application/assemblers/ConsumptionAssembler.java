package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;
import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.ConsumptionFactory;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;
import ca.ulaval.ift6002.m2.factory.hibernate.ConsumptionHibernateFactory;

public class ConsumptionAssembler {

    private final DateFormatter dateFormatter;
    private final ConsumptionFactory consumptionFactory;

    protected ConsumptionAssembler(DateFormatter dateFormatter, ConsumptionFactory consumptionFactory) {
        this.dateFormatter = dateFormatter;
        this.consumptionFactory = consumptionFactory;
    }

    public ConsumptionAssembler() {
        this.dateFormatter = new DateFormatter();
        // TODO call locator instead of new...
        this.consumptionFactory = new ConsumptionHibernateFactory();
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
}
