package ca.ulaval.ift6002.m2.acceptance.contexts;


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
}
