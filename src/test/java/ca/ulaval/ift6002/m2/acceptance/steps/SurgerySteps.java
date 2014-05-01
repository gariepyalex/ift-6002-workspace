package ca.ulaval.ift6002.m2.acceptance.steps;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.SurgeryContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.acceptance.fixtures.SurgeryFixture;
import ca.ulaval.ift6002.m2.application.requests.SurgeryRequest;
import ca.ulaval.ift6002.m2.domain.surgery.Surgery;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryStatus;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;
import ca.ulaval.ift6002.m2.services.SurgeryService;

import com.jayway.restassured.response.Response;

public class SurgerySteps extends Steps {

    private static final String A_DESCRIPTION = "description";
    private static final int A_SURGEON_NUMBER = 101224;
    private static final String A_DATE = "0000-00-00T24:01:00";
    private static final String A_ROOM = "room";
    private static final String A_VALID_TYPE = "OEIL";
    private static final String A_VALID_STATUS = "EN_COURS";
    private static final String NO_STATUS = "";

    private SurgeryRequest surgeryRequest;

    private SurgeryFixture surgeryFixture = new SurgeryFixture();

    @Given("une intervention avec des informations manquantes")
    public void aSurgeryWithMissingData() {
        surgeryRequest = new SurgeryRequest("", 0, "", "", "", "", PatientContext.getPatientNumber());
    }

    @Given("une intervention valide")
    public void aValidSurgery() {
        surgeryRequest = new SurgeryRequest(A_DESCRIPTION, A_SURGEON_NUMBER, A_DATE, A_ROOM, A_VALID_TYPE,
                A_VALID_STATUS, PatientContext.getPatientNumber());
    }

    @Given("une intervention existante")
    public void anExistingSurgery() {
        surgeryFixture.setupExistingSurgery();
    }

    @Given("une intervention existante dangereuse")
    public void anExistingDangerousSurgery() {
        surgeryFixture.setupExistingRestrictedSurgery();
    }

    @Given("une intervention valide sans statut")
    public void aValidSurgeryWithoutStatus() {
        surgeryRequest = new SurgeryRequest(A_DESCRIPTION, A_SURGEON_NUMBER, A_DATE, A_ROOM, A_VALID_TYPE,
                NO_STATUS, PatientContext.getPatientNumber());
    }

    @When("j'ajoute cette intervention au dossier du patient")
    public void createSurgery() {
        Response response = new RequestBuilder().withContent(surgeryRequest).doPost("/interventions/");
        ResponseContext.setResponse(response);
    }

    @When("j'ajoute cette intervention sans statut au dossier du patient")
    public void createSurgeryWithService() {
        SurgeryService surgeryService = new SurgeryService();
        int surgeryNumber = surgeryService.saveSurgery(surgeryRequest);
        Surgery surgery = RepositoryLocator.getSurgeryRepository().get(surgeryNumber);
        SurgeryContext.setSurgery(surgery);
    }

    @Then("cette intervention est conservée")
    public void anSurgeryIsSaved() {
        verify(RepositoryLocator.getSurgeryRepository()).store(any(Surgery.class));
    }

    @Then("le statut de cette intervention est à \"PLANIFIEE\"")
    public void anStatusIsPlanned() {
        Surgery surgery = SurgeryContext.getSurgery();
        assertTrue(surgery.hasStatus(SurgeryStatus.PLANNED));
    }

}
