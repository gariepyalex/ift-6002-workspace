package projectH.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InvalidInstrumentException;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

	public Operation operation;

	private static final String EXPECTED_DESCRIPTION = "Description Test";
	private static final int EXPECTED_SURGEON = 101224;
	private static final String EXPECTED_DATE = "0000-00-00T24:01:00";
	private static final InterventionType EXPECTED_TYPE = InterventionType.EYE;
	private static final InterventionStatus EXPECTED_STATUS = InterventionStatus.CANCELED;
	private static final InterventionStatus DEFAULT_STATUS = InterventionStatus.PLANNED;

	@Mock
	private Instrument INSTRUMENT_1;

	@Mock
	private Instrument INSTRUMENT_2;

	@Before
	public void givenAnOperation() {
		operation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, EXPECTED_TYPE, EXPECTED_STATUS);
	}

	private void addOneInstrument() throws InvalidInstrumentException {
		operation.addInstrument(INSTRUMENT_1);
	}

	private void addTwoInstrument() throws InvalidInstrumentException {
		operation.addInstrument(INSTRUMENT_1);
		operation.addInstrument(INSTRUMENT_2);
	}

	@Test
	public void canHaveADescription() {
		assertEquals(EXPECTED_DESCRIPTION, operation.getDescription());
	}

	@Test
	public void canHaveASurgeon() {
		assertEquals(EXPECTED_SURGEON, operation.getSurgeon());
	}

	@Test
	public void canHaveADate() {
		assertEquals(EXPECTED_DATE, operation.getDate());
	}

	@Test
	public void canHaveAType() {
		assertEquals(EXPECTED_TYPE, operation.getType());
	}

	@Test
	public void canHaveAStatus() {
		assertEquals(EXPECTED_STATUS, operation.getStatus());
	}

	@Test
	public void ifNotGivenStatusIsSetToPlanned() {
		operation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, EXPECTED_TYPE);
		assertEquals(DEFAULT_STATUS, operation.getStatus());
	}

	@Test
	public void createdNewOperationShouldHaveZeroInstrument() {
		assertEquals(0, operation.getNumberOfInstrument());
	}

	@Test
	public void operationShouldNotHaveMissingInstrument() {
		Instrument missingInstrument = mock(Instrument.class);
		boolean result = operation.hasInstrument(missingInstrument);
		assertFalse(result);
	}

	@Test
	public void addingOneInstrumentToOperationShouldHaveOneInstrument() throws InvalidInstrumentException {
		addOneInstrument();
		assertEquals(1, operation.getNumberOfInstrument());
	}

	@Test
	public void addingOneInstrumentToOperationShouldHaveGivenInstrument() throws InvalidInstrumentException {
		addOneInstrument();
		boolean hasInstrument = operation.hasInstrument(INSTRUMENT_1);
		assertTrue(hasInstrument);
	}

	@Test
	public void adding2InstrumentToOperationShouldHaveTwoInstruments() throws InvalidInstrumentException {
		addTwoInstrument();
		assertEquals(2, operation.getNumberOfInstrument());
	}

	@Test
	public void adding2InstrumentToOperationShouldHaveGivenInstruments() throws InvalidInstrumentException {
		addTwoInstrument();
		boolean hasFirstInstrument = operation.hasInstrument(INSTRUMENT_1);
		boolean hasSecondInstrument = operation.hasInstrument(INSTRUMENT_2);

		assertTrue(hasFirstInstrument);
		assertTrue(hasSecondInstrument);
	}

	@Test(expected = InvalidInstrumentException.class)
	public void addingInstrumentWithExistingSerialShouldThrowInvalidInstrumentException()
			throws InvalidInstrumentException {

		operation.addInstrument(INSTRUMENT_1);
		operation.addInstrument(INSTRUMENT_1);
	}

}