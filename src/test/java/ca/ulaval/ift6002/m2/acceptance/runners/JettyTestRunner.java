package ca.ulaval.ift6002.m2.acceptance.runners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.mockito.Mockito;

import ca.ulaval.ift6002.m2.JettyServer;
import ca.ulaval.ift6002.m2.acceptance.configuration.TestHibernatePersistanceConfiguration;
import ca.ulaval.ift6002.m2.acceptance.contexts.SurgeryContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.PrescriptionContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.configuration.factory.HibernateFactoryConfiguration;
import ca.ulaval.ift6002.m2.contexts.IntegrationDrugRepositoryFiller;
import ca.ulaval.ift6002.m2.contexts.IntegrationPatientRepositoryFiller;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderGlobal;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class JettyTestRunner {

    public static final int JETTY_TEST_PORT = 8181;
    private JettyServer server;
    private EntityManager entityManager;

    @BeforeStories
    public void startJetty() {
        new HibernateFactoryConfiguration().configure();
        new TestHibernatePersistanceConfiguration().configure();

        entityManager = setUpEntityManager();

        beginTransaction();
        fillDrugRepository();
        fillPatientRepository();
        commitTransaction();

        server = new JettyServer(JETTY_TEST_PORT);
        server.start();
    }

    @AfterStories
    public void stopJetty() {
        closeEntityManager(entityManager);
    }

    @BeforeScenario
    public void clearResults() {
        ResponseContext.reset();
        PatientContext.reset();
        PrescriptionContext.reset();
        SurgeryContext.reset();
    }

    @BeforeScenario
    public void resetSpy() {
        Mockito.reset(RepositoryLocator.getDrugRepository());
        Mockito.reset(RepositoryLocator.getSurgeryRepository());
        Mockito.reset(RepositoryLocator.getPatientRepository());
    }

    @BeforeScenario
    public void setUpScenario() {
        beginTransaction();
    }

    @AfterScenario
    public void tearDownScenario() {
        rollbackTransaction();
    }

    private EntityManager setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProviderGlobal.setEntityManager(entityManager);

        return entityManager;
    }

    private void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    private void commitTransaction() {
        entityManager.getTransaction().commit();
    }

    private void rollbackTransaction() {
        entityManager.getTransaction().rollback();
    }

    private void fillDrugRepository() {
        new IntegrationDrugRepositoryFiller(RepositoryLocator.getDrugRepository()).fill();
    }

    private void fillPatientRepository() {
        new IntegrationPatientRepositoryFiller().fill();
    }

    private void closeEntityManager(EntityManager entityManager) {
        EntityManagerProviderGlobal.clearEntityManager();
        entityManager.close();
        EntityManagerFactoryProvider.closeFactory();
    }
}
