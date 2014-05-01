package ca.ulaval.ift6002.m2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ca.ulaval.ift6002.m2.configuration.factory.HibernateFactoryConfiguration;
import ca.ulaval.ift6002.m2.configuration.persistence.HibernatePersistanceConfiguration;
import ca.ulaval.ift6002.m2.contexts.DemoDrugRepositoryFiller;
import ca.ulaval.ift6002.m2.contexts.DemoPatientRepositoryFiller;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderThreadSafe;

public class Main {

    private static final int HTTP_PORT = 8080;

    public static void main(String[] args) {
        new HibernateFactoryConfiguration().configure();
        new HibernatePersistanceConfiguration().configure();

        EntityManager entityManager = setUpEntityManager();

        entityManager.getTransaction().begin();
        fillDrugRepository();
        fillPatientRepository();
        entityManager.getTransaction().commit();

        JettyServer server = new JettyServer(HTTP_PORT);
        server.start();
        server.join();
        closeEntityManager(entityManager);
    }

    private static EntityManager setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProviderThreadSafe.setEntityManager(entityManager);

        return entityManager;
    }

    private static void fillDrugRepository() {
        new DemoDrugRepositoryFiller().fill();
    }

    private static void fillPatientRepository() {
        new DemoPatientRepositoryFiller().fill();
    }

    private static void closeEntityManager(EntityManager entityManager) {
        EntityManagerProviderThreadSafe.clearEntityManager();
        entityManager.close();
        EntityManagerFactoryProvider.closeFactory();
    }

}
