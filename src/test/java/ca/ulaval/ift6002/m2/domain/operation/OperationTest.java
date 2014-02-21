package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.patient.Patient;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

    private static final String DESCRIPTION = "Description Test";
    private static final Surgeon SURGEON = new Surgeon(101224);
    private static final Date DATE = new Date();
    private static final Room ROOM = new Room("salleB");
    private static final Patient PATIENT = new Patient(2);

    private static final OperationType OPERATION_TYPE = OperationType.EYE;

    private static final OperationType EYE_TYPE = OperationType.EYE;
    private static final OperationType MARROW_TYPE = OperationType.MARROW;
    private static final OperationType HEART_TYPE = OperationType.HEART;
    private static final OperationType ONCOLOGY_TYPE = OperationType.ONCOLOGY;

    private static final OperationStatus DEFAULT_STATUS = OperationStatus.PLANNED;

    // TODO see if we mock these value objects...
    @Mock
    private Instrument instrument;

    @Mock
    private Instrument otherInstrument;

    @Mock
    private Instrument anonymousInstrument;

    private Operation operation;

    @Before
    public void setupAnonymousInstrument() {
        willReturn(true).given(anonymousInstrument).isAnonymous();
    }

    @Before
    public void setupInstrument() {
        willReturn(false).given(instrument).isAnonymous();
    }

    private void buildAnOperation() {
        operation = new Operation(DESCRIPTION, SURGEON, DATE, ROOM, OPERATION_TYPE, PATIENT);
    }

    private void buildEyeOperation() {
        operation = new Operation(DESCRIPTION, SURGEON, DATE, ROOM, EYE_TYPE, PATIENT);
    }

    private void buildHeartOperation() {
        operation = new Operation(DESCRIPTION, SURGEON, DATE, ROOM, HEART_TYPE, PATIENT);
    }

    private void buildMarrowOperation() {
        operation = new Operation(DESCRIPTION, SURGEON, DATE, ROOM, MARROW_TYPE, PATIENT);
    }

    private void buildOncologyOperation() {
        operation = new Operation(DESCRIPTION, SURGEON, DATE, ROOM, ONCOLOGY_TYPE, PATIENT);
    }

    private void addOneInstrument() throws InvalidInstrumentException {
        operation.addInstrument(instrument);
    }

    private void addTwoInstrument() throws InvalidInstrumentException {
        operation.addInstrument(instrument);
        operation.addInstrument(otherInstrument);
    }

    @Test
    public void givenOperationShouldHaveDefaultStatus() {
        buildAnOperation();

        assertEquals(DEFAULT_STATUS, operation.getStatus());
    }

    @Test
    public void givenOperationShouldHaveZeroInstrument() {
        buildAnOperation();

        assertEquals(0, operation.countInstruments());
    }

    @Test
    public void givenOperationShouldNotHaveAnyInstrument() {
        buildAnOperation();

        boolean hasInstrument = operation.hasInstrument(instrument);
        boolean hasOtherInstrument = operation.hasInstrument(otherInstrument);

        assertFalse(hasInstrument);
        assertFalse(hasOtherInstrument);
    }

    @Test
    public void givenOneInstrumentShouldHaveCountOfOneInstrument() throws InvalidInstrumentException {
        buildAnOperation();
        addOneInstrument();

        int instrumentCount = operation.countInstruments();

        assertEquals(1, instrumentCount);
    }

    @Test
    public void givenOneInstrumentShouldHaveGivenInstrument() throws InvalidInstrumentException {
        buildAnOperation();
        addOneInstrument();

        boolean hasInstrument = operation.hasInstrument(instrument);

        assertTrue(hasInstrument);
    }

    @Test
    public void givenTwoInstrumentsShouldHaveTwoInstruments() throws InvalidInstrumentException {
        buildAnOperation();
        addTwoInstrument();

        int instrumentCount = operation.countInstruments();

        assertEquals(2, instrumentCount);
    }

    @Test
    public void givenTwoInstrumentsShouldHaveGivenInstruments() throws InvalidInstrumentException {
        buildAnOperation();
        addTwoInstrument();

        boolean hasFirstInstrument = operation.hasInstrument(instrument);
        boolean hasSecondInstrument = operation.hasInstrument(otherInstrument);

        assertTrue(hasFirstInstrument);
        assertTrue(hasSecondInstrument);
    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenOperationWhenAddingInstrumentWithExistingSerialShouldThrowException()
            throws InvalidInstrumentException {
        buildAnOperation();

        operation.addInstrument(instrument);
        operation.addInstrument(instrument);
    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenEyeOperationWhenAddingAnonymousInstrumentShouldThrowException() throws InvalidInstrumentException {
        buildEyeOperation();

        operation.addInstrument(anonymousInstrument);
    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenHeartOperationWhenAddingAnonymousInstrumentShouldThrowException()
            throws InvalidInstrumentException {
        buildHeartOperation();

        operation.addInstrument(anonymousInstrument);
    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenMarrowOperationWhenAddingAnonymousInstrumentShouldThrowException()
            throws InvalidInstrumentException {
        buildMarrowOperation();

        operation.addInstrument(anonymousInstrument);
    }

    @Test
    public void givenEyeOperationWhenAddingInstrumentShouldNotThrowExceptionAndHaveSizeOfOne()
            throws InvalidInstrumentException {
        buildEyeOperation();

        operation.addInstrument(instrument);
        int instrumentCount = operation.countInstruments();

        assertEquals(1, instrumentCount);
    }

    @Test
    public void givenHearthOperationWhenAddingInstrumentShouldNotThrowExceptionAndHaveSizeOfOne()
            throws InvalidInstrumentException {
        buildHeartOperation();

        operation.addInstrument(instrument);
        int instrumentCount = operation.countInstruments();

        assertEquals(1, instrumentCount);
    }

    @Test
    public void givenMarrowOperationWhenAddingInstrumentShouldNotThrowExceptionAndHaveSizeOfOne()
            throws InvalidInstrumentException {
        buildMarrowOperation();

        operation.addInstrument(instrument);
        int instrumentCount = operation.countInstruments();

        assertEquals(1, instrumentCount);
    }

    @Test
    public void givenOncologyOperationWhenAddingAnonymousInstrumentShouldNotThrowExceptionAndHaveSizeOfOne()
            throws InvalidInstrumentException {
        buildOncologyOperation();

        operation.addInstrument(anonymousInstrument);
        int instrumentCount = operation.countInstruments();

        assertEquals(1, instrumentCount);
    }
}
