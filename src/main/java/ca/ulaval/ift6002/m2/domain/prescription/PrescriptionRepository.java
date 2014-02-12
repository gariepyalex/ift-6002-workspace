package ca.ulaval.ift6002.m2.domain.prescription;

import ca.ulaval.ift6002.m2.domain.patient.Patient;

public interface PrescriptionRepository {

    void save(Patient patient, Prescription prescription);
}
