package ca.ulaval.ift6002.m2.acceptance.steps;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.PrescriptionContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;

import com.jayway.restassured.response.Response;

public class PatientSteps {

    private static final int PATIENT_ID_WITH_RECENT_PRESCRIPTION = 3;
    private static final Integer EXISTING_PATIENT_ID = 1;
    private static final Integer UNEXISTING_PATIENT_ID = -999;

    private static final String DATE = "2001-07-04T12:08:56";
    private static final String PHARMACY = "Pharmacie ABC de Portneuf";

    private static final Integer CONSUMPTIONS_COUNT = 2;
    private static final Integer MANY_CONSUMPTIONS_COUNT = 100;

    private ConsumptionRequest consumptionRequest;

    @BeforeScenario
    public void clearResults() {
        consumptionRequest = null;
    }

    @Given("un patient est existant")
    public void anExistingPatient() {
        PatientContext.setPatientId(EXISTING_PATIENT_ID);
    }

    @Given("un patient est inexistant")
    public void anUnexistingPatient() {
        PatientContext.setPatientId(UNEXISTING_PATIENT_ID);
    }

    @Given("un patient avec une prescription")
    public void aPatientWithPrescription() {
        PatientContext.setPatientId(PATIENT_ID_WITH_RECENT_PRESCRIPTION);
        PrescriptionContext.setPrescriptionIdWithinCurrentPatient();
    }

    @Given("une consommation valide")
    public void aValidConsumption() {
        consumptionRequest = new ConsumptionRequest(DATE, PHARMACY, CONSUMPTIONS_COUNT);
    }

    @Given("une consommation invalide")
    public void anInvalidConsumption() {
        consumptionRequest = new ConsumptionRequest(null, null, null);
    }

    @Given("une consommation excédant le nombre de renouvellements restants")
    public void aConsumptionWithManyConsumptionsCount() {
        consumptionRequest = new ConsumptionRequest(DATE, PHARMACY, MANY_CONSUMPTIONS_COUNT);
    }

    @When("j'ajoute cette consommation")
    public void consumePrescription() {
        Response response = new RequestBuilder().withContent(consumptionRequest).doPost(
                "/patient/{patientId}/prescriptions/{prescriptionId}/consommations", PatientContext.getPatientId(),
                PrescriptionContext.getPrescriptionId());

        ResponseContext.setResponse(response);
    }

    @Then("cette consommation est effectuée")
    public void prescriptionIsSaved() {
        ResponseContext.getResponse().then().statusCode(Status.OK.getStatusCode());
    }
}
