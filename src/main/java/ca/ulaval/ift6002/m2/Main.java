package ca.ulaval.ift6002.m2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ca.ulaval.ift6002.m2.contexts.DemoDrugRepositoryFiller;
import ca.ulaval.ift6002.m2.domain.drug.CSVDrugParser;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.file.CSVFileReader;
import ca.ulaval.ift6002.m2.domain.file.FileParser;
import ca.ulaval.ift6002.m2.domain.file.FileReader;
import ca.ulaval.ift6002.m2.infrastructure.persistence.factory.HibernateRepositoryFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.factory.RepositoryFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.provider.EntityManagerProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProvider.setEntityManager(entityManager);

        setupRepositoryLocator();
        fillDrugRepository();

        JettyServer server = new JettyServer();
        server.start();

        EntityManagerProvider.clearEntityManager();
        entityManager.close();
        entityManagerFactory.close();

    }

    private static void setupRepositoryLocator() {
        RepositoryFactory hibernateFactory = new HibernateRepositoryFactory();
        RepositoryLocator repositoryLocator = new RepositoryLocator();

        repositoryLocator.register(DrugRepository.class, hibernateFactory.createDrugRepository());

        RepositoryLocator.load(repositoryLocator);
    }

    private static void fillDrugRepository() {
        FileReader<String[]> fileReader = new CSVFileReader();
        FileParser<Drug> drugParser = new CSVDrugParser(fileReader);
        new DemoDrugRepositoryFiller(RepositoryLocator.getDrugRepository(), drugParser).fill();
    }

}
