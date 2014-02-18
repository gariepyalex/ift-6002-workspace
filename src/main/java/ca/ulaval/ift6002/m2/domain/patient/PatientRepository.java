package ca.ulaval.ift6002.m2.domain.patient;

import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public interface PatientRepository {

    void savePrescription(Patient patient, Prescription prescription);

    Patient get(int id);
}
