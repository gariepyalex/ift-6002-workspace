package ca.ulaval.ift6002.m2.factory.hibernate;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.ConsumptionFactory;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.ConsumptionHibernate;

public class ConsumptionHibernateFactory implements ConsumptionFactory {

    @Override
    public Consumption create(Date date, Pharmacy pharmacy, int count) {
        return new ConsumptionHibernate(date, pharmacy, count);
    }

}
