package ca.ulaval.ift6002.m2.acceptance.steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.PrescriptionContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;

import com.jayway.restassured.response.Response;

public class PatientSteps {

    private static final Integer EXISTING_PATIENT_ID = 1;

    private static final Integer UNEXISTING_PATIENT_ID = -999;

    private static final String DATE = "2001-07-04T12:08:56";

    private static final String PHARMACY = "Pharmacie ABC de Portneuf";

    private static final Integer CONSUMPTIONS_COUNT = 2;

    private ConsumptionRequest consumptionRequest;

    @BeforeScenario
    public void clearResults() {
        consumptionRequest = null;
        ResponseContext.reset();
        PatientContext.reset();
    }

    @Given("un patient est existant")
    public void anExistingPatient() {
        PatientContext.setPatientId(EXISTING_PATIENT_ID);
    }

    @Given("un patient est inexistant")
    public void anUnexistingPatient() {
        PatientContext.setPatientId(UNEXISTING_PATIENT_ID);
    }

    @Given("une consommation valide")
    public void aValidConsumption() {
        consumptionRequest = new ConsumptionRequest(DATE, PHARMACY, CONSUMPTIONS_COUNT);
    }

    @When("j'ajoute cette consommation")
    public void consumePrescription() {

        Response response = new RequestBuilder().withContent(consumptionRequest).doPost(
                "/patient/{patientId}/prescriptions/{prescriptionId}/consommations", PatientContext.getPatientId(),
                PrescriptionContext.getPrescriptionId());

        ResponseContext.setResponse(response);
    }
}
