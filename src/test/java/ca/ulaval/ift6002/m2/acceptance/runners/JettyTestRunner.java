package ca.ulaval.ift6002.m2.acceptance.runners;

import org.jbehave.core.annotations.BeforeStories;

import ca.ulaval.ift6002.m2.Main;

public class JettyTestRunner {

    @BeforeStories
    public void startJetty() throws Exception {
        Main.main(null);
    }
}
