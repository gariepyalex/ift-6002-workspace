package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;

public class PatientInMemoryRepository implements PatientRepository {

    @Override
    public Patient get(int id) {
        return new Patient(id);
    }

    @Override
    public void store(Patient patient) {
    }

}
