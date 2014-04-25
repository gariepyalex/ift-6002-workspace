package ca.ulaval.ift6002.m2.acceptance.contexts;

import ca.ulaval.ift6002.m2.domain.patient.Patient;

public class PatientContext {

    private static Patient patientInstance;
    private static Integer patientNumber;

    public static void setPatient(Patient patient) {
        patientInstance = patient;
    }

    public static Patient getPatient() {
        return patientInstance;
    }

    public static void setPatientNumber(Integer number) {
        patientNumber = number;
    }

    public static Integer getPatientNumber() {
        return patientNumber;
    }

    public static void reset() {
        patientInstance = null;
        patientNumber = null;
    }
}
