package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PrescriptionDTO;

public class PrescriptionDTOAssembler {

    private final DateFormatter dateFormatter;
    private final DrugDTOAssembler drugDTOAssembler;

    public PrescriptionDTOAssembler(DateFormatter dateFormatter, DrugDTOAssembler drugDTOAssembler) {
        this.dateFormatter = dateFormatter;
        this.drugDTOAssembler = drugDTOAssembler;
    }

    public PrescriptionDTO toDTO(Prescription prescription) {
        String practitioner = prescription.getPractioner().toString();
        String date = dateFormatter.dateToString(prescription.getDate());
        Integer renewals = prescription.getRenewals();
        DrugDTO drugDTO = drugDTOAssembler.toDTO(prescription.getDrug());
        // TODO do something with this
        return new PrescriptionDTO(practitioner, date, renewals);
    }
}
