package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;
import ca.ulaval.ift6002.m2.factory.FactoryLocator;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PrescriptionDTO;

public class PrescriptionDTOAssembler extends DTOAssembler<Prescription, PrescriptionDTO> {

    private final DateFormatter dateFormatter;
    private final DrugDTOAssembler drugDTOAssembler;
    private final PrescriptionFactory prescriptionFactory;

    protected PrescriptionDTOAssembler(DateFormatter dateFormatter, DrugDTOAssembler drugDTOAssembler,
            PrescriptionFactory prescriptionFactory) {
        this.dateFormatter = dateFormatter;
        this.drugDTOAssembler = drugDTOAssembler;
        this.prescriptionFactory = prescriptionFactory;
    }

    public PrescriptionDTOAssembler() {
        this.dateFormatter = new DateFormatter();
        this.drugDTOAssembler = new DrugDTOAssembler();
        this.prescriptionFactory = FactoryLocator.getPrescriptionFactory();
    }

    @Override
    public PrescriptionDTO toDTO(Prescription prescription) {
        String practitioner = prescription.getPractioner().toString();
        String date = dateFormatter.dateToString(prescription.getDate());
        Integer renewals = prescription.getRenewals();

        DrugDTO drugDTO = null;
        String other = "";
        Drug drug = prescription.getDrug();

        if (drug.hasDin()) {
            drugDTO = drugDTOAssembler.toDTO(prescription.getDrug());
        } else {
            other = drug.getBrandName();
        }

        return new PrescriptionDTO(practitioner, date, renewals, other, drugDTO);
    }

    @Override
    public Prescription fromDTO(PrescriptionDTO dto) {
        Practitioner practitioner = new Practitioner(dto.practitioner);
        Date date = dateFormatter.parse(dto.date);
        Integer renewals = dto.renewals;

        Drug drug;
        if (dto.other.isEmpty()) {
            drug = drugDTOAssembler.fromDTO(dto.drugDTO);
        } else {
            // TODO Change this
            drug = null;// Drug.fromName(dto.other);
        }

        return prescriptionFactory.create(practitioner, date, renewals, drug);
    }

}
