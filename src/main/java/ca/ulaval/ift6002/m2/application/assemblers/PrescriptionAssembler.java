package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Collection;
import java.util.Date;

import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;
import ca.ulaval.ift6002.m2.factory.hibernate.PrescriptionHibernateFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class PrescriptionAssembler {

    private final DateFormatter dateFormatter;
    private final DrugRepository drugRepository;
    private final PrescriptionFactory prescriptionFactory;

    protected PrescriptionAssembler(DateFormatter dateFormatter, DrugRepository drugRepository,
            PrescriptionFactory prescriptionFactory) {
        this.dateFormatter = dateFormatter;
        this.drugRepository = drugRepository;
        this.prescriptionFactory = prescriptionFactory;
    }

    public PrescriptionAssembler() {
        this.dateFormatter = new DateFormatter();
        this.drugRepository = RepositoryLocator.getDrugRepository();
        // TODO Call the factory Locator
        this.prescriptionFactory = new PrescriptionHibernateFactory();
    }

    public PrescriptionResponse toResponse(Prescription prescription) {
        String formattedDate = dateFormatter.dateToString(prescription.getDate());
        String practitioner = prescription.getPractioner().toString();
        Integer remainingRenewals = Integer.valueOf(prescription.remainingRenewals());
        Integer authorizedRenewals = Integer.valueOf(prescription.getRenewals());
        String brandName = prescription.getDrug().getBrandName();

        return new PrescriptionResponse(brandName, practitioner, formattedDate, remainingRenewals, authorizedRenewals);
    }

    public PrescriptionResponse[] toResponses(Collection<Prescription> prescriptions) {
        PrescriptionResponse[] prescriptionResponses = new PrescriptionResponse[prescriptions.size()];
        int i = 0;

        for (Prescription prescription : prescriptions) {
            prescriptionResponses[i++] = toResponse(prescription);
        }

        return prescriptionResponses;
    }

    public Prescription fromRequest(PrescriptionRequest request) {
        Practitioner practitioner = new Practitioner(request.practitioner);
        Date parsedDate = dateFormatter.parse(request.date);
        Drug drug = getDrugFromRepository(request);

        return prescriptionFactory.create(practitioner, parsedDate, request.renewals, drug);
    }

    private Drug getDrugFromRepository(PrescriptionRequest request) {
        if (isDinSpecified(request)) {
            Din din = new Din(request.din);

            return drugRepository.get(din);
        } else {
            return drugRepository.get(request.name);
        }
    }

    private boolean isDinSpecified(PrescriptionRequest request) {
        return !request.din.trim().isEmpty();
    }

}
