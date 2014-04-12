package ca.ulaval.ift6002.m2.configuration.factory;

import ca.ulaval.ift6002.m2.configuration.Configuration;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.prescription.ConsumptionFactory;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory.ConsumptionHibernateFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory.DrugHibernateFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory.InstrumentHibernateFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory.OperationHibernateFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory.PatientHibernateFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory.PrescriptionHibernateFactory;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class HibernateFactoryConfiguration implements Configuration {

    @Override
    public void configure() {
        FactoryLocator factoryLocator = new FactoryLocator();

        factoryLocator.register(ConsumptionFactory.class, new ConsumptionHibernateFactory());
        factoryLocator.register(DrugFactory.class, new DrugHibernateFactory());
        factoryLocator.register(InstrumentFactory.class, new InstrumentHibernateFactory());
        factoryLocator.register(OperationFactory.class, new OperationHibernateFactory());
        factoryLocator.register(PatientFactory.class, new PatientHibernateFactory());
        factoryLocator.register(PrescriptionFactory.class, new PrescriptionHibernateFactory());

        FactoryLocator.load(factoryLocator);
    }

}
