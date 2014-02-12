package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

	private Operation eyeOperation;
	private Operation heartOperation;
	private Operation marrowOperation;
	private Operation oncologyOperation;

	private static final String EXPECTED_DESCRIPTION = "Description Test";
	private static final int EXPECTED_SURGEON = 101224;
	private static final Date EXPECTED_DATE = new Date();
	private static final String EXPECTED_ROOM = "salleB";
	private static final OperationType EXPECTED_TYPE = OperationType.EYE;
	private static final OperationType MARROW_TYPE = OperationType.MARROW;
	private static final OperationType HEART_TYPE = OperationType.HEART;
	private static final OperationType ONCOLOGY_TYPE = OperationType.ONCOLOGY;
	private static final OperationStatus EXPECTED_STATUS = OperationStatus.CANCELED;
	private static final OperationStatus DEFAULT_STATUS = OperationStatus.PLANNED;
	private static final int EXPECTED_PATIENT = 2;
	private static final String INVALID_DATE = "";

	@Mock
	private Instrument anInstrument;

	@Mock
	private Instrument differentInstrument;

	@Before
	public void givenAnOperation() {
		eyeOperation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, EXPECTED_ROOM,
				EXPECTED_TYPE, EXPECTED_STATUS, EXPECTED_PATIENT);
		heartOperation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, EXPECTED_ROOM,
				HEART_TYPE, EXPECTED_STATUS, EXPECTED_PATIENT);
		marrowOperation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, EXPECTED_ROOM,
				MARROW_TYPE, EXPECTED_STATUS, EXPECTED_PATIENT);
		oncologyOperation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, EXPECTED_ROOM,
				ONCOLOGY_TYPE, EXPECTED_STATUS, EXPECTED_PATIENT);

	}

	private void addOneInstrument() throws InvalidInstrumentException {
		eyeOperation.addInstrument(anInstrument);
	}

	private void addTwoInstrument() throws InvalidInstrumentException {
		eyeOperation.addInstrument(anInstrument);
		eyeOperation.addInstrument(differentInstrument);
	}

	@Test
	public void ifNotGivenStatusIsSetToPlanned() {
		eyeOperation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, EXPECTED_ROOM,
				EXPECTED_TYPE, EXPECTED_PATIENT);
		assertEquals(DEFAULT_STATUS, eyeOperation.getStatus());
	}

	@Test
	public void createdNewOperationShouldHaveZeroInstrument() {
		assertEquals(0, eyeOperation.getNumberOfInstrument());
	}

	@Test
	public void operationShouldNotHaveMissingInstrument() {
		Instrument missingInstrument = mock(Instrument.class);
		boolean hasMissingInstrument = eyeOperation.hasInstrument(missingInstrument);
		assertFalse(hasMissingInstrument);
	}

	@Test
	public void addingOneInstrumentToOperationShouldHaveOneInstrument() throws InvalidInstrumentException {
		addOneInstrument();
		assertEquals(1, eyeOperation.getNumberOfInstrument());
	}

	@Test
	public void addingOneInstrumentToOperationShouldHaveGivenInstrument() throws InvalidInstrumentException {
		addOneInstrument();
		boolean hasInstrument = eyeOperation.hasInstrument(anInstrument);
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
		boolean hasFirstInstrument = eyeOperation.hasInstrument(anInstrument);
		boolean hasSecondInstrument = eyeOperation.hasInstrument(differentInstrument);

		assertTrue(hasFirstInstrument);
		assertTrue(hasSecondInstrument);
	}

	@Test(expected = InvalidInstrumentException.class)
	public void addingInstrumentWithExistingSerialShouldThrowInvalidInstrumentException()
			throws InvalidInstrumentException {

		eyeOperation.addInstrument(anInstrument);
		eyeOperation.addInstrument(anInstrument);
	}

	@Test(expected = InvalidInstrumentException.class)
	public void addingAnonymousInstrumentToEyeOperationShouldThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(anInstrument.isAnonymous()).thenReturn(true);
		eyeOperation.addInstrument(anInstrument);
	}

	@Test(expected = InvalidInstrumentException.class)
	public void addingAnonymousInstrumentToHeartOperationShouldThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(anInstrument.isAnonymous()).thenReturn(true);
		heartOperation.addInstrument(anInstrument);
	}

	@Test(expected = InvalidInstrumentException.class)
	public void addingAnonymousInstrumentToMarrowOperationShouldThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(anInstrument.isAnonymous()).thenReturn(true);
		marrowOperation.addInstrument(anInstrument);
	}

	@Test
	public void addingInstrumentToEyeOperationShouldNotThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(anInstrument.isAnonymous()).thenReturn(false);
		eyeOperation.addInstrument(anInstrument);
	}

	@Test
	public void addingInstrumentToHeartOperationShouldNotThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(anInstrument.isAnonymous()).thenReturn(false);
		heartOperation.addInstrument(anInstrument);
	}

	@Test
	public void addingInstrumentToMarrowOperationShouldNotThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(anInstrument.isAnonymous()).thenReturn(false);
		marrowOperation.addInstrument(anInstrument);
	}

	@Test
	public void addingAnonymousInstrumentToOncologyOperationShouldNotThrowInvalidInstrumentException()
			throws InvalidInstrumentException {
		when(anInstrument.isAnonymous()).thenReturn(true);
		oncologyOperation.addInstrument(anInstrument);
	}
}
