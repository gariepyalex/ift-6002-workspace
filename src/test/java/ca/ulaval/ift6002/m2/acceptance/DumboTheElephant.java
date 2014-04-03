package ca.ulaval.ift6002.m2.acceptance;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.junit.JUnitStories;

public class DumboTheElephant extends JUnitStories {

    public DumboTheElephant() {
        super();
        this.configuredEmbedder().candidateSteps().add(new PrescriptionSteps());
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays
                .asList("ca/ulaval/ift6002/m2/acceptance/AddPrescription.story");
    }
}
