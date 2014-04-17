package ca.ulaval.ift6002.m2.acceptance.steps;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;
import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.OperationData;
import ca.ulaval.ift6002.m2.services.OperationService;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class SurgerySteps extends Steps {

    private static final int CREATED_HTTP_CODE = 201;
    private static final String INSTRUMENT_SERIAL_NUMBER = "231-654-65465";
    private static final String INSTRUMENT_TYPE_CODE = "IT72353";
    private static final int SURGEON_NUMBER = 110124;
    private Response response;
    private int operationNumber;
    private OperationData data;
    private OperationService operationService = new OperationService();

    @BeforeScenario
    public void clearResult() {
        response = null;
    }

    @Given("une intervention valide")
    public void getValidOperation() {
        addValidOperation();
    }

    public void addValidOperation() {
        DateFormatter dateFormatter = new DateFormatter();
        OperationRequest operationRequest = new OperationRequest("operation", SURGEON_NUMBER,
                dateFormatter.dateToString(new Date()), "blocB", "coeur", "planifiee", 1);
        operationNumber = operationService.saveOperation(operationRequest);
    }

    @When("je cree une intervention")
    public void createSurgery() {
    }

    @When("j'ajoute l'instrument à l'intervention")
    public void addInstrumentToOperation() {
        response = given().contentType(ContentType.JSON).port(JettyTestRunner.JETTY_TEST_PORT)
                .param("typecode", INSTRUMENT_TYPE_CODE).param("status", "SOUILLE")
                .param("noserie", INSTRUMENT_SERIAL_NUMBER).when()
                .post("/intervetions/" + operationNumber + "/instruments/");
    }

    @Then("cette instrument est conservé")
    public void instrumentIsLinkedWithOperation() {
        response.then()
                .body("location",
                        equalTo("/intervention/" + operationNumber + "/instruments/" + INSTRUMENT_TYPE_CODE + "/"
                                + INSTRUMENT_SERIAL_NUMBER)).statusCode(CREATED_HTTP_CODE);
    }
}
