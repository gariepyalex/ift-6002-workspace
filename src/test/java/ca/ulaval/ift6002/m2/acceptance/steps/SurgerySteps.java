package ca.ulaval.ift6002.m2.acceptance.steps;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.application.requests.OperationRequest;

import com.jayway.restassured.response.Response;

public class SurgerySteps extends Steps {

    private static final String A_DESCRIPTION = "description";
    private static final int A_SURGEON_NUMBER = 101224;
    private static final String A_DATE = "0000-00-00T24:01:00";
    private static final String A_ROOM = "room";
    private static final String A_VALID_TYPE = "OEIL";
    private static final String A_VALID_STATUS = "EN_COURS";

    private OperationRequest operationRequest;

    @Given("une intervention avec des informations manquantes")
    public void anOperationWithMissingData() {
        operationRequest = new OperationRequest("", 0, "", "", "", "", PatientContext.getPatientNumber());
    }

    @Given("une intervention valide")
    public void aValidOperation() {
        operationRequest = new OperationRequest(A_DESCRIPTION, A_SURGEON_NUMBER, A_DATE, A_ROOM, A_VALID_TYPE,
                A_VALID_STATUS, PatientContext.getPatientNumber());
    }

    @Given("une intervention valide sans statut")
    public void aValidOperationWithoutStatus() {
        aValidOperation();
        operationRequest.status = "";
    }

    @Given("une intervention existante")
    public void anExistingOperation() {
        // TODO have real number, OperationFixture
    }

    @When("j'ajoute cette intervention au dossier du patient")
    public void createSurgery() {
        Response response = new RequestBuilder().withContent(operationRequest).doPost("/interventions/");
        ResponseContext.setResponse(response);
    }

    @Then("cette intervention est conservée")
    public void anOperationIsSaved() {
        ResponseContext.getResponse().then().statusCode(Status.CREATED.getStatusCode());
    }

    @Then("cette intervention est associée au dossier du patient")
    public void operationIsLinkedToPatient() {
        // TODO We must validate this criteria as well.. I'm confused on how to
        // do it
    }

    @Then("le statut de cette intervention est à \"PLANIFIEE\"")
    public void operationStatusIsSetToPlanified() {
        // TODO review if this is necessary also...
        // TODO We must validate this criteria as well... see how to do it
        // Assert.assertEquals("PLANIFIEE", operationRequest.status); // stupid
        // try
    }
}
