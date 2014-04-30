package ca.ulaval.ift6002.m2.acceptance.steps;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import javax.ws.rs.core.Response.Status;

import org.hamcrest.Matcher;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import ca.ulaval.ift6002.m2.acceptance.builder.PrescriptionRequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.builder.RequestBuilder;
import ca.ulaval.ift6002.m2.acceptance.contexts.DateOrderContext;
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

    @BeforeScenario
    public void clearResults() {
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

        ResponseContext.setResponse(response);
    }

    @When("je demande le sommaire détaillé des prescriptions de ce patient")
    public void getDetailedPrescriptionsSummary() {
        Response response = new RequestBuilder().withQueryParam("view", "details").doGet(
                "/patient/{patientId}/prescriptions/", PatientContext.getPatientNumber());

        ResponseContext.setResponse(response);
    }

    @Then("cette prescription est conservée")
    public void prescriptionIsSaved() {
        verify(RepositoryLocator.getPatientRepository()).store(any(Patient.class));

        ResponseContext.getResponse().then().statusCode(Status.CREATED.getStatusCode());
    }

    @Then("toutes les informations du sommaire sont affichées")
    public void allSummaryInformationsAreShown() {
        ResponseContext.getResponse().then().assertThat().body("prescription.date", isDisplayed())
                .body("prescription.intervenant", isDisplayed()).body("prescription.nom", isDisplayed())
                .body("prescription.renouvellements_restants", isDisplayed())
                .body("prescription.renouvellements_autorises", isDisplayed());
    }

    @Then("toutes les informations du sommaire détaillé sont affichées")
    public void allDetailedSummaryInformationsAreShown() {
        ResponseContext.getResponse().then().assertThat().body("prescription.date", isDisplayed())
                .body("prescription.intervenant", isDisplayed()).body("prescription.nom", isDisplayed())
                .body("prescription.renouvellements_restants", isDisplayed())
                .body("prescription.renouvellements_autorises", isDisplayed()).body("prescription.din", isDisplayed())
                .body("prescription.consommations", isDisplayed());
    }

    @Then("toutes les prescriptions sont affichées en ordre décroissant de date")
    public void allPrescriptionsAreInDescendingOrder() {
        String[] expectedDateOrder = DateOrderContext.getExpectedPrescriptionDateOrder();
        ResponseContext.getResponse().then().body("prescription.date", contains(expectedDateOrder));
    }

    @Then("toutes les consommations des prescriptions sont affichées en ordre décroissant de date")
    public void allConsumptionsAreInDescendingOrder() {
        // TODO: MAKE THIS TEST PASS
        String[][] expectedDateOrder = DateOrderContext.getExpectedConsumptionDateOrder();
        ResponseContext.getResponse().then().body("prescription.consommations.date", contains(expectedDateOrder));
    }

    private Matcher<?> isDisplayed() {
        return not(hasItem(nullValue()));
    }
}
