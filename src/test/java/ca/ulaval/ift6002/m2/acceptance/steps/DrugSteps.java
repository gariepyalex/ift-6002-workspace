package ca.ulaval.ift6002.m2.acceptance.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;

import com.jayway.restassured.response.Response;

public class DrugSteps extends Steps {

    private static final String KEYWORD_SHORTER_THAN_THE_LIMIT = "aa";

    private static final String EXISTING_KEYWORD = "Advil";

    private static final String WILDCARD_KEYWORD = "Advil liqui";

    @When("je cherche un médicament avec moins de caractères que la limite requise")
    public void findDrugsWithLessCharatersThanLimitRequired() {
        findDrugs(KEYWORD_SHORTER_THAN_THE_LIMIT);
    }

    @When("je cherche des médicaments avec un mot-clé qui se retrouve dans quelques noms de médicaments")
    public void findDrugsWithNameKeyword() {
        findDrugs(EXISTING_KEYWORD);
    }

    @When("je cherche des médicaments avec un mot-clé qui se retrouve dans quelques descriptions de médicaments")
    public void findDrugsWithDescriptionKeyword() {
        findDrugs(EXISTING_KEYWORD);
    }

    @When("je cherche des médicaments avec un mot-clé qui contient un patron générique")
    public void findDrugsWithWildCard() {
        findDrugs(WILDCARD_KEYWORD);
    }

    @Then("la liste de médicaments retournée contient ceux-ci")
    public void drugsListCorrespondsToTheKeyword() {
        // TODO assert something... non empty list? success?? ask teacher...
    }

    private void findDrugs(String keyword) {
        Response response = new RequestBuilder().doGet("/medicaments/dins/" + keyword);
        ResponseContext.setResponse(response);
    }

}
