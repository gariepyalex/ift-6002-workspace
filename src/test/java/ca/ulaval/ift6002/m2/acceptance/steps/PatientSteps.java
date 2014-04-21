package ca.ulaval.ift6002.m2.acceptance.steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;

import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;

public class PatientSteps {

    private static final Integer EXISTING_PATIENT_ID = 1;

    private static final Integer UNEXISTING_PATIENT_ID = -999;

    @BeforeScenario
    public void clearResults() {
        ResponseContext.reset();
        PatientContext.reset();
    }

    @Given("un patient est existant")
    public void anExistingPatient() {
        PatientContext.setPatientId(EXISTING_PATIENT_ID);
    }

    @Given("un patient est inexistant")
    public void anUnexistingPatient() {
        PatientContext.setPatientId(UNEXISTING_PATIENT_ID);
    }
}
