package ca.ulaval.ift6002.m2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ca.ulaval.ift6002.m2.contexts.DemoDrugRepositoryFiller;
import ca.ulaval.ift6002.m2.contexts.DemoPatientRepositoryFiller;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.factory.HibernateRepositoryFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.factory.RepositoryFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class Main {

    public static void main(String[] args) {
        setupRepositoryLocator();

        EntityManager entityManager = setUpEntityManager();
        fillDrugRepository();
        fillPatientRepository();

        JettyServer server = new JettyServer();
        server.start();

        closeEntityManager(entityManager);
    }

    private static void setupRepositoryLocator() {
        RepositoryFactory hibernateRepositoryFactory = new HibernateRepositoryFactory();
        RepositoryLocator repositoryLocator = new RepositoryLocator();

        repositoryLocator.register(DrugRepository.class, hibernateRepositoryFactory.createDrugRepository());
        repositoryLocator.register(PatientRepository.class, hibernateRepositoryFactory.createPatientRepository());
        repositoryLocator.register(OperationRepository.class, hibernateRepositoryFactory.createOperationRepository());

        RepositoryLocator.load(repositoryLocator);
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
