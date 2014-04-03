package ca.ulaval.ift6002.m2.acceptance;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.domain.patient.Patient;

public class PatientSteps extends Steps {

    private Patient patient;

    @Given("an existing patient")
    public void anExistingPatient() {
    }

}
