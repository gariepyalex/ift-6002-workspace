package ca.ulaval.ift6002.m2.acceptance.fixtures;

import java.util.Iterator;

import ca.ulaval.ift6002.m2.acceptance.contexts.PatientContext;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class PrescriptionFixture {

    public PrescriptionFixture() {

    }

    public Prescription getFirstPrescriptionOfCurrentPatient() {
        PatientRepository patientRepository = RepositoryLocator.getPatientRepository();
        Patient patient = patientRepository.get(PatientContext.getPatientId());
        Iterator<Prescription> iterator = patient.getPrescriptions().iterator();
        Prescription prescription = iterator.next();
        return prescription;
    }
}
