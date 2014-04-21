package ca.ulaval.ift6002.m2.acceptance.steps;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;

public class ErrorSteps extends Steps {

    @Then("une erreur est retournée")
    public void anErrorIsReported() {
        ResponseContext.getResponse().then().statusCode(not(Status.OK.getStatusCode()));
    }

    @Then("cette erreur a le code \"$errorCode\"")
    public void thisErrorHasCodeDIN001(@Named("errorCode") String errorCode) {
        ResponseContext.getResponse().then().body("code", equalTo(errorCode));
    }
}
