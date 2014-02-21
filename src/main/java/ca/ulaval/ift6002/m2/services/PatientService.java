package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionDTO;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.InvalidPrescriptionException;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PatientService {

    private final PrescriptionDTOAssembler prescriptionAssembler;
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository, PrescriptionDTOAssembler prescriptionAssembler) {
        this.patientRepository = patientRepository;
        this.prescriptionAssembler = prescriptionAssembler;
    }

    public void savePrescription(String patientId, PrescriptionDTO dto) {
        // TODO have a PrescriptionValidator, this class do 2 things
        validateDtoIntegrity(dto);
        Patient patient = patientRepository.get(Integer.valueOf(patientId));
        Prescription prescription = prescriptionAssembler.fromDTO(dto);
        patientRepository.savePrescription(patient, prescription);
    }

    private void validateDtoIntegrity(PrescriptionDTO dto) throws InvalidPrescriptionException {
        if (!hasEnoughRenewals(dto)) {
            throw new InvalidPrescriptionException("The number of renewals must be greater than or equals to zero");
        }

        if (!hasSetDinOrDrugName(dto)) {
            throw new InvalidPrescriptionException("A din or drug name must be set");
        }

        if (hasSetBothDinAndDrugName(dto)) {
            throw new InvalidPrescriptionException("You cannot set din and drug name at the same time");
        }
    }

    private boolean hasEnoughRenewals(PrescriptionDTO dto) {
        return (dto.renewals != null && dto.renewals >= 0);
    }

    private boolean isDinSet(PrescriptionDTO dto) {
        return (!dto.din.trim().isEmpty());
    }

    private boolean isDrugNameSet(PrescriptionDTO dto) {
        return (!dto.name.trim().isEmpty());
    }

    private boolean hasSetDinOrDrugName(PrescriptionDTO dto) {
        return (isDinSet(dto) || isDrugNameSet(dto));
    }

    private boolean hasSetBothDinAndDrugName(PrescriptionDTO dto) {
        return (isDinSet(dto) && isDrugNameSet(dto));
    }
}
