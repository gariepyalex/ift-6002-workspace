package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.PatientHibernate;

public class PatientHibernateFactory implements PatientFactory {

    @Override
    public Patient create(int number, String healthInsuranceNumber) {
        return new PatientHibernate(number, healthInsuranceNumber);
    }

}
