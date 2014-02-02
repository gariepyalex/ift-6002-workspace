package projectH.domain.prescription;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;

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
	private final String AN_INVALID_DIN = "-42";
	private final String A_VALID_DRUG_NAME = "Advil";
	private final Date A_VALID_DATE = getAValidDate();
	private final DrugRepository A_DRUG_REPOSITORY = getDrugRepositoryMock();
	private final String A_VALID_DATE_STRING = "1970-06-01T12:00:00";
	private final String AN_INVALID_DATE_STRING = "123.06.1t12.02.02";

	private Drug aDrug;

	@Before
	public void givenADimPrescription() {
		aDimPrescription = new Prescription(A_PRACTITIONER_NUMBER, A_VALID_DATE, A_VALID_RENEWALS, A_VALID_DIN, "",
				A_DRUG_REPOSITORY);
	}

	@Before
	public void givenDrug() {
		aDrug = new Drug(A_VALID_DIN, "", "");
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
	public void canGetDrugName() {
		Prescription prescription = new Prescription(A_PRACTITIONER_NUMBER, A_VALID_DATE, A_VALID_RENEWALS, "",
				A_VALID_DRUG_NAME, A_DRUG_REPOSITORY);
		assertEquals(A_VALID_DRUG_NAME, prescription.getDrugName());
	}

	@Test
	public void canGetDrug() {
		assertEquals(aDrug, aDimPrescription.getDrug());
	}

	@Test
	public void whenInstanciateWithDateStringDateStaysTheSame() {
		Prescription prescription = new Prescription(A_PRACTITIONER_NUMBER, A_VALID_DATE_STRING, A_VALID_RENEWALS, "",
				A_VALID_DRUG_NAME, A_DRUG_REPOSITORY);

		assertEquals(A_VALID_DATE_STRING, prescription.getDate());
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenInstanciateWithInvalidDateStringExceptionIsThrown() {
		new Prescription(A_PRACTITIONER_NUMBER, AN_INVALID_DATE_STRING, A_VALID_RENEWALS, "", A_VALID_DRUG_NAME,
				A_DRUG_REPOSITORY);
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenInvalidRenewalsIsEnteredExceptionIsThrown() {
		new Prescription(A_PRACTITIONER_NUMBER, A_VALID_DATE, AN_INVALID_RENEWALS, A_VALID_DIN, "", A_DRUG_REPOSITORY);
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenNoDinOrDrugNameIsEnteredExceptionIsThrown() {
		new Prescription(A_PRACTITIONER_NUMBER, A_VALID_DATE, A_VALID_RENEWALS, "", "", A_DRUG_REPOSITORY);
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenNoDinAndDrugNameAreFilledWithWhiteSpaceExceptionIsThrown() {
		new Prescription(A_PRACTITIONER_NUMBER, A_VALID_DATE, A_VALID_RENEWALS, "  ", "  ", A_DRUG_REPOSITORY);
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenDinAndDrugNameAreEnteredExceptionIsThrown() {
		new Prescription(A_PRACTITIONER_NUMBER, A_VALID_DATE, A_VALID_RENEWALS, A_VALID_DIN, A_VALID_DRUG_NAME,
				A_DRUG_REPOSITORY);
	}

	@Test(expected = InvalidPrescriptionException.class)
	public void whenInvalidDinIsEnteredExceptionIsThrown() {
		new Prescription(A_PRACTITIONER_NUMBER, A_VALID_DATE, A_VALID_RENEWALS, AN_INVALID_DIN, A_VALID_DRUG_NAME,
				A_DRUG_REPOSITORY);
	}

	private DrugRepository getDrugRepositoryMock() {
		DrugRepository drugRepositoryMock = mock(DrugRepository.class);
		when(drugRepositoryMock.isAValidDin(A_VALID_DIN)).thenReturn(true);
		when(drugRepositoryMock.isAValidDin(AN_INVALID_DIN)).thenReturn(false);
		return drugRepositoryMock;
	}

	private Date getAValidDate() {
		Calendar calendar = new GregorianCalendar(A_YEAR, A_MONTH, A_DAY, A_HOUR, A_MINUTE, A_SECOND);
		return calendar.getTime();
	}
}