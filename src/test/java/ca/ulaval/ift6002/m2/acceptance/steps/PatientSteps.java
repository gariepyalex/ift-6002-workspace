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
import ca.ulaval.ift6002.m2.acceptance.fixtures.PatientFixture;
import ca.ulaval.ift6002.m2.acceptance.fixtures.PrescriptionFixture;
import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;

import com.jayway.restassured.response.Response;

public class PatientSteps {

    private static final String DATE = "2001-07-04T12:08:56";
    private static final String PHARMACY = "Pharmacie ABC de Portneuf";

    private static final Integer CONSUMPTIONS_COUNT = 2;
    private static final Integer MANY_CONSUMPTIONS_COUNT = 100;

    private ConsumptionRequest consumptionRequest;

    private PatientFixture patientFixture = new PatientFixture();
    private PrescriptionFixture prescriptionFixture = new PrescriptionFixture();

    @BeforeScenario
    public void clearResults() {
        consumptionRequest = null;
    }

    @Given("un patient est existant")
    public void anExistingPatient() {
        patientFixture.setupExistingPatient();
    }

    @Given("un patient est inexistant")
    public void anUnexistingPatient() {
        patientFixture.setupUnexistingPatient();
    }

    @Given("un patient avec une prescription")
    public void aPatientWithPrescription() {
        patientFixture.setupExistingPatientWithPrescription();
        prescriptionFixture.setupPrescriptionContext(PatientContext.getPatient());
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
                "/patient/{patientId}/prescriptions/{prescriptionId}/consommations", PatientContext.getPatientNumber(),
                PrescriptionContext.getPrescriptionId());

        ResponseContext.setResponse(response);
    }

    @Then("cette consommation est effectuée")
    public void prescriptionIsSaved() {
        ResponseContext.getResponse().then().statusCode(Status.OK.getStatusCode());
    }
}
