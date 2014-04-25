package ca.ulaval.ift6002.m2.acceptance.contexts;

import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PrescriptionContext {

    private static Prescription prescriptionInstance;

    public static void setPrescription(Prescription prescription) {
        prescriptionInstance = prescription;
    }

    public static Integer getPrescriptionId() {
        return prescriptionInstance.getNumber();
    }

    public static void reset() {
        prescriptionInstance = null;
    }

}
