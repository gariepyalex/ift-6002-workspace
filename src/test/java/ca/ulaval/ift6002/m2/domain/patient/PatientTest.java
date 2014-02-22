package ca.ulaval.ift6002.m2.domain.patient;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PatientTest {

    private static final int PATIENT_NUMBER = 1;

    private static final Date DATE = new Date();
    private static final Practitioner PRACTITIONER = new Practitioner("a pratitioner name");
    private static final Drug DRUG = Drug.fromName("Advil");
    private static final int RENEWALS = 1;

    private static final Prescription A_PRESCRIPTION = new Prescription(PRACTITIONER, DATE, RENEWALS, DRUG);

    private static final Collection<Prescription> PRESCRIPTIONS = Arrays.asList(A_PRESCRIPTION);

    private static final Patient PATIENT_WITHOUT_PRESCRITPION = new Patient(PATIENT_NUMBER);
    private static final Patient PATIENT_WITH_PRESCRIPTION = new Patient(PATIENT_NUMBER, PRESCRIPTIONS);

    @Test
    public void givenPatientWhenAddingPrescriptionShouldReturnNewPatientWithAddedPrescription() {
        Patient newPatient = Patient.addPrescriptionTo(PATIENT_WITHOUT_PRESCRITPION, A_PRESCRIPTION);

        assertEquals(PATIENT_WITH_PRESCRIPTION, newPatient);
    }

}
