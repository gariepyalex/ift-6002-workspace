package ca.ulaval.ift6002.m2.acceptance.steps;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.PrescriptionBuilder;
import ca.ulaval.ift6002.m2.acceptance.fixture.JsonFixture;
import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;
import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class PrescriptionSteps extends Steps {

    private Integer patientId;
    private PrescriptionRequest prescriptionRequest;
    private Response response;
    private JsonFixture jsonFixture = new JsonFixture();

    private static final String INVALID_PRESCRIPTION_ERROR_CODE = "PRES001";

    private static final String ADVIL_DIN = "11111111";
    private static final String INVALID_DIN = "Invalid";

    @BeforeScenario
    public void clearResults() {
        patientId = null;
        response = null;
        prescriptionRequest = null;
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
        response = given().port(JettyTestRunner.JETTY_TEST_PORT).body(jsonFixture.convertToJson(prescriptionRequest))
                .contentType(ContentType.JSON).post("/patient/{patientId}/prescriptions", patientId);
    }

    @Then("une erreur est retournée")
    public void anErrorIsReported() {
        response.then().statusCode(Status.BAD_REQUEST.getStatusCode());
    }

    @Then("cette erreur a le code \"PRES001\"")
    public void thisErrorHasCodeDIN001() {
        response.then().body("code", equalTo(INVALID_PRESCRIPTION_ERROR_CODE));
    }

    @Then("cette prescription est conservée")
    public void presciptionIsSaved() {
        response.then().statusCode(Status.CREATED.getStatusCode());
    }
}
