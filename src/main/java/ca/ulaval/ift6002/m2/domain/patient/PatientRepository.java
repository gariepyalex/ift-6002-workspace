package ca.ulaval.ift6002.m2.domain.patient;

import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public interface PatientRepository {

    /**
     * TODO You should not have a method savePrescription, you should get your
     * patient, append (update) your prescriptions and store it back to the
     * repository
     */
    void savePrescription(Patient patient, Prescription prescription);

    Patient get(int id);
}
