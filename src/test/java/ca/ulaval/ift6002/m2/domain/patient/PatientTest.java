package ca.ulaval.ift6002.m2.domain.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class PatientTest {

    private static final int PATIENT_NUMBER = 12345;
    private static final int DEAD_PATIENT_NUMBER = 67890;
    private static final int NOT_EXISTING_PRESCRIPTION_NUMBER = 42;
    private static final int EXISTING_PRESCRIPTION_NUMBER = 1;

    @Mock
    private Prescription prescription;

    @Mock
    private Consumption consumption;

    private Patient patient;

    @Before
    public void setUp() {
        patient = new Patient(PATIENT_NUMBER);
    }

    @Test
    public void givenNewPatientWhenCountPrescriptionsShouldBeEmpty() {
        int prescriptionsCount = patient.countPrescriptions();

        assertEquals(0, prescriptionsCount);
    }

    @Test
    public void givenNewPatientShouldNotBeDead() {
        assertFalse(patient.isDead());
    }

    @Test
    public void givenDeadPatientShouldBeDead() {
        patient.declareDead();
        boolean isDead = patient.isDead();
        assertTrue(isDead);
    }

    @Test
    public void givenPatientWhenAddPrescriptionShouldHaveCountOfOne() {
        patient.receivesPrescription(prescription);
        int prescriptionsCount = patient.countPrescriptions();
        assertEquals(1, prescriptionsCount);
    }

    @Test
    public void givenPatientWhenAddTwoPrescriptionsShouldHaveCountOfTwo() {
        patient.receivesPrescription(prescription);
        patient.receivesPrescription(prescription);

        int prescriptionsCount = patient.countPrescriptions();

        assertEquals(2, prescriptionsCount);
    }

    @Test(expected = DeadPatientException.class)
    public void givenDeadPatientWhenAddingPrescriptionShouldThrowException() {
        Patient deadPatient = new Patient(DEAD_PATIENT_NUMBER);
        deadPatient.declareDead();

        deadPatient.receivesPrescription(prescription);
    }

    @Test(expected = PrescriptionNotFoundException.class)
    public void givenNotExistingPrescriptionNumberWhenConsumesPrescriptionShouldThrowException() {
        patient.receivesPrescription(prescription);
        willReturn(false).given(prescription).hasNumber(NOT_EXISTING_PRESCRIPTION_NUMBER);

        patient.consumesPrescription(NOT_EXISTING_PRESCRIPTION_NUMBER, consumption);
    }

    @Test
    public void givenExistingPrescriptionWhenConsumesPrescriptionShouldCallAddConsumption() {
        patient.receivesPrescription(prescription);
        willReturn(true).given(prescription).hasNumber(EXISTING_PRESCRIPTION_NUMBER);

        patient.consumesPrescription(EXISTING_PRESCRIPTION_NUMBER, consumption);
        verify(prescription).addConsumption(consumption);
    }
}
