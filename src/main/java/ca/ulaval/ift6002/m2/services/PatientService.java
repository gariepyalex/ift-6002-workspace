package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.ConsumptionAssembler;
import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionAssembler;
import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;
import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class PatientService {

    private final PatientRepository patientRepository;
    private final PrescriptionAssembler prescriptionAssembler;
    private final ConsumptionAssembler consumptionAssembler;

    public PatientService(PatientRepository patientRepository, PrescriptionAssembler prescriptionAssembler,
            ConsumptionAssembler consumptionAssembler) {
        this.patientRepository = patientRepository;
        this.prescriptionAssembler = prescriptionAssembler;
        this.consumptionAssembler = consumptionAssembler;
    }

    public PatientService() {
        this.patientRepository = RepositoryLocator.getPatientRepository();
        this.prescriptionAssembler = new PrescriptionAssembler();
        this.consumptionAssembler = new ConsumptionAssembler();
    }

    public void savePrescription(String patientId, PrescriptionRequest response) {
        Prescription prescription = prescriptionAssembler.fromResponse(response);
        Patient patient = getPatient(patientId);

        patient.receivesPrescription(prescription);

        patientRepository.store(patient);
    }

    public void addConsumption(String patientId, String prescriptionId, ConsumptionRequest response) {
        Consumption consumption = consumptionAssembler.fromRequest(response);

        Patient patient = getPatient(patientId);
        patient.consumesPrescription(Integer.valueOf(prescriptionId), consumption);
    }

    public PrescriptionRequest[] getPrescriptions(String patientId) {
        Patient patient = getPatient(patientId);

        return prescriptionAssembler.toResponses(patient.getPrescriptions());
    }

    private Patient getPatient(String patientId) {
        return patientRepository.get(Integer.valueOf(patientId));
    }
}
