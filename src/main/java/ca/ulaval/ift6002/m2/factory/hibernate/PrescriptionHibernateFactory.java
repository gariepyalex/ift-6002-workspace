package ca.ulaval.ift6002.m2.factory.hibernate;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.PrescriptionHibernate;

public class PrescriptionHibernateFactory implements PrescriptionFactory {

    @Override
    public Prescription create(Practitioner practitioner, Date date, int renewals, Drug drug) {
        return new PrescriptionHibernate(practitioner, date, renewals, drug);
    }
}
