package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Collection;

import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.application.responses.DetailedPrescriptionResponse;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;

public class DetailedPrescriptionAssembler extends PrescriptionAssembler {

    private final ConsumptionAssembler consumptionAssembler;

    public DetailedPrescriptionAssembler() {
        super();
        consumptionAssembler = new ConsumptionAssembler();
    }

    public DetailedPrescriptionResponse toDetailedResponse(Prescription prescription) {
        PrescriptionResponse summaryResponse = super.toResponse(prescription);

        ConsumptionResponse[] consumptions = consumptionAssembler.toResponses(prescription.getConsumptions());
        String din = prescription.getDrug().getDin().toString();

        return new DetailedPrescriptionResponse(summaryResponse.practitioner, summaryResponse.name,
                summaryResponse.date, summaryResponse.remainingRenewals, summaryResponse.autorizedRenewals,
                consumptions, din);
    }

    public DetailedPrescriptionResponse[] toDetailedResponses(Collection<Prescription> prescriptions) {
        DetailedPrescriptionResponse[] prescriptionResponses = new DetailedPrescriptionResponse[prescriptions.size()];
        int i = 0;

        for (Prescription prescription : prescriptions) {
            prescriptionResponses[i++] = toDetailedResponse(prescription);
        }

        return prescriptionResponses;
    }

    protected DetailedPrescriptionAssembler(DateFormatter dateFormatter, DrugRepository drugRepository,
            PrescriptionFactory prescriptionFactory, ConsumptionAssembler consumptionAssembler) {
        super(dateFormatter, drugRepository, prescriptionFactory);
        this.consumptionAssembler = consumptionAssembler;
    }
}
