package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class PrescriptionResponseAssembler {

    private final DateFormatter dateFormatter;
    private final DrugRepository drugRepository;

    protected PrescriptionResponseAssembler(DateFormatter dateFormatter, DrugRepository drugRepository) {
        this.dateFormatter = dateFormatter;
        this.drugRepository = drugRepository;
    }

    public PrescriptionResponseAssembler() {
        this.dateFormatter = new DateFormatter();
        this.drugRepository = RepositoryLocator.getDrugRepository();
    }

    public PrescriptionResponse toResponse(Prescription prescription) {
        String formattedDate = dateFormatter.dateToString(prescription.getDate());
        String practitioner = prescription.getPractioner().toString();
        Integer renewals = prescription.getRenewals();
        String din = prescription.getDrug().getDin().toString();
        String brandName = prescription.getDrug().getBrandName();

        return new PrescriptionResponse(practitioner, formattedDate, renewals, din, brandName);
    }

    public Prescription fromResponse(PrescriptionResponse response) {
        Practitioner practitioner = new Practitioner(response.practitioner);
        Date parsedDate = dateFormatter.parse(response.date);
        Drug drug = getDrugFromRepository(response);

        return new Prescription(practitioner, parsedDate, response.renewals, drug);
    }

    private Drug getDrugFromRepository(PrescriptionResponse response) {
        if (isDinSpecified(response)) {
            Din din = new Din(response.din);

            return drugRepository.get(din);
        } else {
            return drugRepository.get(response.name);
        }
    }

    private boolean isDinSpecified(PrescriptionResponse response) {
        return !response.din.trim().isEmpty();
    }
}
