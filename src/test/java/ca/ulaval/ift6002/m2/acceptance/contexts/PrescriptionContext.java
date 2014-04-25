package ca.ulaval.ift6002.m2.acceptance.contexts;

import java.util.Iterator;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

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

    public static void setPrescriptionIdWithinCurrentPatient() {
        int firstPrescriptionId = getFirstPrescriptionIdOfCurrentPatient();

        setPrescriptionId(firstPrescriptionId);
    }

    public static int getFirstPrescriptionIdOfCurrentPatient() {
        Patient currentPatient = PatientContext.getCurrentPatient();
        Prescription firstPrescription = getFirstPrescriptionFrom(currentPatient);

        return firstPrescription.getNumber();
    }

    private static Prescription getFirstPrescriptionFrom(Patient patient) {
        Iterator<Prescription> iterator = patient.getPrescriptions().iterator();

        return iterator.next();
    }
}
