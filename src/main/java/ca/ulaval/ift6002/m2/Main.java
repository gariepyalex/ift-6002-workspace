package ca.ulaval.ift6002.m2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.factory.InMemoryRepositoryFactory;
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

        JettyServer server = new JettyServer();
        server.init();

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EntityManagerProvider.clearEntityManager();
        entityManager.close();
        entityManagerFactory.close();

    }

    private static void setupRepositoryLocator() {
        RepositoryFactory inMemoryFactory = new InMemoryRepositoryFactory();
        RepositoryLocator repositoryLocator = new RepositoryLocator();

        repositoryLocator.register(DrugRepository.class, inMemoryFactory.createDrugRepository());

        RepositoryLocator.load(repositoryLocator);
    }

}
