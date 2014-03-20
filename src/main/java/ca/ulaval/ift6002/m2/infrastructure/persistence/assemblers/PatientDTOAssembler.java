package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import java.util.Collection;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.factory.hibernate.PatientHibernateFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;

public class PatientDTOAssembler extends DTOAssembler<Patient, PatientDTO> {

    private final PrescriptionDTOAssembler prescriptionDTOAssembler;
    private final PatientFactory patientFactory;

    public PatientDTOAssembler() {
        this.prescriptionDTOAssembler = new PrescriptionDTOAssembler();
        // TODO The class should be deleted soon
        this.patientFactory = new PatientHibernateFactory();
    }

    @Override
    public Patient fromDTO(PatientDTO dto) {
        Collection<Prescription> prescriptions = prescriptionDTOAssembler.fromDTOs(dto.prescriptions);

        return patientFactory.create(dto.number, dto.healthInsuranceNumber, prescriptions);
    }

    @Override
    public PatientDTO toDTO(Patient patient) {
        prescriptionDTOAssembler.toDTOs(patient.getPrescriptions());

        // This class will be deleted soon...
        return null;
        // return new PatientDTO(patient.getNumber(), prescriptionsDTO,
        // patient.getHealthInsuranceNumber());
    }

    protected PatientDTOAssembler(PrescriptionDTOAssembler prescriptionDTOAssembler, PatientFactory patientFactory) {
        this.prescriptionDTOAssembler = prescriptionDTOAssembler;
        this.patientFactory = patientFactory;
    }
}
