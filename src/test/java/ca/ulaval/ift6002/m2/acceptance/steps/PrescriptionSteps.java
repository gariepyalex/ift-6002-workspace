package ca.ulaval.ift6002.m2.acceptance.steps;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.fixture.JsonFixture;
import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;
import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class PrescriptionSteps extends Steps {

    private Integer patientId;
    private PrescriptionRequest prescriptionToPost;
    private Response response;
    private JsonFixture jsonFixture = new JsonFixture();

    private static final String INVALID_PRESCRIPTION_ERROR_CODE = "PRES001";

    @BeforeScenario
    public void clearResults() {
        patientId = null;
        response = null;
        prescriptionToPost = null;
    }

    @Given("un patient est existant")
    public void anExistingPatient() {
        patientId = 1;
    }

    @Given("une prescription a des données manquantes")
    public void aPrescriptionWithMissingData() {
        prescriptionToPost = getPrescriptionWithMissingData();
    }

    private PrescriptionRequest getPrescriptionWithMissingData() {
        return new PrescriptionRequest("0asd139", "2001-07-04T12:08:56", 0, "", "");
    }

    @Given("une prescription avec DIN est valide")
    public void aValidPrescriptionWithDin() {
        prescriptionToPost = getValidPrescriptionWithDin();
    }

    private PrescriptionRequest getValidPrescriptionWithDin() {
        return new PrescriptionRequest("1233sdsd", "2007-09-12T06:08:06", 4, "11111111", "");
    }

    @Given("une prescription valide a un DIN inexistant")
    public void aPrescriptionWithInexistantDin() {
        prescriptionToPost = getValidPrescriptionWithInexistantDin();
    }

    private PrescriptionRequest getValidPrescriptionWithInexistantDin() {
        return new PrescriptionRequest("tomtom", "2011-05-14T12:08:56", 1, "INEXISTANT", "");
    }

    @Given("une prescription avec nom de médicament est valide")
    public void aValidPrescriptionWithDrugName() {
        prescriptionToPost = getValidPrescriptionWithDrugName();
    }

    private PrescriptionRequest getValidPrescriptionWithDrugName() {
        return new PrescriptionRequest("1asd", "2014-01-12T00:08:06", 1, "", "Advil turbo");
    }

    @Given("une prescription avec un DIN et un nom est invalide")
    public void anInvalidPrescriptionWithBothNameAndDin() {
        prescriptionToPost = getValidPrescriptionWithBothDinAndName();
    }

    private PrescriptionRequest getValidPrescriptionWithBothDinAndName() {
        return new PrescriptionRequest("toto", "2014-01-12T00:08:06", 8, "11111111", "Advil turbo");
    }

    @Given("une prescription a un nombre de renouvellements invalide")
    public void aPrescriptionWithInvalidRenewals() {
        prescriptionToPost = getPrescriptionWithInvalidRenewals();
    }

    private PrescriptionRequest getPrescriptionWithInvalidRenewals() {
        return new PrescriptionRequest("23jjks", "2004-11-12T00:38:06", -1, "", "Super flu counter");
    }

    @When("j'ajoute cette prescription au dossier du patient")
    public void addingThePrescriptionWithMissingData() {
        response = given().port(JettyTestRunner.JETTY_TEST_PORT).body(jsonFixture.convertToJson(prescriptionToPost))
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
