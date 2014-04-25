package ca.ulaval.ift6002.m2.acceptance.steps;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

import com.jayway.restassured.response.Response;

public class InstrumentSteps extends Steps {

    private static final String AN_EXISTING_SERIAL_NUMBER = "231-654-65465";

    private static final String SERIAL_NUMBER = "231-654-65465";
    private static final String TYPECODE = "IT72353";
    private static final String STATUS = InstrumentStatus.SOILED.toString();

    private InstrumentRequest instrumentRequest;

    @BeforeScenario
    public void clearResults() {
        instrumentRequest = null;
    }

    @Given("un instrument valide")
    public void aValidInstrument() {
        instrumentRequest = new InstrumentRequest(TYPECODE, STATUS, SERIAL_NUMBER);
    }

    @Given("un instrument existant")
    public void anExistingInstrument() {
        // TODO see how to have an existing instrument also
        instrumentRequest = new InstrumentRequest(TYPECODE, STATUS, AN_EXISTING_SERIAL_NUMBER);
    }

    @Given("un instrument sans statut")
    public void anInstrumentWithoutStatut() {
        instrumentRequest = new InstrumentRequest(TYPECODE, "", SERIAL_NUMBER);
    }

    @When("j'ajoute cet instrument à l'intervention")
    public void addInstrumentToOperation() {
        // TODO link with OperationFixture
        int operationNumber = 1;

        Response response = new RequestBuilder().withContent(instrumentRequest).doPost(
                "/interventions/{operationNumber}/instruments", operationNumber);

        ResponseContext.setResponse(response);
    }

    @When("je modifie le statut de cet instrument")
    public void modifiyInstrumentStatus() {
        // TODO link with OperationFixture
        int operationNumber = 1;

        Response response = new RequestBuilder().withContent(instrumentRequest).doPut(
                "/interventions/{operationNumber}/instruments/{typecode}/{no_serie}", operationNumber,
                instrumentRequest.typecode, instrumentRequest.serial);

        ResponseContext.setResponse(response);
    }

    @Then("cet instrument a été ajouté à l'intervention")
    public void instrumentBindedToOperation() {
        ResponseContext.getResponse().then().statusCode(Status.CREATED.getStatusCode());
        // TODO assert header
    }

    @Then("cet instrument a été modifié")
    public void instrumentHasBeenModified() {
        ResponseContext.getResponse().then().statusCode(Status.OK.getStatusCode());
    }

}
