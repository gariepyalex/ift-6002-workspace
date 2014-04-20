package ca.ulaval.ift6002.m2.acceptance.steps;

import static com.jayway.restassured.RestAssured.given;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.DumboTheElephantStories;
import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class SurgerySteps extends Steps {

    private static final int SURGEON_NUMBER = 101224;
    private static final String INSTRUMENT_SERIAL_NUMBER = "231-654-65465";
    private static final String INSTRUMENT_TYPE_CODE = "IT72353";
    private static final int PATIENT_NUMBER = 1;
    private Response response;
    private int operationNumber;
    private OperationRequest operationToPost;
    private InstrumentRequest instrumentToPost;

    @BeforeScenario
    public void clearResult() {
        response = null;

    }

    @BeforeStory
    public void setUpStories() {
        addValidOperation();
    }

    private void addValidOperation() {
        operationToPost = getValidOperation();

        try {
            response = given().port(JettyTestRunner.JETTY_TEST_PORT)
                    .body(DumboTheElephantStories.OBJECT_MAPPER.writeValueAsString(operationToPost))
                    .contentType(ContentType.JSON).post("/interventions");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String location = response.header("location");
        operationNumber = Integer.parseInt(location.replaceAll("(.*/interventions)/(.*)", "$2"));
    }

    private OperationRequest getValidOperation() {
        return new OperationRequest("description", SURGEON_NUMBER, "0000-00-00T24:01:00", "blocB", "OEIL", "EN_COURS",
                PATIENT_NUMBER);
    }

    @Given("une intervention est existante")
    public void anExistingOperation() {

    }

    @Given("un instrument est existant")
    public void anExistingInstrument() {

    }

    @When("je cree une intervention")
    public void createSurgery() {

    }

    @When("j'ajoute l'instrument à l'intervention")
    public void addInstrumentToOperation() {
        instrumentToPost = new InstrumentRequest(INSTRUMENT_TYPE_CODE, InstrumentStatus.SOILED.toString(),
                INSTRUMENT_SERIAL_NUMBER);
        try {
            response = given().contentType(ContentType.JSON).port(JettyTestRunner.JETTY_TEST_PORT)
                    .body(DumboTheElephantStories.OBJECT_MAPPER.writeValueAsString(instrumentToPost))
                    .post("/interventions/{operationNumber}/instruments", operationNumber);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @When("je modifie le statut d'un instrument")
    public void modifyingInstrumentStatus() {
        instrumentToPost = new InstrumentRequest(INSTRUMENT_TYPE_CODE, InstrumentStatus.UNUSED.toString(),
                INSTRUMENT_SERIAL_NUMBER);
        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .port(JettyTestRunner.JETTY_TEST_PORT)
                    .body(DumboTheElephantStories.OBJECT_MAPPER.writeValueAsString(instrumentToPost))
                    .put("/interventions/{operationNumber}/instruments/" + INSTRUMENT_TYPE_CODE + "/"
                            + INSTRUMENT_SERIAL_NUMBER, operationNumber);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Then("cette instrument a été ajouté à l'opération")
    public void instrumentIsLinkedWithOperation() {
        String expectedLocation = "http://localhost:" + JettyTestRunner.JETTY_TEST_PORT + "/interventions/"
                + operationNumber + "/instruments/" + INSTRUMENT_TYPE_CODE + "/" + INSTRUMENT_SERIAL_NUMBER;

        response.then().statusCode(Status.CREATED.getStatusCode());
        response.then().header("location", expectedLocation);
    }

    @Then("cette instrument a été modifié")
    public void instrumentHasBeenModified() {
        response.then().statusCode(Status.OK.getStatusCode());
    }
}