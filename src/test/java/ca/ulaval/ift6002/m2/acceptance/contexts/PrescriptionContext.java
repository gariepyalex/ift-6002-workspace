package ca.ulaval.ift6002.m2.acceptance.contexts;

public class PrescriptionContext {

    private static Integer prescriptionId;

    public static void setPrescriptionId(Integer aPrescriptionId) {
        prescriptionId = aPrescriptionId;
    }

    public static Integer getPrescriptionId() {
        return prescriptionId;
    }

    public static void reset() {
        prescriptionId = null;
    }
}
