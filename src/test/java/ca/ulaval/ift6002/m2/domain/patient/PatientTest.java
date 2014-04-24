package ca.ulaval.ift6002.m2.domain.patient;

import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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

    private Collection<Prescription> prescriptions;

    @Before
    public void setUp() {
        patient = mock(Patient.class, CALLS_REAL_METHODS);

        prescriptions = Arrays.asList(prescription);
    }

    @Test(expected = InteractionDetectionException.class)
    public void givenPatientWithAPrescriptionWhenAddingInteractingPrescriptionShouldThrowException() {
        willReturn(false).given(patient).isDead();
        willReturn(prescriptions).given(patient).getPrescriptions();

        Prescription interactingPrescription = mock(Prescription.class, CALLS_REAL_METHODS);
        willReturn(true).given(prescription).isInteractingWith(interactingPrescription);
        doNothing().when(patient).addPrescription(interactingPrescription);

        patient.receivesPrescription(interactingPrescription);
    }

    @Test(expected = DeadPatientException.class)
    public void givenDeadPatientWhenAddingPrescriptionShouldThrowException() {
        willReturn(true).given(patient).isDead();
        patient.receivesPrescription(prescription);
    }

    @Test
    public void whenReceivesPrescriptionShouldAddPrescription() {
        willReturn(false).given(patient).isDead();
        willReturn(Collections.emptyList()).given(patient).getPrescriptions();
        doNothing().when(patient).addPrescription(prescription);
        patient.receivesPrescription(prescription);

        verify(patient).addPrescription(prescription);
    }

    @Test
    public void whenConsumingAnExistingPrescriptionShouldConsumeAConsumption() {
        willReturn(prescriptions).given(patient).getPrescriptions();
        willReturn(true).given(prescription).hasNumber(EXISTING_PRESCRIPTION_NUMBER);

        patient.consumesPrescription(EXISTING_PRESCRIPTION_NUMBER, consumption);

        verify(prescription).addConsumption(consumption);
    }

    @Test(expected = PrescriptionNotFoundException.class)
    public void whenConsumingAnUnexistingPrescriptionShouldThrowException() {
        willReturn(prescriptions).given(patient).getPrescriptions();
        willReturn(false).given(prescription).hasNumber(EXISTING_PRESCRIPTION_NUMBER);

        patient.consumesPrescription(NOT_EXISTING_PRESCRIPTION_NUMBER, consumption);
    }

}
