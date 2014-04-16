package ca.ulaval.ift6002.m2.acceptance.steps;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;

import com.jayway.restassured.response.Response;

public class DrugSteps extends Steps {

    private static final String KEYWORD_SHORTER_THAN_THE_LIMIT = "aa";

    private static final String INVALID_SEARCH_ERROR_CODE = "DIN001";

    private Response response;

    @BeforeScenario
    public void clearResults() {
        response = null;
    }

    @When("je cherche un medicaments avec moins de caracteres que la limite requise")
    public void findDrugsWithLessCharatersThanLimitRequired() {
        findDrug(KEYWORD_SHORTER_THAN_THE_LIMIT);
    }

    @Then("une erreur est retournee")
    public void anErrorIsReported() {
        response.then().statusCode(Status.BAD_REQUEST.getStatusCode());
    }

    @Then("cette erreur a le code \"DIN001\"")
    public void thisErrorHasCodeDIN001() {
        response.then().body("code", equalTo(INVALID_SEARCH_ERROR_CODE));
    }

    private void findDrug(String drugName) {
        response = given().port(JettyTestRunner.JETTY_TEST_PORT).when().get("/medicaments/dins/" + drugName);
    }

}

// Scénario: Recherche avec trop peu de caractères
// Lorsque je cherche un médicaments avec moins de caractères que la limite
// requise
// Alors une erreur est retournée
// Et cette erreur a le code "DIN001"
