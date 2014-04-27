package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
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

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

    private static final Serial A_SERIAL = new Serial("abc");
    private static final InstrumentStatus AN_INSTRUMENT_STATUS = InstrumentStatus.SOILED;

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anotherInstrument;

    @Mock
    private Instrument anonymousInstrument;

    @Mock
    private OperationData operationData;

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
    public void givenOperationWhenCallingHasInstrumentShouldCallOperationDataHasInstrument() {
        buildAnOperation();

        operation.hasInstruments();

        verify(operationData).hasInstruments();
    }

    @Test
    public void givenOperationShouldNotHaveAnyInstrument() {
        buildAnOperation();
        boolean hasInstrument = operation.has(instrument);
        assertFalse(hasInstrument);
    }

    @Test
    public void givenTwoInstrumentsWithSerialWhenAddingToOperationShouldOperationDataAddInstrument() {
        buildAnOperation();
        operation.add(instrument);
        operation.add(anotherInstrument);

        verify(operationData).addInstrument(instrument);
        verify(operationData).addInstrument(anotherInstrument);
    }

    @Test
    public void givenInstrumentWhenBookmarkInstrumentShouldCallChangeToNewStatus() {
        buildAnOperation();
        setupBookMarkInstrument();

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
        setupInstrumentList();

        operation.add(instrument);
        operation.add(instrument);
    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenOperationWithInstrumentNotElligibleWhenAddInstrumentShouldThrowException() {
        buildAnOperationWhereInstrumentsAreNotElligible();

        operation.add(instrument);
    }

    @Test
    public void givenOperationWithPlannedStatusWhenCheckingIfHasPlannedStatusShouldReturnTrue() {
        buildAnOperation();
        willReturn(OperationStatus.PLANNED).given(operationData).getStatus();

        boolean hasStatusPlanned = operation.hasStatus(OperationStatus.PLANNED);

        assertTrue(hasStatusPlanned);
    }

    @Test
    public void givenOperationWithPlannedStatusWhenCheckingIfHasAnotherStatusShouldReturnFalse() {
        buildAnOperation();
        willReturn(OperationStatus.PLANNED).given(operationData).getStatus();

        boolean hasStatusFinish = operation.hasStatus(OperationStatus.FINISH);

        assertFalse(hasStatusFinish);
    }

    private void buildAnOperation() {

        operation = new Operation(operationData) {

            @Override
            protected boolean isInstrumentElligible(Instrument instrument) {
                return true;
            }

        };
    }

    private void buildAnOperationWhereInstrumentsAreNotElligible() {
        operation = new Operation(operationData) {

            @Override
            protected boolean isInstrumentElligible(Instrument instrument) {
                return false;
            }

        };
    }

    private void setupBookMarkInstrument() {
        List<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(instrument);

        willReturn(true).given(instrument).hasSerial(A_SERIAL);
        willReturn(instruments).given(operationData).getInstruments();
    }

    private void setupInstrumentList() {
        List<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(instrument);

        willReturn(instruments).given(operationData).getInstruments();
    }

}
