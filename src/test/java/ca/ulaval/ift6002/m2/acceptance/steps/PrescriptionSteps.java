package ca.ulaval.ift6002.m2.acceptance.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

public class PrescriptionSteps extends Steps {

    @Given("une prescription valide")
    public void validPrescriptio() {

    }

    @Given("avec des données manquantes")
    public void validPrescriptionWithMissingData() {

    }

    @When("j'ajoute cette prescription au dossier du patient")
    public void addingPrescriptions() {
    }

    @Then("une erreur est retournée")
    public void errorIsReported() {
    }

    @Then("cette erreur a le code 'PRES001'")
    public void errorCodeIsPRES001() {

    }
}
