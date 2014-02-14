package ca.ulaval.ift6002.m2.domain.patient;

import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public interface PatientRepository {

    public void savePrescription(Patient patient, Prescription prescription);
    public Patient getPatientById(Integer id);
}
