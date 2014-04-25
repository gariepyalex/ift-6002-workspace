package ca.ulaval.ift6002.m2.acceptance.runners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;

import ca.ulaval.ift6002.m2.JettyServer;
import ca.ulaval.ift6002.m2.acceptance.configuration.TestHibernatePersistanceConfiguration;
import ca.ulaval.ift6002.m2.configuration.factory.HibernateFactoryConfiguration;
import ca.ulaval.ift6002.m2.contexts.IntegrationDrugRepositoryFiller;
import ca.ulaval.ift6002.m2.contexts.IntegrationPatientRepositoryFiller;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderThreadSafe;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class JettyTestRunner {

    public static final int JETTY_TEST_PORT = 8181;
    private JettyServer server;
    private EntityManager entityManager;

    @BeforeStories
    public void startJetty() throws Exception {
        new HibernateFactoryConfiguration().configure();
        new TestHibernatePersistanceConfiguration().configure();

        entityManager = setUpEntityManager();

        entityManager.getTransaction().begin();
        fillDrugRepository();
        fillPatientRepository();
        entityManager.getTransaction().commit();

        server = new JettyServer(JETTY_TEST_PORT);
        server.start();
    }

    @AfterStories
    public void stopJetty() throws Exception {
        closeEntityManager(entityManager);
    }

    private EntityManager setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProviderThreadSafe.setEntityManager(entityManager);

        return entityManager;
    }

    private void fillDrugRepository() {
        new IntegrationDrugRepositoryFiller(RepositoryLocator.getDrugRepository()).fill();
    }

    private void fillPatientRepository() {
        new IntegrationPatientRepositoryFiller().fill();
    }

    private void closeEntityManager(EntityManager entityManager) {
        EntityManagerProviderThreadSafe.clearEntityManager();
        entityManager.close();
        EntityManagerFactoryProvider.closeFactory();
    }
}
