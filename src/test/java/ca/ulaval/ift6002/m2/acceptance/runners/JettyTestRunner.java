package ca.ulaval.ift6002.m2.acceptance.runners;

import org.jbehave.core.annotations.BeforeStories;

import ca.ulaval.ift6002.m2.JettyServer;
import ca.ulaval.ift6002.m2.acceptance.configuration.TestHibernatePersistanceConfiguration;
import ca.ulaval.ift6002.m2.configuration.factory.HibernateFactoryConfiguration;

public class JettyTestRunner {

    public static final int JETTY_TEST_PORT = 8181;
    private JettyServer server;

    @BeforeStories
    public void startJetty() throws Exception {
        // TODO CHANGE THIS TO MOCK
        new HibernateFactoryConfiguration().configure();
        new TestHibernatePersistanceConfiguration().configure();

        server = new JettyServer(JETTY_TEST_PORT);
        server.start();
    }
}
