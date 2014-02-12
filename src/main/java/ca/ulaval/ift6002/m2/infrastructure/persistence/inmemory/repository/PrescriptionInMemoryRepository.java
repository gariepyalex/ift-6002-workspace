package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionRepository;

public class PrescriptionInMemoryRepository implements PrescriptionRepository {

    private boolean empty = true;

    @Override
    public void save(Patient patient, Prescription prescription) {
        this.empty = false;
    }

    public boolean isEmpty() {
        return empty;
    }

}
