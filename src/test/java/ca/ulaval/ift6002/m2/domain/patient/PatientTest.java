package ca.ulaval.ift6002.m2.domain.patient;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
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

    private static final int EXISTING_PRESCRIPTION_NUMBER = 1;
    private static final int NOT_EXISTING_PRESCRIPTION_NUMBER = 42;

    @Mock
    private Prescription prescription;

    @Mock
    private Consumption consumption;

    private Patient patient;

    @Before
    public void setUp() {
        patient = mock(Patient.class, CALLS_REAL_METHODS);
    }

    @Test(expected = DeadPatientException.class)
    public void givenDeadPatientWhenAddingPrescriptionShouldThrowException() {
        willReturn(true).given(patient).isDead();

        patient.receivesPrescription(prescription);
    }

    @Test
    public void whenReceivesPrescriptionShouldAddPrescription() {
        willReturn(false).given(patient).isDead();
        doNothing().when(patient).addPrescription(prescription);
        patient.receivesPrescription(prescription);

        verify(patient).addPrescription(prescription);
    }

    @Test
    public void whenConsumingAnExistingPrescriptionShouldConsumeAConsumption() {
        willReturn(prescription).given(patient).findPrescription(EXISTING_PRESCRIPTION_NUMBER);
        patient.consumesPrescription(EXISTING_PRESCRIPTION_NUMBER, consumption);

        verify(prescription).addConsumption(consumption);
    }

    @Test(expected = PrescriptionNotFoundException.class)
    public void whenConsumingAnUnexistingPrescriptionShouldThrowException() {
        doThrow(new PrescriptionNotFoundException("")).when(patient).findPrescription(NOT_EXISTING_PRESCRIPTION_NUMBER);
        patient.consumesPrescription(NOT_EXISTING_PRESCRIPTION_NUMBER, consumption);
    }
}
