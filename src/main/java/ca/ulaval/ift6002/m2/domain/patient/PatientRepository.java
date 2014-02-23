package ca.ulaval.ift6002.m2.domain.patient;


public interface PatientRepository {

    Patient get(int id);

    void store(Patient patient);
}
