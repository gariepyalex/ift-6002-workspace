package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class PatientService {

    private final PrescriptionResponseAssembler prescriptionAssembler;
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository, PrescriptionResponseAssembler prescriptionAssembler) {
        this.patientRepository = patientRepository;
        this.prescriptionAssembler = prescriptionAssembler;
    }

    public PatientService() {
        this.patientRepository = RepositoryLocator.getPatientRepository();
        this.prescriptionAssembler = new PrescriptionResponseAssembler();
    }

    public void savePrescription(String patientId, PrescriptionResponse response) {
        Prescription prescription = prescriptionAssembler.fromResponse(response);
        Patient patient = loadPatient(patientId);

        patient.receivesPrescription(prescription);

        patientRepository.store(patient);
    }

    public PrescriptionResponse[] loadPrescription(String patientId) {

        Patient patient = loadPatient(patientId);
        return prescriptionAssembler.toResponses(patient.getPrescriptions());
    }

    private Patient loadPatient(String patientId) {
        return patientRepository.get(Integer.valueOf(patientId));
    }
}
