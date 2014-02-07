package projectH.domain.operation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InvalidInstrumentException;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

	private Operation eyeOperation;
	private Operation heartOperation;
	private Operation marrowOperation;
	private Operation oncologyOperation;

	private static final String EXPECTED_DESCRIPTION = "Description Test";
	private static final int EXPECTED_SURGEON = 101224;
	private static final String EXPECTED_DATE = "0000-00-00T24:01:00";
	private static final InterventionType EXPECTED_TYPE = InterventionType.EYE;
	private static final InterventionType MARROW_TYPE = InterventionType.MARROW;
	private static final InterventionType HEART_TYPE = InterventionType.HEART;
	private static final InterventionType ONCOLOGY_TYPE = InterventionType.ONCOLOGY;
	private static final InterventionStatus EXPECTED_STATUS = InterventionStatus.CANCELED;
	private static final InterventionStatus DEFAULT_STATUS = InterventionStatus.PLANNED;

	@Mock
	private Instrument INSTRUMENT_1;

	@Mock
	private Instrument INSTRUMENT_2;

	@Before
	public void givenAnOperation() {
		eyeOperation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, EXPECTED_TYPE,
				EXPECTED_STATUS);
		heartOperation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, HEART_TYPE,
				EXPECTED_STATUS);
		marrowOperation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, MARROW_TYPE,
				EXPECTED_STATUS);
		oncologyOperation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, ONCOLOGY_TYPE,
				EXPECTED_STATUS);

	}

	private void addOneInstrument() throws InvalidInstrumentException {
		eyeOperation.addInstrument(INSTRUMENT_1);
	}

	private void addTwoInstrument() throws InvalidInstrumentException {
		eyeOperation.addInstrument(INSTRUMENT_1);
		eyeOperation.addInstrument(INSTRUMENT_2);
	}

	@Test
	public void canHaveADescription() {
		assertEquals(EXPECTED_DESCRIPTION, eyeOperation.getDescription());
	}

	@Test
	public void canHaveASurgeon() {
		assertEquals(EXPECTED_SURGEON, eyeOperation.getSurgeon());
	}

	@Test
	public void canHaveADate() {
		assertEquals(EXPECTED_DATE, eyeOperation.getDate());
	}

	@Test
	public void canHaveAType() {
		assertEquals(EXPECTED_TYPE, eyeOperation.getType());
	}

	@Test
	public void canHaveAStatus() {
		assertEquals(EXPECTED_STATUS, eyeOperation.getStatus());
	}

	@Test
	public void ifNotGivenStatusIsSetToPlanned() {
		eyeOperation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, EXPECTED_TYPE);
		assertEquals(DEFAULT_STATUS, eyeOperation.getStatus());
	}

	@Test
	public void createdNewOperationShouldHaveZeroInstrument() {
		assertEquals(0, eyeOperation.getNumberOfInstrument());
	}

	@Test
	public void operationShouldNotHaveMissingInstrument() {
		Instrument missingInstrument = mock(Instrument.class);
		boolean result = eyeOperation.hasInstrument(missingInstrument);
		assertFalse(result);
	}

	@Test
	public void addingOneInstrumentToOperationShouldHaveOneInstrument() throws InvalidInstrumentException {
		addOneInstrument();
		assertEquals(1, eyeOperation.getNumberOfInstrument());
	}

	@Test
	public void addingOneInstrumentToOperationShouldHaveGivenInstrument() throws InvalidInstrumentException {
		addOneInstrument();
		boolean hasInstrument = eyeOperation.hasInstrument(INSTRUMENT_1);
		assertTrue(hasInstrument);
	}

	@Test
	public void addingTwoInstrumentsToOperationShouldHaveTwoInstruments() throws InvalidInstrumentException {
		addTwoInstrument();
		assertEquals(2, eyeOperation.getNumberOfInstrument());
	}

	@Test
	public void addingTwoInstrumentsToOperationShouldHaveGivenInstruments() throws InvalidInstrumentException {
		addTwoInstrument();
		boolean hasFirstInstrument = eyeOperation.hasInstrument(INSTRUMENT_1);
		boolean hasSecondInstrument = eyeOperation.hasInstrument(INSTRUMENT_2);

		assertTrue(hasFirstInstrument);
		assertTrue(hasSecondInstrument);
	}

	@Test(expected = InvalidInstrumentException.class)
	public void addingInstrumentWithExistingSerialShouldThrowInvalidInstrumentException()
			throws InvalidInstrumentException {

		eyeOperation.addInstrument(INSTRUMENT_1);
		eyeOperation.addInstrument(INSTRUMENT_1);
	}

	@Test(expected = InvalidInstrumentException.class)
	public void addingAnonymousInstrumentToEyeOperationShouldThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(INSTRUMENT_1.isAnonymous()).thenReturn(true);
		eyeOperation.addInstrument(INSTRUMENT_1);
	}

	@Test(expected = InvalidInstrumentException.class)
	public void addingAnonymousInstrumentToHeartOperationShouldThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(INSTRUMENT_1.isAnonymous()).thenReturn(true);
		heartOperation.addInstrument(INSTRUMENT_1);
	}

	@Test(expected = InvalidInstrumentException.class)
	public void addingAnonymousInstrumentToMarrowOperationShouldThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(INSTRUMENT_1.isAnonymous()).thenReturn(true);
		marrowOperation.addInstrument(INSTRUMENT_1);
	}

	@Test
	public void addingInstrumentToEyeOperationShouldNotThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(INSTRUMENT_1.isAnonymous()).thenReturn(false);
		eyeOperation.addInstrument(INSTRUMENT_1);
	}

	@Test
	public void addingInstrumentToHeartOperationShouldNotThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(INSTRUMENT_1.isAnonymous()).thenReturn(false);
		heartOperation.addInstrument(INSTRUMENT_1);
	}

	@Test
	public void addingInstrumentToMarrowOperationShouldNotThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(INSTRUMENT_1.isAnonymous()).thenReturn(false);
		marrowOperation.addInstrument(INSTRUMENT_1);
	}

	@Test
	public void addingAnonymousInstrumentToOncologyOperationShouldNotThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(INSTRUMENT_1.isAnonymous()).thenReturn(true);
		oncologyOperation.addInstrument(INSTRUMENT_1);
	}
}