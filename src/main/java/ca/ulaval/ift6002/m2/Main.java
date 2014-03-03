package ca.ulaval.ift6002.m2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ca.ulaval.ift6002.m2.contexts.DemoDrugRepositoryFiller;
import ca.ulaval.ift6002.m2.contexts.DemoPatientRepositoryFiller;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.operation.patient.PatientRepository;
import ca.ulaval.ift6002.m2.file.CSVDrugParser;
import ca.ulaval.ift6002.m2.file.CSVFileReader;
import ca.ulaval.ift6002.m2.file.FileParser;
import ca.ulaval.ift6002.m2.file.FileReader;
import ca.ulaval.ift6002.m2.infrastructure.persistence.factory.HibernateRepositoryFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.factory.RepositoryFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class Main {

    public static void main(String[] args) {
        setupRepositoryLocator();

        EntityManager entityManager = setUpEntityManager();
        fillDrugRepository(entityManager);
        fillPatientRepository(entityManager);

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

    private static void fillDrugRepository(EntityManager entityManager) {
        FileReader<String[]> fileReader = new CSVFileReader();
        FileParser<Drug> drugParser = new CSVDrugParser(fileReader);

        new DemoDrugRepositoryFiller(RepositoryLocator.getDrugRepository(), drugParser).fill();
    }

    private static void fillPatientRepository(EntityManager entityManager) {
        new DemoPatientRepositoryFiller().fill(RepositoryLocator.getPatientRepository());
    }

    private static void closeEntityManager(EntityManager entityManager) {
        EntityManagerProvider.clearEntityManager();
        entityManager.close();
        EntityManagerFactoryProvider.getFactory().close();
    }

    private static EntityManager setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProvider.setEntityManager(entityManager);

        return entityManager;
    }
}
