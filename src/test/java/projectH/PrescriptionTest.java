package projectH;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class PrescriptionTest {

	private Prescription aDimPrescription;

	private final int A_PRACTITIONER_NUMBER = 123123;

	private final String A_FORMATTED_DATE = "1970-07-01 12:00:00";
	private final int A_YEAR = 1970;
	private final int A_MONTH = 6;
	private final int A_DAY = 1;
	private final int A_HOUR = 12;
	private final int A_MINUTE = 0;
	private final int A_SECOND = 0;

	private final int A_VALID_RENEWALS = 0;
	private final int AN_INVALID_RENEWALS = -1;

	@Before
	public void givenADimPrescription() {
		Date date = new GregorianCalendar(A_YEAR, A_MONTH, A_DAY, A_HOUR,
				A_MINUTE, A_SECOND).getTime();
		aDimPrescription = new Prescription(A_PRACTITIONER_NUMBER, date,
				A_VALID_RENEWALS, "ADVIL");
	}

	@Test
	public void canGetPractionerNumber() {
		assertEquals(A_PRACTITIONER_NUMBER, aDimPrescription.getPractioner());
	}

	@Test
	public void canGetDate() {
		System.out.print(aDimPrescription.getDate());
		assertEquals(A_FORMATTED_DATE, aDimPrescription.getDate());
	}

	@Test
	public void canGetRenewals() {
		assertEquals(A_VALID_RENEWALS, aDimPrescription.getRenewals());
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenInvalidRenewalsIsInteredExceptionIsThrown() {
		Date date = new GregorianCalendar(A_YEAR, A_MONTH, A_DAY, A_HOUR,
				A_MINUTE, A_SECOND).getTime();
		Prescription invalidPrescription = new Prescription(
				A_PRACTITIONER_NUMBER, date, AN_INVALID_RENEWALS, "ADVIL");
	}
}