package ca.ulaval.ift6002.m2.acceptance;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;

import java.util.List;
import java.util.Locale;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.PrescriptionContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;
import ca.ulaval.ift6002.m2.acceptance.steps.DrugSteps;
import ca.ulaval.ift6002.m2.acceptance.steps.ErrorSteps;
import ca.ulaval.ift6002.m2.acceptance.steps.PatientSteps;
import ca.ulaval.ift6002.m2.acceptance.steps.PrescriptionSteps;
import ca.ulaval.ift6002.m2.acceptance.steps.SurgerySteps;
import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class DumboTheElephantStories extends JUnitStories {

    private static final LocalizedKeywords KEYWORDS = new LocalizedKeywords(Locale.FRENCH);

    @BeforeScenario
    public void clearResults() {
        ResponseContext.reset();
        PatientContext.reset();
        PrescriptionContext.reset();
    }

    @Override
    public Configuration configuration() {
        StoryReporterBuilder reporterBuilder = new StoryReporterBuilder().withKeywords(KEYWORDS)
                .withCodeLocation(codeLocationFromClass(DumboTheElephantStories.class)).withFailureTrace(true)
                .withFailureTraceCompression(true).withDefaultFormats().withFormats(CONSOLE);

        return new MostUsefulConfiguration().useKeywords(KEYWORDS).usePendingStepStrategy(new FailingUponPendingStep())
                .useStoryParser(new RegexStoryParser(KEYWORDS))
                .useStoryLoader(new LoadFromClasspath(DumboTheElephantStories.class))
                .useStoryReporterBuilder(reporterBuilder);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new ErrorSteps(), new SurgerySteps(), new PrescriptionSteps(),
                new DrugSteps(), new PatientSteps(), new JettyTestRunner());

    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(),
                asList("**/*.story", "*.story"), null);
    }
}
