package ca.ulaval.ift6002.m2.acceptance.steps;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.OperationContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.acceptance.fixtures.OperationFixture;
import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;
import ca.ulaval.ift6002.m2.services.OperationService;

import com.jayway.restassured.response.Response;

public class OperationSteps extends Steps {

    private static final String A_DESCRIPTION = "description";
    private static final int A_SURGEON_NUMBER = 101224;
    private static final String A_DATE = "0000-00-00T24:01:00";
    private static final String A_ROOM = "room";
    private static final String A_VALID_TYPE = "OEIL";
    private static final String A_VALID_STATUS = "EN_COURS";
    private static final String NO_STATUS = "";

    private OperationRequest operationRequest;

    private OperationFixture operationFixture = new OperationFixture();

    @Given("une intervention avec des informations manquantes")
    public void anOperationWithMissingData() {
        operationRequest = new OperationRequest("", 0, "", "", "", "", PatientContext.getPatientNumber());
    }

    @Given("une intervention valide")
    public void aValidOperation() {
        operationRequest = new OperationRequest(A_DESCRIPTION, A_SURGEON_NUMBER, A_DATE, A_ROOM, A_VALID_TYPE,
                A_VALID_STATUS, PatientContext.getPatientNumber());
    }

    @Given("une intervention existante")
    public void anExistingOperation() {
        operationFixture.setupExistingOperation();
    }

    @Given("une intervention existante dangereuse")
    public void anExistingDangerousOperation() {
        operationFixture.setupExistingRestrictedOperation();
    }

    @Given("une intervention valide sans statut")
    public void aValidOperationWithoutStatus() {
        operationRequest = new OperationRequest(A_DESCRIPTION, A_SURGEON_NUMBER, A_DATE, A_ROOM, A_VALID_TYPE,
                NO_STATUS, PatientContext.getPatientNumber());
    }

    @When("j'ajoute cette intervention au dossier du patient")
    public void createSurgery() {
        Response response = new RequestBuilder().withContent(operationRequest).doPost("/interventions/");
        ResponseContext.setResponse(response);
    }

    @When("j'ajoute cette intervention sans statut au dossier du patient")
    public void createSurgeryWithService() {
        OperationService operationService = new OperationService();
        int operationNumber = operationService.saveOperation(operationRequest);
        Operation operation = RepositoryLocator.getOperationRepository().get(operationNumber);
        OperationContext.setOperation(operation);
    }

    @Then("cette intervention est conservée")
    public void anOperationIsSaved() {
        verify(RepositoryLocator.getOperationRepository()).store(any(Operation.class));
    }

    @Then("le statut de cette intervention est à \"PLANIFIEE\"")
    public void anStatusIsPlanned() {
        Operation operation = OperationContext.getOperation();
        assertTrue(operation.hasStatus(OperationStatus.PLANNED));
    }

}
