package ca.ulaval.ift6002.m2.acceptance.steps;

import static com.jayway.restassured.RestAssured.given;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class SurgerySteps extends Steps {

    private static final int CREATED_HTTP_CODE = 201;
    private static final String INSTRUMENT_SERIAL_NUMBER = "231-654-65465";
    private static final String INSTRUMENT_TYPE_CODE = "IT72353";
    private static final int PATIENT_NUMBER = 1;
    private Response response;
    private int operationNumber;

    @BeforeScenario
    public void clearResult() {
        response = null;
    }

    @BeforeStory
    public void setUpStories() {
        addValidOperation();
    }

    @Given("une intervention valide est existante")
    public void aValidOperationExists() {

    }

    private void addValidOperation() {
        Response response = given()
                .port(JettyTestRunner.JETTY_TEST_PORT)
                .body("{ \"chirurgien\" : 101224," + "\"description\" : \"operation\","
                        + "\"date\" : \"0000-00-00T24:01:00\"," + "\"salle\" :\"blocB\"," + "\"type\" : \"OEIL\","
                        + "\"statut\" : \"EN_COURS\"," + "\"patient\" : " + PATIENT_NUMBER + "}")
                .contentType(ContentType.JSON).post("/interventions");

        String location = response.header("location");
        operationNumber = Integer.parseInt(location.replaceAll("(.*/interventions)/(.*)", "$2"));
    }

    @When("je cree une intervention")
    public void createSurgery() {
    }

    @When("j'ajoute l'instrument à l'intervention")
    public void addInstrumentToOperation() {
        response = given()
                .contentType(ContentType.JSON)
                .port(JettyTestRunner.JETTY_TEST_PORT)
                .body("{\"typecode\" : \"" + INSTRUMENT_TYPE_CODE + "\" , \"statut\" : \""
                        + InstrumentStatus.SOILED.toString() + "\" , \"noserie\" : \"" + INSTRUMENT_SERIAL_NUMBER
                        + "\" }").when().post("/interventions/{operationNumber}/instruments", operationNumber);
    }

    @Then("cette instrument est conservé")
    public void instrumentIsLinkedWithOperation() {
        response.then().statusCode(CREATED_HTTP_CODE);
    }
}
