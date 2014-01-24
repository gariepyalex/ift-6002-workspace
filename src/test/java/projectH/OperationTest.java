package projectH;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OperationTest {

	public Operation operation;

	private static final String EXPECTED_DESCRIPTION = "Description Test";
	private static final int EXPECTED_SURGEON = 101224;
	private static final String EXPECTED_DATE = "0000-00-00T24:01:00";
	private static final String EXPECTED_TYPE = "EYE";
	private static final String EXPECTED_STATUS = "CANCELED";
	private static final String DEFAULT_STATUS = "PLANNED";

	@Before
	public void givenAnOperation() {
		operation = new Operation(EXPECTED_DESCRIPTION, EXPECTED_SURGEON, EXPECTED_DATE, EXPECTED_TYPE, EXPECTED_STATUS);
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
}
