package projectH.application.assemblers;

import java.util.Date;

import projectH.application.responses.PrescriptionDTO;
import projectH.domain.date.DateFormatter;
import projectH.domain.drug.Din;
import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;
import projectH.domain.prescription.Practitioner;
import projectH.domain.prescription.Prescription;

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
