package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import java.util.Collection;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PrescriptionDTO;

public class PatientDTOAssembler extends DTOAssembler<Patient, PatientDTO> {

    private final PrescriptionDTOAssembler prescriptionDTOAssembler;

    protected PatientDTOAssembler(PrescriptionDTOAssembler prescriptionDTOAssembler) {
        this.prescriptionDTOAssembler = prescriptionDTOAssembler;
    }

    public PatientDTOAssembler() {
        this.prescriptionDTOAssembler = new PrescriptionDTOAssembler();
    }

    @Override
    public Patient fromDTO(PatientDTO dto) {
        Collection<Prescription> prescriptions = prescriptionDTOAssembler.fromDTOs(dto.prescriptions);

        return new Patient(dto.number, prescriptions, dto.healthInsuranceNumber);
    }

    @Override
    public PatientDTO toDTO(Patient patient) {
        Collection<PrescriptionDTO> prescriptionsDTO = prescriptionDTOAssembler.toDTOs(patient.getPrescriptions());

        return new PatientDTO(patient.getNumber(), prescriptionsDTO, patient.getHealthInsuranceNumber());
    }
}
