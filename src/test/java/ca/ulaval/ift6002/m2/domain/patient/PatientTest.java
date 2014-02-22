package ca.ulaval.ift6002.m2.domain.patient;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PatientTest {
    private static final Date DATE = new Date();
    private static final Practitioner PRACTITIONER = new Practitioner("a pratitioner name");
    private static final Drug DRUG = Drug.fromName("Advil");
    private static final int ONE_RENEWALS = 1;

    private static final Prescription PRESCRIPTION = new Prescription(PRACTITIONER, DATE, ONE_RENEWALS, DRUG);
    private static final Collection<Prescription> PRESCRIPTION_COLLECTION = new ArrayList<Prescription>();

    private static final int PATIENT_NUMBER = 1;

    private static Patient noPrescriptionPatient;
    private static Patient onePrescriptionPatient;

    @Before
    public void setup() {

        PRESCRIPTION_COLLECTION.add(PRESCRIPTION);

        noPrescriptionPatient = new Patient(PATIENT_NUMBER);
        onePrescriptionPatient = new Patient(PATIENT_NUMBER, PRESCRIPTION_COLLECTION);
    }

    @Test
    public void addPrescriptionToWhenNoPrescriptionPatientShouldReturnPatientWithOnePrescription() {
        Patient patient = Patient.addPrescriptionTo(noPrescriptionPatient, PRESCRIPTION);
        assertEquals(onePrescriptionPatient, patient);
    }

}
