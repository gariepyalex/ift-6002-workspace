package ca.ulaval.ift6002.m2.acceptance.steps;

import static com.jayway.restassured.RestAssured.given;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.context.ResponseContext;
import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;

import com.jayway.restassured.response.Response;

public class DrugSteps extends Steps {

    private static final String KEYWORD_SHORTER_THAN_THE_LIMIT = "aa";

    @BeforeScenario
    public void clearResults() {
        ResponseContext.reset();
    }

    @When("je cherche un medicaments avec moins de caracteres que la limite requise")
    public void findDrugsWithLessCharatersThanLimitRequired() {
        findDrug(KEYWORD_SHORTER_THAN_THE_LIMIT);
    }

    private void findDrug(String drugName) {
        Response response = given().port(JettyTestRunner.JETTY_TEST_PORT).when().get("/medicaments/dins/" + drugName);
        ResponseContext.init(response);
    }

}

// Scénario: Recherche avec trop peu de caractères
// Lorsque je cherche un médicaments avec moins de caractères que la limite
// requise
// Alors une erreur est retournée
// Et cette erreur a le code "DIN001"
