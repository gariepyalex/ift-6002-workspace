package ca.ulaval.ift6002.m2.acceptance.steps;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.PrescriptionRequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.PrescriptionContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;

import com.jayway.restassured.response.Response;

public class PrescriptionSteps extends Steps {

    private static final String ADVIL_DIN = "11111111";
    private static final String ADVIL_NAME = "Advil turbo";

    private static final String INVALID_DIN = "Invalid";
    private static final int INVALID_RENEWALS = -1;
    private static final Integer EXISTING_PATIENT_PRESCRIPTION = 1;

    private PrescriptionRequest prescriptionRequest;

    @BeforeScenario
    public void clearResults() {
        prescriptionRequest = null;
        ResponseContext.reset();
        PrescriptionContext.reset();
    }

    @Given("une prescription avec des données manquantes")
    public void aPrescriptionWithMissingData() {
        prescriptionRequest = new PrescriptionRequestBuilder().build();
    }

    @Alias("une prescription valide")
    @Given("une prescription valide avec DIN")
    public void aValidPrescriptionWithDin() {
        prescriptionRequest = new PrescriptionRequestBuilder().din(ADVIL_DIN).build();
        PrescriptionContext.setPrescriptionId(EXISTING_PATIENT_PRESCRIPTION);
    }

    @Given("une prescription avec un DIN inexistant")
    public void aPrescriptionWithInexistantDin() {
        prescriptionRequest = new PrescriptionRequestBuilder().din(INVALID_DIN).build();
    }

    @Given("une prescription avec nom de médicament")
    public void aValidPrescriptionWithDrugName() {
        prescriptionRequest = new PrescriptionRequestBuilder().name(ADVIL_NAME).build();
    }

    @Given("une prescription avec un DIN et un nom de médicament")
    public void anInvalidPrescriptionWithBothNameAndDin() {
        prescriptionRequest = new PrescriptionRequestBuilder().din(ADVIL_DIN).name(ADVIL_NAME).build();
    }

    @Given("une prescription avec un nombre de renouvellements invalide")
    public void aPrescriptionWithInvalidRenewals() {
        prescriptionRequest = new PrescriptionRequestBuilder().renewals(INVALID_RENEWALS).din(ADVIL_DIN).build();
    }

    @When("j'ajoute cette prescription au dossier du patient")
    public void addingThePrescriptionWithMissingData() {
        Response response = new RequestBuilder().withContent(prescriptionRequest).doPost(
                "/patient/{patientId}/prescriptions", PatientContext.getPatientId());

        ResponseContext.setResponse(response);
    }

    @Then("cette prescription est conservée")
    public void prescriptionIsSaved() {
        ResponseContext.getResponse().then().statusCode(Status.CREATED.getStatusCode());
    }

}
