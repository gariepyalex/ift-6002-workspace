package ca.ulaval.ift6002.m2.acceptance;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

public class PrescriptionSteps extends Steps {

    @Given("a valid prescription with missing data")
    public void validPrescriptionWithMissingData() {

    }

    @When("adding a prescription to the patient")
    public void addingPrescriptions() {

    }

    @Then("an error is reported")
    public void errorIsReported() {

    }

    @Then("this error has code 'PRES001'")
    public void errorCodeIsPRES001() {

    }
}
