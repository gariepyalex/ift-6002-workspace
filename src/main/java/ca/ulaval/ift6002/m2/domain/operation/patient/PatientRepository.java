package ca.ulaval.ift6002.m2.domain.operation.patient;


public interface PatientRepository {

    Patient get(int number);

    void store(Patient patient);
}
