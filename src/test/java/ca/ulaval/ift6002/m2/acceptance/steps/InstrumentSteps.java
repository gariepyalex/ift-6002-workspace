package ca.ulaval.ift6002.m2.acceptance.steps;

import static org.hamcrest.Matchers.containsString;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.OperationContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.acceptance.fixtures.OperationFixture;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

import com.jayway.restassured.response.Response;

public class InstrumentSteps extends Steps {

    private static final String SERIAL_NUMBER = "231-654-65465";
    private static final String TYPECODE = "IT72353";
    private static final String STATUS = InstrumentStatus.SOILED.toString();

    private OperationFixture operationFixture = new OperationFixture();
    private InstrumentRequest instrumentRequest;

    @BeforeScenario
    public void clearResults() {
        instrumentRequest = null;
    }

    @Given("un instrument valide")
    public void aValidInstrument() {
        instrumentRequest = new InstrumentRequest(TYPECODE, STATUS, SERIAL_NUMBER);
    }

    @Given("un instrument valide associé à cette intervention")
    public void anExistingInstrument() {
        aValidInstrument();

        operationFixture.addInstrumentToExistingOperation(instrumentRequest);
    }

    @Given("un instrument valide anonyme associé à cette intervention")
    public void anExistingAnonymousInstrument() {
        anInstrumentWithoutSerialNumber();

        operationFixture.addInstrumentToExistingOperation(instrumentRequest);
    }

    @Given("un instrument sans numéro de série")
    public void anInstrumentWithoutSerialNumber() {
        instrumentRequest = new InstrumentRequest(TYPECODE, STATUS, "");
    }

    @Given("un instrument sans statut")
    public void anInstrumentWithoutStatut() {
        instrumentRequest = new InstrumentRequest(TYPECODE, "", SERIAL_NUMBER);
    }

    @When("j'ajoute cet instrument à l'intervention")
    public void addInstrumentToOperation() {
        Response response = new RequestBuilder().withContent(instrumentRequest).doPost(
                "/interventions/{operationNumber}/instruments", OperationContext.getOperationNumber());

        ResponseContext.setResponse(response);
    }

    @When("je modifie le statut de cet instrument")
    public void modifiyInstrumentStatus() {
        Response response = new RequestBuilder().withContent(instrumentRequest).doPut(
                "/interventions/{operationNumber}/instruments/{typecode}/{no_serie}",
                OperationContext.getOperationNumber(), TYPECODE, SERIAL_NUMBER);

        ResponseContext.setResponse(response);
    }

    @Then("cet instrument a été ajouté à l'intervention")
    public void instrumentBindedToOperation() {
        ResponseContext
                .getResponse()
                .then()
                .statusCode(Status.CREATED.getStatusCode())
                .header("location",
                        containsString("/interventions/" + OperationContext.getOperationNumber().toString()
                                + "/instruments/" + instrumentRequest.typecode + "/" + instrumentRequest.serial));
    }

    @Then("cet instrument a été modifié")
    public void instrumentHasBeenModified() {
        ResponseContext.getResponse().then().statusCode(Status.OK.getStatusCode());
    }
}
