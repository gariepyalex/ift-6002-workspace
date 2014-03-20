package ca.ulaval.ift6002.m2.factory.hibernate;

import java.util.Collection;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PatientHibernateFactory implements PatientFactory {

    @Override
    public Patient create(int number, String healthInsuranceNumber) {
        // TODO
        return null;
    }

    @Override
    public Patient create(int number, String healthInsuranceNumber, Collection<Prescription> prescriptions) {
        // TODO
        return null;
    }

}
