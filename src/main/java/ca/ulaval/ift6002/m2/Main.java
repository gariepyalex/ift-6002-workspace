package ca.ulaval.ift6002.m2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ca.ulaval.ift6002.m2.contexts.DemoDrugRepositoryFiller;
import ca.ulaval.ift6002.m2.contexts.DemoPatientRepositoryFiller;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.ConsumptionFactory;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;
import ca.ulaval.ift6002.m2.factory.FactoryLocator;
import ca.ulaval.ift6002.m2.factory.hibernate.ConsumptionHibernateFactory;
import ca.ulaval.ift6002.m2.factory.hibernate.DrugHibernateFactory;
import ca.ulaval.ift6002.m2.factory.hibernate.InstrumentHibernateFactory;
import ca.ulaval.ift6002.m2.factory.hibernate.OperationHibernateFactory;
import ca.ulaval.ift6002.m2.factory.hibernate.PatientHibernateFactory;
import ca.ulaval.ift6002.m2.factory.hibernate.PrescriptionHibernateFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.OperationHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.PatientHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class Main {

    public static void main(String[] args) {
        setupRepositoryLocator();
        setupFactoryLocator();

        EntityManager entityManager = setUpEntityManager();
        fillDrugRepository();
        fillPatientRepository();

        JettyServer server = new JettyServer();
        server.start();

        closeEntityManager(entityManager);
    }

    private static void setupRepositoryLocator() {
        RepositoryLocator repositoryLocator = new RepositoryLocator();

        repositoryLocator.register(DrugRepository.class, new DrugHibernateRepository());
        repositoryLocator.register(PatientRepository.class, new PatientHibernateRepository());
        repositoryLocator.register(OperationRepository.class, new OperationHibernateRepository());

        RepositoryLocator.load(repositoryLocator);
    }

    private static void setupFactoryLocator() {
        FactoryLocator factoryLocator = new FactoryLocator();

        factoryLocator.register(ConsumptionFactory.class, new ConsumptionHibernateFactory());
        factoryLocator.register(DrugFactory.class, new DrugHibernateFactory());
        factoryLocator.register(InstrumentFactory.class, new InstrumentHibernateFactory());
        factoryLocator.register(OperationFactory.class, new OperationHibernateFactory());
        factoryLocator.register(PatientFactory.class, new PatientHibernateFactory());
        factoryLocator.register(PrescriptionFactory.class, new PrescriptionHibernateFactory());

        FactoryLocator.load(factoryLocator);
    }

    private static EntityManager setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProvider.setEntityManager(entityManager);

        return entityManager;
    }

    private static void fillDrugRepository() {
        new DemoDrugRepositoryFiller().fill();
    }

    private static void fillPatientRepository() {
        new DemoPatientRepositoryFiller().fill();
    }

    private static void closeEntityManager(EntityManager entityManager) {
        EntityManagerProvider.clearEntityManager();
        entityManager.close();
        EntityManagerFactoryProvider.getFactory().close();
    }

}
