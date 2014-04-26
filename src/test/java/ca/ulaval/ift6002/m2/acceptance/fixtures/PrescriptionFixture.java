package ca.ulaval.ift6002.m2.acceptance.fixtures;

import java.util.Date;
import java.util.Iterator;

import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.PrescriptionContext;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class PrescriptionFixture {

    private static final int RENEWALS = 0;
    private static final Date DATE = new Date();
    private static final Practitioner PRACTITIONER = new Practitioner("a Practitionner");
    private static final String A_DRUG_NAME = "a drug";
    private PatientFixture patientFixture = new PatientFixture();

    public void setupPrescriptionWithinCurrentPatient() {
        setupPrescriptionContextWith(PatientContext.getPatient());
    }

    public void setupPrescriptionContextWith(Patient patient) {
        Prescription prescription = getFirstPrescriptionFrom(patient);
        PrescriptionContext.setPrescription(prescription);
    }

    public Prescription getFirstPrescriptionFrom(Patient patient) {
        Iterator<Prescription> iterator = patient.getPrescriptions().iterator();

        return iterator.next();
    }

    public void setupExistingPrescription() {
        Patient patient = patientFixture.getExistingPatientWithPrescription();
        Prescription prescription = getFirstPrescriptionFrom(patient);
        PrescriptionContext.setPrescription(prescription);
    }

    public void setupUnexistingPrescription() {
        Drug drug = FactoryLocator.getDrugFactory().create(A_DRUG_NAME);
        Prescription prescription = FactoryLocator.getPrescriptionFactory().create(PRACTITIONER, DATE, RENEWALS, drug);
        PrescriptionContext.setPrescription(prescription);
    }
}
