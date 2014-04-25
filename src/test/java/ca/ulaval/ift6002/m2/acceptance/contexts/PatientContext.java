package ca.ulaval.ift6002.m2.acceptance.contexts;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class PatientContext {

    private static Integer patientId;

    public static void setPatientId(Integer aPatientId) {
        patientId = aPatientId;
    }

    public static Integer getPatientId() {
        return patientId;
    }

    public static void reset() {
        patientId = null;
    }

    public static Patient getCurrentPatient() {
        return RepositoryLocator.getPatientRepository().get(patientId);
    }
}
