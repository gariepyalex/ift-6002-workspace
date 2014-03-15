package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.ConsumptionResponseAssembler;
import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class PatientService {

    private final PrescriptionResponseAssembler prescriptionAssembler;
    private final PatientRepository patientRepository;

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

    public void savePrescription(String patientId, PrescriptionResponse response) {
        Prescription prescription = prescriptionAssembler.fromResponse(response);
        Patient patient = loadPatient(patientId);

        patient.receivesPrescription(prescription);

        patientRepository.store(patient);
    }

    public void addConsumption(String patientId, String prescriptionId, ConsumptionResponse response) {
        Consumption consumption = consumptionAssembler.fromResponse(response);

        Patient patient = loadPatient(patientId);
        patient.consumePrescription(Integer.valueOf(prescriptionId), consumption);
    }

    public PrescriptionResponse[] loadPrescription(String patientId) {
        Patient patient = loadPatient(patientId);

        return prescriptionAssembler.toResponses(patient.getPrescriptions());
    }

    private Patient loadPatient(String patientId) {
        return patientRepository.get(Integer.valueOf(patientId));
    }
}
