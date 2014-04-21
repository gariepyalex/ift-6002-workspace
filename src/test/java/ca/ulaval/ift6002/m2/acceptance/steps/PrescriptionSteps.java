package ca.ulaval.ift6002.m2.acceptance.steps;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.PrescriptionBuilder;
import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;

import com.jayway.restassured.response.Response;

public class PrescriptionSteps extends Steps {

    private static final String ADVIL_DIN = "11111111";
    private static final String INVALID_DIN = "Invalid";

    private Integer patientId;
    private PrescriptionRequest prescriptionRequest;

    @BeforeScenario
    public void clearResults() {
        patientId = null;
        prescriptionRequest = null;
        ResponseContext.reset();
    }

    @Given("un patient est existant")
    public void anExistingPatient() {
        patientId = 1;
    }

    @Given("une prescription avec des données manquantes")
    public void aPrescriptionWithMissingData() {
        prescriptionRequest = new PrescriptionBuilder().buildRequest();
    }

    @Given("une prescription avec DIN")
    public void aValidPrescriptionWithDin() {
        prescriptionRequest = new PrescriptionBuilder().din(ADVIL_DIN).buildRequest();
    }

    @Given("une prescription avec un DIN inexistant")
    public void aPrescriptionWithInexistantDin() {
        prescriptionRequest = new PrescriptionBuilder().din(INVALID_DIN).buildRequest();
    }

    @Given("une prescription avec nom de médicament")
    public void aValidPrescriptionWithDrugName() {
        prescriptionRequest = new PrescriptionBuilder().name("Advil turbo").buildRequest();
    }

    @Given("une prescription avec un DIN et un nom de médicament")
    public void anInvalidPrescriptionWithBothNameAndDin() {
        prescriptionRequest = new PrescriptionBuilder().din(ADVIL_DIN).name("Advil turbo").buildRequest();
    }

    @Given("une prescription avec un nombre de renouvellements invalide")
    public void aPrescriptionWithInvalidRenewals() {
        prescriptionRequest = new PrescriptionBuilder().renewals(-1).din(ADVIL_DIN).buildRequest();
    }

    @When("j'ajoute cette prescription au dossier du patient")
    public void addingThePrescriptionWithMissingData() {
        Response response = new RequestBuilder().withContent(prescriptionRequest).doPost(
                "/patient/{patientId}/prescriptions", patientId);

        ResponseContext.init(response);
    }

    @Then("cette prescription est conservée")
    public void presciptionIsSaved() {
        ResponseContext.getResponse().then().statusCode(Status.CREATED.getStatusCode());
    }
}
