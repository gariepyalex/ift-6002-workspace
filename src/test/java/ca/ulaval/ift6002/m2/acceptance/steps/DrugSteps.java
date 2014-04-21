package ca.ulaval.ift6002.m2.acceptance.steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;

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
        Response response = new RequestBuilder().doGet("/medicaments/dins/" + drugName);

        ResponseContext.init(response);
    }

}
