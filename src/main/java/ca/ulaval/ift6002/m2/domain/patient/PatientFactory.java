package ca.ulaval.ift6002.m2.domain.patient;

import java.util.Collection;

import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public interface PatientFactory {

    Patient create(int number, String healthInsuranceNumber);

    Patient create(int number, String healthInsuranceNumber, Collection<Prescription> prescriptions);
}
