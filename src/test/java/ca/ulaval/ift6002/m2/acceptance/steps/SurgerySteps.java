package ca.ulaval.ift6002.m2.acceptance.steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationData;

import com.jayway.restassured.response.Response;

public class SurgerySteps extends Steps {

    private Response response;
    private Operation surgery;
    private OperationData data;

    @BeforeScenario
    public void clearResult() {
        response = null;
    }

    @When("je cree une intervention")
    public void createSurgery() {
    }
}
