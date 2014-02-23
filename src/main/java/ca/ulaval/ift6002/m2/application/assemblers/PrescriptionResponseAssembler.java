package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PrescriptionResponseAssembler {

    private final DateFormatter dateFormatter;
    private final DrugRepository drugRepository;

    public PrescriptionResponseAssembler(DateFormatter dateFormatter, DrugRepository drugRepository) {
        this.dateFormatter = dateFormatter;
        this.drugRepository = drugRepository;
    }

    public PrescriptionResponse toResponse(Prescription prescription) {
        String formattedDate = dateFormatter.dateToString(prescription.getDate());

        return new PrescriptionResponse(prescription.getPractioner().toString(), formattedDate, prescription.getRenewals(),
                prescription.getDrug().getDin().toString(), prescription.getDrug().getBrandName());
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
