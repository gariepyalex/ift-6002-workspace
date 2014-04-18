package ca.ulaval.ift6002.m2.acceptance.steps;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationData;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class SurgerySteps extends Steps {

    private static final int CREATED_HTTP_CODE = 201;
    private static final String INSTRUMENT_SERIAL_NUMBER = "231-654-65465";
    private static final String INSTRUMENT_TYPE_CODE = "IT72353";
    private static final int PATIENT_NUMBER = 1;
    private Response response;
    private int operationNumber;
    private OperationData data;
    private InstrumentRequest instrumentToPost;
    private OperationRequest operationToPost;

    @BeforeScenario
    public void clearResult() {
        response = null;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                objectMapperConfig().jacksonObjectMapperFactory(new jacksonObjectMapperFactory() {
                    public Gson create(Class cls, String charset) {
                        return new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
                    }
                }));
    }

    @Given("une intervention valide est existante")
    public void aValidOperationExists() {
        addValidOperation();
    }

    private void addValidOperation() {

        operationToPost = getValidOperation();
        String json = given().port(JettyTestRunner.JETTY_TEST_PORT).body(operationToPost).contentType(ContentType.XML)
                .post("/interventions").asString();

        System.out.println(json);
        String location = from(json).get("location");

        operationNumber = Integer.parseInt(location.replaceAll("(/interventions)/(.*)", "$2"));
    }

    private OperationRequest getValidOperation() {
        return new OperationRequest("operation", 101224, "0000-00-00T24:01:00", "blocB", "OEIL", "EN_COURS",
                PATIENT_NUMBER);
    }

    @When("je cree une intervention")
    public void createSurgery() {
    }

    @When("j'ajoute l'instrument à l'intervention")
    public void addInstrumentToOperation() {
        response = given().contentType(ContentType.JSON).port(JettyTestRunner.JETTY_TEST_PORT)
                .param("typecode", INSTRUMENT_TYPE_CODE).param("statut", InstrumentStatus.SOILED.toString())
                .param("noserie", INSTRUMENT_SERIAL_NUMBER).when()
                .post("/interventions/" + operationNumber + "/instruments");
    }

    @Then("cette instrument est conservé")
    public void instrumentIsLinkedWithOperation() {
        response.then().statusCode(CREATED_HTTP_CODE);
        // "/intervention/" + operationNumber + "/instruments/" +
        // INSTRUMENT_TYPE_CODE + "/"
        // + INSTRUMENT_SERIAL_NUMBER
    }
}
