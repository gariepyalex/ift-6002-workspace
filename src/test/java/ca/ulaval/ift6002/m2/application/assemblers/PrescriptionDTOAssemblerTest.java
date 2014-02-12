package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionDTO;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionDTOAssemblerTest {

	private static final Practitioner PRACTITIONER = new Practitioner("A random name");
	private static final String DATE_AS_STRING = "2014-01-03T12:00:00";
	private static final Date DATE = new Date();
	private static final int RENEWALS = 1;

	private static final Din DIN = new Din("A random din");
	private static final String BRAND_NAME = "A random brand name";
	private static final String DESCRIPTOR = "A random descriptor";
	private static final Drug DRUG = new Drug(DIN, BRAND_NAME, DESCRIPTOR);

	private static final Prescription PRESCRIPTION = new Prescription(PRACTITIONER, DATE, RENEWALS, DRUG);

	private static final PrescriptionDTO PRESCRIPTION_DTO = new PrescriptionDTO(PRACTITIONER.toString(),
			DATE_AS_STRING, RENEWALS, DIN.toString(), BRAND_NAME);

	@Mock
	private DrugRepository drugRepository;

	@Mock
	private DateFormatter dateFormatter;

	@InjectMocks
	private PrescriptionDTOAssembler prescriptionAssembler;

	@Test
	public void givenPrescriptionWhenConvertToDTOShouldReturnGivenPrescriptionDTO() {
		willReturn(DATE_AS_STRING).given(dateFormatter).dateToString(DATE);

		PrescriptionDTO dtoBuilt = prescriptionAssembler.toDTO(PRESCRIPTION);

		assertPrescriptionDTOEquals(PRESCRIPTION_DTO, dtoBuilt);
	}

	@Test
	public void givenPrescriptionDTOWhenConvertToPrescriptionShouldReturnGivenPrescription() {
		willReturn(DRUG).given(drugRepository).get(DIN);
		willReturn(DATE).given(dateFormatter).parse(DATE_AS_STRING);

		Prescription prescriptionBuilt = prescriptionAssembler.fromDTO(PRESCRIPTION_DTO);

		assertEquals(PRESCRIPTION, prescriptionBuilt);
	}

	private void assertPrescriptionDTOEquals(PrescriptionDTO expected, PrescriptionDTO actual) {
		assertEquals(expected.practitioner, actual.practitioner);
		assertEquals(expected.date, actual.date);
		assertEquals(expected.renewals, actual.renewals);
		assertEquals(expected.name, actual.name);
		assertEquals(expected.din, actual.din);
	}
}
