package ca.ulaval.ift6002.m2.acceptance.fixtures;

import java.util.Iterator;

import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.acceptance.contexts.PrescriptionContext;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PrescriptionFixture {

    public void setupPrescriptionWithinCurrentPatient() {
        setupPrescriptionContext(PatientContext.getPatient());
    }

    public void setupPrescriptionContext(Patient patient) {
        Prescription prescription = getFirstPrescriptionFrom(patient);
        PrescriptionContext.setPrescription(prescription);
    }

    public Prescription getFirstPrescriptionFrom(Patient patient) {
        Iterator<Prescription> iterator = patient.getPrescriptions().iterator();

        return iterator.next();
    }
}
