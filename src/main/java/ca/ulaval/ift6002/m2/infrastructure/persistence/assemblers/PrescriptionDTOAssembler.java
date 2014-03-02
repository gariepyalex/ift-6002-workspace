package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
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

        return new PrescriptionDTO(practitioner, date, renewals, drugDTO);
    }

    public Collection<PrescriptionDTO> toDTOs(Collection<Prescription> prescriptions) {
        Collection<PrescriptionDTO> prescriptionsDTO = new ArrayList<PrescriptionDTO>();

        for (Prescription prescription : prescriptions) {
            PrescriptionDTO prescriptionDTO = toDTO(prescription);
            prescriptionsDTO.add(prescriptionDTO);
        }

        return prescriptionsDTO;
    }

    public Prescription fromDTO(PrescriptionDTO dto) {
        Practitioner practitioner = new Practitioner(dto.practitioner);
        Date date = dateFormatter.parse(dto.date);
        Integer renewals = dto.renewals;
        Drug drug = drugDTOAssembler.fromDTO(dto.drugDTO);

        return new Prescription(practitioner, date, renewals, drug);
    }

    public Collection<Prescription> fromDTOs(Collection<PrescriptionDTO> prescriptionDTOs) {
        Collection<Prescription> prescriptions = new ArrayList<Prescription>();

        for (PrescriptionDTO dto : prescriptionDTOs) {
            Prescription prescription = fromDTO(dto);
            prescriptions.add(prescription);
        }

        return prescriptions;
    }

}
