package ca.ulaval.ift6002.m2.domain.surgery;

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
public class SurgeryTest {

    private static final Serial A_SERIAL = new Serial("abc");
    private static final InstrumentStatus AN_INSTRUMENT_STATUS = InstrumentStatus.SOILED;

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anotherInstrument;

    @Mock
    private Instrument anonymousInstrument;

    @Mock
    private SurgeryData surgeryData;

    private Surgery surgery;

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
    public void givenSurgeryWhenCallingHasInstrumentShouldCallSurgeryDataHasInstrument() {
        buildASurgery();

        surgery.hasInstruments();

        verify(surgeryData).hasInstruments();
    }

    @Test
    public void givenSurgeryShouldNotHaveAnyInstrument() {
        buildASurgery();
        boolean hasInstrument = surgery.has(instrument);
        assertFalse(hasInstrument);
    }

    @Test
    public void givenTwoInstrumentsWithSerialWhenAddingToSurgeryShouldSurgeryDataAddInstrument() {
        buildASurgery();
        surgery.add(instrument);
        surgery.add(anotherInstrument);

        verify(surgeryData).addInstrument(instrument);
        verify(surgeryData).addInstrument(anotherInstrument);
    }

    @Test
    public void givenInstrumentWhenBookmarkInstrumentShouldCallChangeToNewStatus() {
        buildASurgery();
        setupBookMarkInstrument();

        surgery.bookmarkInstrumentToStatus(A_SERIAL, AN_INSTRUMENT_STATUS);

        verify(instrument).changeTo(AN_INSTRUMENT_STATUS);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenInstrumentWithNonExistingSerialWhenBookmarkInstrumentShouldThrowException() {
        buildASurgery();
        surgery.add(instrument);
        willReturn(false).given(instrument).hasSerial(A_SERIAL);

        surgery.bookmarkInstrumentToStatus(A_SERIAL, AN_INSTRUMENT_STATUS);
    }

    @Test(expected = IllegalStateException.class)
    public void givenInstrumentWithExistingSerialNumberWhenAddingInstrumentShouldThrowException() {
        buildASurgery();
        setupInstrumentList();

        surgery.add(instrument);
        surgery.add(instrument);
    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenSurgeryWithInstrumentNotElligibleWhenAddInstrumentShouldThrowException() {
        buildAnSurgeryWhereInstrumentsAreNotElligible();

        surgery.add(instrument);
    }

    @Test
    public void givenSurgeryWithPlannedStatusWhenCheckingIfHasPlannedStatusShouldReturnTrue() {
        buildASurgery();
        willReturn(SurgeryStatus.PLANNED).given(surgeryData).getStatus();

        boolean hasStatusPlanned = surgery.hasStatus(SurgeryStatus.PLANNED);

        assertTrue(hasStatusPlanned);
    }

    @Test
    public void givenSurgeryWithPlannedStatusWhenCheckingIfHasAnotherStatusShouldReturnFalse() {
        buildASurgery();
        willReturn(SurgeryStatus.PLANNED).given(surgeryData).getStatus();

        boolean hasStatusFinish = surgery.hasStatus(SurgeryStatus.FINISH);

        assertFalse(hasStatusFinish);
    }

    private void buildASurgery() {

        surgery = new Surgery(surgeryData) {

            @Override
            protected boolean isInstrumentElligible(Instrument instrument) {
                return true;
            }

        };
    }

    private void buildAnSurgeryWhereInstrumentsAreNotElligible() {
        surgery = new Surgery(surgeryData) {

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
        willReturn(instruments).given(surgeryData).getInstruments();
    }

    private void setupInstrumentList() {
        List<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(instrument);

        willReturn(instruments).given(surgeryData).getInstruments();
    }

}
