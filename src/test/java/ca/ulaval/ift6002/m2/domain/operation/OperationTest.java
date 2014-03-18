package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

    private static final OperationStatus OPERATION_STATUS = OperationStatus.PLANNED;
    private static final String DESCRIPTION = "description";
    private static final Room ROOM = new Room("room");
    private static final Surgeon SURGEON = new Surgeon(12345);
    private static final Date DATE = new Date();
    private static final Patient PATIENT = new Patient(12345);
    private static final int OPERATION_NUMBER = 1;

    private static final Serial A_SERIAL = new Serial("abc");
    private static final InstrumentStatus AN_INSTRUMENT_STATUS = InstrumentStatus.SOILED;

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anotherInstrument;

    @Mock
    private Instrument anonymousInstrument;

    private Operation operation;

    @Before
    public void setUpInstrument() {
        willReturn(false).given(instrument).isAnonymous();
        willReturn(true).given(instrument).hasASerial();
        willReturn(true).given(instrument).hasSameSerial(instrument);
        willReturn(false).given(instrument).hasSameSerial(anotherInstrument);
    }

    @Before
    public void setUpAnotherInstrument() {
        willReturn(true).given(anotherInstrument).hasASerial();
        willReturn(false).given(anotherInstrument).hasSameSerial(instrument);
        willReturn(true).given(anotherInstrument).hasSameSerial(anotherInstrument);
    }

    @Before
    public void setUpAnonymousInstrument() {
        willReturn(true).given(anonymousInstrument).isAnonymous();
    }

    @Test
    public void givenOperationShouldHaveZeroInstrument() {
        buildAnOperation();

        boolean hasInstrument = operation.hasInstruments();

        assertFalse(hasInstrument);
    }

    @Test
    public void givenOperationShouldNotHaveAnyInstrument() {
        buildAnOperation();
        boolean hasInstrument = operation.has(instrument);
        assertFalse(hasInstrument);
    }

    @Test
    public void givenTwoInstrumentsWithSerialWhenAddingToOperationShouldBeInOperationInstruments() {
        buildAnOperation();
        operation.add(instrument);
        operation.add(anotherInstrument);

        boolean hasInsturment = operation.has(instrument);
        boolean hasAnotherInstrument = operation.has(anotherInstrument);

        assertTrue(hasInsturment);
        assertTrue(hasAnotherInstrument);
    }

    @Test
    public void givenInstrumentWhenBookmarkInstrumentShouldCallChangeToNewStatus() {
        buildAnOperation();
        operation.add(instrument);
        willReturn(true).given(instrument).hasSerial(A_SERIAL);

        operation.bookmarkInstrumentToStatus(A_SERIAL, AN_INSTRUMENT_STATUS);

        verify(instrument).changeTo(AN_INSTRUMENT_STATUS);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenInstrumentWithNonExistingSerialWhenBookmarkInstrumentShouldThrowException() {
        buildAnOperation();
        operation.add(instrument);
        willReturn(false).given(instrument).hasSerial(A_SERIAL);

        operation.bookmarkInstrumentToStatus(A_SERIAL, AN_INSTRUMENT_STATUS);
    }

    @Test(expected = IllegalStateException.class)
    public void givenInstrumentWithExistingSerialNumberWhenAddingInstrumentShouldThrowException() {
        buildAnOperation();
        operation.add(instrument);
        operation.add(instrument);
    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenOperationWithInstrumentNotElligibleWhenAddInstrumentShouldThrowException() {
        buildAnOperationWhereInstrumentsAreNotElligible();

        operation.add(instrument);
    }

    @Test
    public void givenOperationShouldNotHaveNumber() {
        buildAnOperation();
        assertFalse(operation.hasNumber());
    }

    @Test
    public void givenOperationShouldHaveNumber() {
        buildAnOperationWithNumber();
        assertTrue(operation.hasNumber());
    }

    private void buildAnOperation() {
        operation = new Operation(DESCRIPTION, SURGEON, DATE, ROOM, OPERATION_STATUS, PATIENT) {

            @Override
            protected boolean isInstrumentElligible(Instrument instrument) {
                return true;
            }

            @Override
            public OperationType getType() {
                return OperationType.OTHER;
            }

        };
    }

    private void buildAnOperationWithNumber() {
        buildAnOperation();
        operation.updateNumber(OPERATION_NUMBER);
    }

    private void buildAnOperationWhereInstrumentsAreNotElligible() {
        operation = new Operation(DESCRIPTION, SURGEON, DATE, ROOM, OPERATION_STATUS, PATIENT) {

            @Override
            protected boolean isInstrumentElligible(Instrument instrument) {
                return false;
            }

            @Override
            public OperationType getType() {
                return OperationType.OTHER;
            }

        };
    }

}
