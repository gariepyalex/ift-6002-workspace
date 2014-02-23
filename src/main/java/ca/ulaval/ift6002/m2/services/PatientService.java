package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PatientService {

    private final PrescriptionResponseAssembler prescriptionAssembler;
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository, PrescriptionResponseAssembler prescriptionAssembler) {
        this.patientRepository = patientRepository;
        this.prescriptionAssembler = prescriptionAssembler;
    }

    public void savePrescription(String patientId, PrescriptionResponse dto) {
        Prescription prescription = prescriptionAssembler.fromResponse(dto);
        Patient patient = patientRepository.get(Integer.valueOf(patientId));

        patient.addPrescription(prescription);

        patientRepository.store(patient);
    }
}
