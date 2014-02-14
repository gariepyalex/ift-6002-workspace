package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PatientInMemoryRepository implements PatientRepository {

    private boolean empty = true;

    @Override
    public void savePrescription(Patient patient, Prescription prescription) {
        this.empty = false;
    }

    public boolean isEmpty() {
        return empty;
    }

    @Override
    public Patient getPatientById(Integer id) {
        return new Patient(id);
    }

}
