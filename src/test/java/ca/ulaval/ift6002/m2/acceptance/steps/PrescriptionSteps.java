package ca.ulaval.ift6002.m2.acceptance.steps;

import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.PrescriptionRequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.ResponseContext;
import ca.ulaval.ift6002.m2.acceptance.fixtures.PrescriptionFixture;
import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

import com.jayway.restassured.response.Response;

public class PrescriptionSteps extends Steps {

    private static final String INTERACTING_DIN = "11111111";

    private static final String ADVIL_DIN = "02229682";
    private static final String ADVIL_NAME = "Advil turbo";

    private static final String INVALID_DIN = "Invalid";
    private static final int INVALID_RENEWALS = -1;

    private PrescriptionRequest prescriptionRequest;
    private final PrescriptionFixture prescriptionFixture = new PrescriptionFixture();

    private String[] expectedDateOrder;

    private String[] expectedConsumptionsDateOrder;

    @BeforeScenario
    public void clearResults() {
        expectedDateOrder = null;
        expectedConsumptionsDateOrder = null;
        prescriptionRequest = null;
    }

    @Given("une prescription avec des données manquantes")
    public void aPrescriptionWithMissingData() {
        prescriptionRequest = new PrescriptionRequestBuilder().build();
    }

    @Given("une prescription valide avec DIN")
    @Alias("une prescription valide")
    public void aValidPrescriptionWithDin() {
        prescriptionRequest = new PrescriptionRequestBuilder().din(ADVIL_DIN).build();
    }

    @Given("une prescription existante")
    public void existingPrescription() {
        prescriptionFixture.setupExistingPrescription();
    }

    @Given("une prescription inexistante")
    public void unexistingPrescription() {
        prescriptionFixture.setupUnexistingPrescription();
    }

    @Given("une prescription avec un DIN inexistant")
    public void aPrescriptionWithInexistantDin() {
        prescriptionRequest = new PrescriptionRequestBuilder().din(INVALID_DIN).build();
    }

    @Given("une prescription avec nom de médicament")
    public void aValidPrescriptionWithDrugName() {
        prescriptionRequest = new PrescriptionRequestBuilder().name(ADVIL_NAME).build();
    }

    @Given("une prescription avec un DIN et un nom de médicament")
    public void anInvalidPrescriptionWithBothNameAndDin() {
        prescriptionRequest = new PrescriptionRequestBuilder().din(ADVIL_DIN).name(ADVIL_NAME).build();
    }

    @Given("une prescription avec un nombre de renouvellements invalide")
    public void aPrescriptionWithInvalidRenewals() {
        prescriptionRequest = new PrescriptionRequestBuilder().renewals(INVALID_RENEWALS).din(ADVIL_DIN).build();
    }

    @Given("une prescription récente qui interagit avec tous les médicaments")
    public void aPrescriptionInPatientFiles() {
        prescriptionRequest = new PrescriptionRequestBuilder().din(INTERACTING_DIN).withRecentDate().build();
    }

    @When("j'ajoute cette prescription au dossier du patient")
    public void addingThePrescriptionWithMissingData() {
        Response response = new RequestBuilder().withContent(prescriptionRequest).doPost(
                "/patient/{patientId}/prescriptions", PatientContext.getPatientNumber());

        ResponseContext.setResponse(response);
    }

    @When("je demande le sommaire des prescriptions de ce patient")
    public void getPrescriptionsSummary() {
        Response response = new RequestBuilder().doGet("/patient/{patientId}/prescriptions/",
                PatientContext.getPatientNumber());

        expectedDateOrder("2012-04-04T12:08:56", "2010-02-04T12:08:56", "2009-01-04T12:08:56", "2007-03-04T12:08:56");

        ResponseContext.setResponse(response);
    }

    @When("je demande le sommaire détaillé des prescriptions de ce patient")
    public void getDetailedPrescriptionsSummary() {
        Response response = new RequestBuilder().withQueryParam("view", "details").doGet(
                "/patient/{patientId}/prescriptions/", PatientContext.getPatientNumber());

        expectedDateOrder("2012-04-04T12:08:56", "2010-02-04T12:08:56", "2009-01-04T12:08:56", "2007-03-04T12:08:56");
        expectedConsumptionsDateOrder("2010-02-04T12:08:56", "2009-01-04T12:08:56");

        ResponseContext.setResponse(response);
    }

    @Then("cette prescription est conservée")
    public void prescriptionIsSaved() {
        verify(RepositoryLocator.getPatientRepository()).store(any(Patient.class));

        ResponseContext.getResponse().then().statusCode(Status.CREATED.getStatusCode());
    }

    @Then("toutes les informations du sommaire sont affichées")
    public void allSummaryInformationsAreShown() {
        ResponseContext.getResponse().then().assertThat().body("prescription.date", anything())
                .body("prescription.intervenant", anything()).body("prescription.nom", anything())
                .body("prescription.renouvellements_restants", anything())
                .body("prescription.renouvellements_autorises", anything());
    }

    @Then("toutes les informations du sommaire détaillé sont affichées")
    public void allDetailedSummaryInformationsAreShown() {
        ResponseContext.getResponse().then().assertThat().body("prescription.date", anything())
                .body("prescription.intervenant", anything()).body("prescription.nom", anything())
                .body("prescription.renouvellements_restants", anything())
                .body("prescription.renouvellements_autorises", anything()).body("prescription.din", anything())
                .body("prescription.consommations", anything());
    }

    @Then("toutes les prescriptions sont affichées en ordre décroissant de date")
    public void allPrescriptionsAreInDescendingOrder() {
        // TODO: Verify if there's a cleaner way to test this

        List<String> responsesDate = ResponseContext.getResponse().getBody().jsonPath().get("prescription.date");
        String[] actualResponsesDate = responsesDate.toArray(new String[responsesDate.size()]);

        assertArrayEquals(expectedDateOrder, actualResponsesDate);
    }

    @Then("toutes les consommations des prescriptions sont affichées en ordre décroissant de date")
    public void allConsumptionsAreInDescendingOrder() {
        // TODO: Verify if there's a cleaner way to test this
        List<List<String>> responsesConsumptionDate = ResponseContext.getResponse().getBody().jsonPath()
                .get("prescription.consommations.date");
        String[] actualResponsesConsumptionDate = responsesConsumptionDate.get(0).toArray(
                new String[responsesConsumptionDate.size()]);

        assertArrayEquals(expectedConsumptionsDateOrder, actualResponsesConsumptionDate);
    }

    private void expectedDateOrder(String... date) {
        expectedDateOrder = date;
    }

    private void expectedConsumptionsDateOrder(String... consumptions) {
        expectedConsumptionsDateOrder = consumptions;
    }
}
