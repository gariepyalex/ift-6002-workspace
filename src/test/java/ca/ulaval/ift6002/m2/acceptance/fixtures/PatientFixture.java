package ca.ulaval.ift6002.m2.acceptance.fixtures;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class PatientFixture {

    private static final int PATIENT_NUMBER = 1;

    public Patient getExistingPatient() {
        return RepositoryLocator.getPatientRepository().get(PATIENT_NUMBER);
    }
}
