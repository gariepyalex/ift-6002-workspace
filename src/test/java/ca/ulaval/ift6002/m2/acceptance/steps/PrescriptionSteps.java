package ca.ulaval.ift6002.m2.acceptance.steps;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.DumboTheElephantStories;
import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;
import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class PrescriptionSteps extends Steps {

    private Integer patientId;
    private PrescriptionRequest prescriptionToPost;
    private Response response;

    private final String INVALID_PRESCRIPTION_ERROR_CODE = "PRES001";

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

    @When("j'ajoute cette prescription au dossier du patient")
    public void addingThePrescriptionWithMissingData() {
        try {
            response = given().port(JettyTestRunner.JETTY_TEST_PORT)
                    .body(DumboTheElephantStories.OBJECT_MAPPER.writeValueAsString(prescriptionToPost))
                    .contentType(ContentType.JSON).post("/patient/{patientId}/prescriptions", patientId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Then("une erreur est retournée")
    public void anErrorIsReported() {
        response.then().statusCode(Status.BAD_REQUEST.getStatusCode());
    }

    @Then("cette erreur a le code \"PRES001\"")
    public void thisErrorHasCodeDIN001() {
        response.then().body("code", equalTo(INVALID_PRESCRIPTION_ERROR_CODE));
    }
}
