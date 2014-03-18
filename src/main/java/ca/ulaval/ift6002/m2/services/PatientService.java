package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.ConsumptionResponseAssembler;
import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionResponseAssembler;
import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;
import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class PatientService {

    private final PatientRepository patientRepository;
    private final PrescriptionResponseAssembler prescriptionAssembler;
    private final ConsumptionResponseAssembler consumptionAssembler;

    public PatientService(PatientRepository patientRepository, PrescriptionResponseAssembler prescriptionAssembler,
            ConsumptionResponseAssembler consumptionAssembler) {
        this.patientRepository = patientRepository;
        this.prescriptionAssembler = prescriptionAssembler;
        this.consumptionAssembler = consumptionAssembler;
    }

    public PatientService() {
        this.patientRepository = RepositoryLocator.getPatientRepository();
        this.prescriptionAssembler = new PrescriptionResponseAssembler();
        this.consumptionAssembler = new ConsumptionResponseAssembler();
    }

    public void savePrescription(String patientId, PrescriptionRequest response) {
        Prescription prescription = prescriptionAssembler.fromResponse(response);
        Patient patient = getPatient(patientId);

        patient.receivesPrescription(prescription);

        patientRepository.store(patient);
    }

    public void addConsumption(String patientId, String prescriptionId, ConsumptionRequest response) {
        Consumption consumption = consumptionAssembler.fromResponse(response);

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
