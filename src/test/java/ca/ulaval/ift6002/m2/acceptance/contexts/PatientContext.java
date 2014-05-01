package ca.ulaval.ift6002.m2.acceptance.contexts;

import ca.ulaval.ift6002.m2.domain.patient.Patient;

public class PatientContext {

    private static Patient patientInstance;
    private static int patientNumber;

    public static void setPatient(Patient patient) {
        patientInstance = patient;
    }

    public static Patient getPatient() {
        return patientInstance;
    }

    public static void setPatientNumber(int number) {
        patientNumber = number;
    }

    public static int getPatientNumber() {
        return patientNumber;
    }

    public static void reset() {
        patientInstance = null;
    }
}
