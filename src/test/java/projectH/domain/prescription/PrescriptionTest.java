package projectH.domain.prescription;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class PrescriptionTest {

	private Prescription aDimPrescription;

	private final int A_PRACTITIONER_NUMBER = 123123;

	private final String A_FORMATTED_DATE = "1970-07-01T12:00:00";
	private final int A_YEAR = 1970;
	private final int A_MONTH = 6;
	private final int A_DAY = 1;
	private final int A_HOUR = 12;
	private final int A_MINUTE = 0;
	private final int A_SECOND = 0;

	private final int A_VALID_RENEWALS = 0;
	private final int AN_INVALID_RENEWALS = -1;
	private final String A_VALID_DIN = "111111";
	private final String A_VALID_MEDECINE_NAME = "Advil";

	@Before
	public void givenADimPrescription() {
		GregorianCalendar date = new GregorianCalendar(A_YEAR, A_MONTH, A_DAY, A_HOUR, A_MINUTE, A_SECOND);
		aDimPrescription = new Prescription(A_PRACTITIONER_NUMBER, date, A_VALID_RENEWALS, A_VALID_DIN, "");
	}

	@Test
	public void canGetPractionerNumber() {
		assertEquals(A_PRACTITIONER_NUMBER, aDimPrescription.getPractioner());
	}

	@Test
	public void canGetDate() {
		assertEquals(A_FORMATTED_DATE, aDimPrescription.getDate());
	}

	@Test
	public void canGetRenewals() {
		assertEquals(A_VALID_RENEWALS, aDimPrescription.getRenewals());
	}

	@Test
	public void canGetDin() {
		assertEquals(A_VALID_DIN, aDimPrescription.getDin());
	}

	@Test
	public void canGetMedecineName() {
		Prescription prescription = new Prescription(A_PRACTITIONER_NUMBER, new GregorianCalendar(), A_VALID_RENEWALS,
				"", A_VALID_MEDECINE_NAME);
		assertEquals(A_VALID_MEDECINE_NAME, prescription.getMedecineName());
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenInvalidRenewalsIsInteredExceptionIsThrown() {
		GregorianCalendar date = new GregorianCalendar(A_YEAR, A_MONTH, A_DAY, A_HOUR, A_MINUTE, A_SECOND);
		new Prescription(A_PRACTITIONER_NUMBER, date, AN_INVALID_RENEWALS, A_VALID_DIN, "");
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenNoDinOrMedecineNameIsInteredExceptionIsThrown() {
		GregorianCalendar date = new GregorianCalendar(A_YEAR, A_MONTH, A_DAY, A_HOUR, A_MINUTE, A_SECOND);
		new Prescription(A_PRACTITIONER_NUMBER, date, A_VALID_RENEWALS, "", "");
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenNoDinAndMedecineNameAreFilledWithWhiteSpaceExceptionIsThrown() {
		GregorianCalendar date = new GregorianCalendar(A_YEAR, A_MONTH, A_DAY, A_HOUR, A_MINUTE, A_SECOND);
		new Prescription(A_PRACTITIONER_NUMBER, date, A_VALID_RENEWALS, "  ", "  ");
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenDinAndMedecineNameIsInteredExceptionIsThrown() {
		GregorianCalendar date = new GregorianCalendar(A_YEAR, A_MONTH, A_DAY, A_HOUR, A_MINUTE, A_SECOND);
		new Prescription(A_PRACTITIONER_NUMBER, date, A_VALID_RENEWALS, A_VALID_DIN, A_VALID_MEDECINE_NAME);
	}
}