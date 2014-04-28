package ca.ulaval.ift6002.m2.acceptance.fixtures;

import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class PatientFixture {

    private static final int PATIENT_NUMBER = 1;
    private static final int PATIENT_NUMBER_WITH_RECENT_PRESCRIPTION = 3;
    private static final int PATIENT_NUMBER_WITH_OBSOLETE_PRESCRIPTION = 3;
    private static final int DEAD_PATIENT_NUMBER = 5;
    private static final int PATIENT_NUMBER_WITH_MULTIPLE_PRESCRIPTIONS = 6;

    private static final Integer UNEXISTING_PATIENT_ID = -999;

    public void setupExistingPatient() {
        PatientContext.setPatient(getExistingPatient());
        PatientContext.setPatientNumber(PATIENT_NUMBER);
    }

    public void setupExistingPatientWithPrescription() {
        PatientContext.setPatient(getExistingPatientWithPrescription());
        PatientContext.setPatientNumber(PATIENT_NUMBER_WITH_RECENT_PRESCRIPTION);
    }

    public void setupExistingPatientWithMultiplePrescriptions() {
        PatientContext.setPatient(getExistingPatientWithMultiplePrescriptions());
        PatientContext.setPatientNumber(PATIENT_NUMBER_WITH_MULTIPLE_PRESCRIPTIONS);
    }

    public void setupUnexistingPatient() {
        PatientContext.setPatient(getUnexistingPatient());
        PatientContext.setPatientNumber(UNEXISTING_PATIENT_ID);
    }

    public void setupDeadPatient() {
        PatientContext.setPatient(getDeadPatient());
        PatientContext.setPatientNumber(DEAD_PATIENT_NUMBER);
    }

    public void setupExistingPatientWithObsoletePrescription() {
        PatientContext.setPatient(getPatientWithObsoletePrescription());
        PatientContext.setPatientNumber(PATIENT_NUMBER_WITH_OBSOLETE_PRESCRIPTION);
    }

    public Patient getExistingPatient() {
        return RepositoryLocator.getPatientRepository().get(PATIENT_NUMBER);
    }

    public Patient getExistingPatientWithPrescription() {
        return RepositoryLocator.getPatientRepository().get(PATIENT_NUMBER_WITH_RECENT_PRESCRIPTION);
    }

    public Patient getExistingPatientWithMultiplePrescriptions() {
        return RepositoryLocator.getPatientRepository().get(PATIENT_NUMBER_WITH_MULTIPLE_PRESCRIPTIONS);
    }

    public Patient getUnexistingPatient() {
        PatientFactory patientFactory = FactoryLocator.getPatientFactory();
        return patientFactory.create(UNEXISTING_PATIENT_ID);
    }

    public Patient getDeadPatient() {
        return RepositoryLocator.getPatientRepository().get(DEAD_PATIENT_NUMBER);
    }

    private Patient getPatientWithObsoletePrescription() {
        return RepositoryLocator.getPatientRepository().get(PATIENT_NUMBER_WITH_OBSOLETE_PRESCRIPTION);
    }

}
