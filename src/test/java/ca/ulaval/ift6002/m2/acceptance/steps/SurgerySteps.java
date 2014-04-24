package ca.ulaval.ift6002.m2.acceptance.steps;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

import com.jayway.restassured.response.Response;

public class SurgerySteps extends Steps {

    private static final String A_DESCRIPTION = "description";
    private static final int A_SURGEON_NUMBER = 101224;
    private static final String A_DATE = "0000-00-00T24:01:00";
    private static final String A_ROOM = "room";
    private static final String A_VALID_TYPE = "OEIL";
    private static final String A_VALID_STATUS = "EN_COURS";

    private static final String INSTRUMENT_SERIAL_NUMBER = "231-654-65465";
    private static final String INSTRUMENT_TYPE_CODE = "IT72353";

    private int operationNumber;
    private InstrumentRequest instrumentToPost;

    private OperationRequest operationRequest;

    // TODO is all this crap really useful?!
    // @BeforeStory
    // public void setUpStories() {
    // addValidOperation();
    // }
    //
    // private void addValidOperation() {
    // aValidOperation();
    //
    // Response response = new
    // RequestBuilder().withContent(operationRequest).doPost("/interventions");
    // ResponseContext.setResponse(response);
    //
    // String location = response.header("location");
    // operationNumber =
    // Integer.parseInt(location.replaceAll("(.*/interventions)/(.*)", "$2"));
    // }

    @Given("une intervention avec des informations manquantes")
    public void anOperationWithMissingData() {
        operationRequest = new OperationRequest("", 0, "", "", "", "", PatientContext.getPatientId());
    }

    @Given("une intervention valide")
    public void aValidOperation() {
        operationRequest = new OperationRequest(A_DESCRIPTION, A_SURGEON_NUMBER, A_DATE, A_ROOM, A_VALID_TYPE,
                A_VALID_STATUS, PatientContext.getPatientId());
    }

    @Given("une intervention valide sans statut")
    public void aValidOperationWithoutStatus() {
        aValidOperation();
        operationRequest.status = "";
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
        // TODO We must validate this criteria as well... see how to do it
        Assert.assertEquals("PLANIFIEE", operationRequest.status); // stupid try
    }

    // TODO Remove all this crap related to instrument...
    @Given("une intervention est existante")
    public void anExistingOperation() {

    }

    @Given("un instrument est existant")
    public void anExistingInstrument() {

    }

    @When("j'ajoute l'instrument à l'intervention")
    public void addInstrumentToOperation() {
        instrumentToPost = new InstrumentRequest(INSTRUMENT_TYPE_CODE, InstrumentStatus.SOILED.toString(),
                INSTRUMENT_SERIAL_NUMBER);

        Response response = new RequestBuilder().withContent(instrumentToPost).doPost(
                "/interventions/{operationNumber}/instruments", operationNumber);

        ResponseContext.setResponse(response);

    }

    @When("je modifie le statut d'un instrument")
    public void modifyingInstrumentStatus() {
        instrumentToPost = new InstrumentRequest(INSTRUMENT_TYPE_CODE, InstrumentStatus.UNUSED.toString(),
                INSTRUMENT_SERIAL_NUMBER);

        Response response = new RequestBuilder().withContent(instrumentToPost)
                .doPut("/interventions/{operationNumber}/instruments/" + INSTRUMENT_TYPE_CODE + "/"
                        + INSTRUMENT_SERIAL_NUMBER, operationNumber);
        ResponseContext.setResponse(response);

    }

    @Then("cet instrument a été ajouté à l'opération")
    public void instrumentIsLinkedWithOperation() {
        String expectedLocation = "http://localhost:" + JettyTestRunner.JETTY_TEST_PORT + "/interventions/"
                + operationNumber + "/instruments/" + INSTRUMENT_TYPE_CODE + "/" + INSTRUMENT_SERIAL_NUMBER;

        ResponseContext.getResponse().then().statusCode(Status.CREATED.getStatusCode());
        ResponseContext.getResponse().then().header("location", expectedLocation);
    }

    @Then("cet instrument a été modifié")
    public void instrumentHasBeenModified() {
        ResponseContext.getResponse().then().statusCode(Status.OK.getStatusCode());
    }
}
