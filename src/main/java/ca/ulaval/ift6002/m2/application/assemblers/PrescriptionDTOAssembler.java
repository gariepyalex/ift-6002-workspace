package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionDTO;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PrescriptionDTOAssembler {

    private final DateFormatter dateFormatter;
    private final DrugRepository drugRepository;

    public PrescriptionDTOAssembler(DateFormatter dateFormatter, DrugRepository drugRepository) {
        this.dateFormatter = dateFormatter;
        this.drugRepository = drugRepository;
    }

    public PrescriptionDTO toDTO(Prescription prescription) {
        String formattedDate = dateFormatter.dateToString(prescription.getDate());

        return new PrescriptionDTO(prescription.getPractioner().toString(), formattedDate, prescription.getRenewals(),
                prescription.getDrug().getDin().toString(), prescription.getDrug().getBrandName());
    }

    public Prescription fromDTO(PrescriptionDTO dto) {
        Practitioner practitioner = new Practitioner(dto.practitioner);
        Date parsedDate = dateFormatter.parse(dto.date);
        Din din = new Din(dto.din);

        Drug drug = drugRepository.get(din);

        return new Prescription(practitioner, parsedDate, dto.renewals, drug);
    }
}
