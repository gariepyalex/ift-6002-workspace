package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionDTO;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PatientService {

    private final PrescriptionDTOAssembler prescriptionAssembler;
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository, PrescriptionDTOAssembler prescriptionAssembler) {
        this.patientRepository = patientRepository;
        this.prescriptionAssembler = prescriptionAssembler;
    }

    public void savePrescription(String patientId, PrescriptionDTO dto) {
        Prescription prescription = prescriptionAssembler.fromDTO(dto);
        Patient patient = patientRepository.get(Integer.valueOf(patientId));

        patient = Patient.addPrescriptionTo(patient, prescription);
        patientRepository.store(patient);
    }
}
