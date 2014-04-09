package ca.ulaval.ift6002.m2.acceptance;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.junit.JUnitStories;
import org.junit.runner.RunWith;

import ca.ulaval.ift6002.m2.acceptance.steps.PatientSteps;
import ca.ulaval.ift6002.m2.acceptance.steps.PrescriptionSteps;
import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class DumboTheElephant extends JUnitStories {

    public DumboTheElephant() {
        super();
        this.configuredEmbedder().candidateSteps().add(new PrescriptionSteps());
        this.configuredEmbedder().candidateSteps().add(new PatientSteps());
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays
                .asList("ca/ulaval/ift6002/m2/acceptance/AddPrescription.story");
    }
}
