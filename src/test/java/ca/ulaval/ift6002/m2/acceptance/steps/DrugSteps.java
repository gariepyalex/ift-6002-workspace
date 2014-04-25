package ca.ulaval.ift6002.m2.acceptance.steps;

import static org.hamcrest.Matchers.hasItems;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;

import com.jayway.restassured.response.Response;

public class DrugSteps extends Steps {

    private static final String KEYWORD_SHORTER_THAN_THE_LIMIT = "aa";

    private static final String EXISTING_KEYWORD = "Advil";

    private static final String WILDCARD_KEYWORD = "ANTI CLEAN";

    private String[] expectedDins;

    @BeforeScenario
    public void clearResults() {
        expectedDins = null;
    }

    @When("je cherche un médicament avec moins de caractères que la limite requise")
    public void findDrugsWithLessCharatersThanLimitRequired() {
        findDrugs(KEYWORD_SHORTER_THAN_THE_LIMIT);
    }

    @When("je cherche des médicaments avec un mot-clé qui se retrouve dans quelques noms de médicaments")
    public void findDrugsWithNameKeyword() {
        findDrugs(EXISTING_KEYWORD);

        expectDins("11111111", "22222222");
    }

    @When("je cherche des médicaments avec un mot-clé qui se retrouve dans quelques descriptions de médicaments")
    public void findDrugsWithDescriptionKeyword() {
        findDrugs(EXISTING_KEYWORD);

        expectDins("11111111", "22222222");
    }

    @When("je cherche des médicaments avec un mot-clé qui contient un patron générique")
    public void findDrugsWithWildCard() {
        findDrugs(WILDCARD_KEYWORD);

        expectDins("02239513", "02229501", "02229377");
    }

    @Then("la liste de médicaments retournée contient ceux-ci")
    public void drugsListCorrespondsToTheKeyword() {
        ResponseContext.getResponse().then().assertThat().body("drug.din", hasItems(expectedDins));
    }

    private void findDrugs(String keyword) {
        Response response = new RequestBuilder().doGet("/medicaments/dins/" + keyword);
        ResponseContext.setResponse(response);
    }

    private void expectDins(String... dins) {
        expectedDins = dins;
    }

}
