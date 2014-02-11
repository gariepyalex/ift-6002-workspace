package projectH.application.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import projectH.application.responses.PrescriptionDTO;
import projectH.domain.date.DateFormatter;
import projectH.domain.drug.Din;
import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;
import projectH.domain.prescription.Practitioner;
import projectH.domain.prescription.Prescription;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionDTOAssemblerTest {

	private final static DateFormatter FORMATTER = new DateFormatter();

	private final static Practitioner PRACTITIONER = new Practitioner("A random name");
	private final static String DATE_AS_STRING = "2014-01-03T12:00:00";
	private final static Date DATE = FORMATTER.parse(DATE_AS_STRING);
	private final static int RENEWALS = 1;

	private final static Din DIN = new Din("A random din");
	private final static String BRAND_NAME = "A random brand name";
	private final static String DESCRIPTOR = "A random descriptor";
	private final static Drug DRUG = new Drug(DIN, BRAND_NAME, DESCRIPTOR);

	private final static Prescription PRESCRIPTION = new Prescription(PRACTITIONER, DATE, RENEWALS, DRUG);

	private final static PrescriptionDTO PRESCRIPTION_DTO = new PrescriptionDTO(PRACTITIONER.toString(),
			DATE_AS_STRING, RENEWALS, DIN.toString(), BRAND_NAME);

	@Mock
	private DrugRepository drugRepository;

	@InjectMocks
	private PrescriptionDTOAssembler assembler;

	@Before()
	public void setup() {
		assembler = new PrescriptionDTOAssembler(drugRepository);
	}

	@Test
	public void givenPrescriptionShouldReturnDTO() {
		PrescriptionDTO dtoBuilt = assembler.toDTO(PRESCRIPTION);

		assertPrescriptionDTOEquals(PRESCRIPTION_DTO, dtoBuilt);
	}

	@Test
	public void givenPrescriptionDTOShouldReturnPrescription() {
		willReturn(DRUG).given(drugRepository).get(any(Din.class));

		Prescription prescriptionBuilt = assembler.fromDTO(PRESCRIPTION_DTO);

		assertEquals(PRESCRIPTION, prescriptionBuilt);
	}

	private void assertPrescriptionDTOEquals(PrescriptionDTO expected, PrescriptionDTO actual) {
		assertEquals(expected.intervenant, actual.intervenant);
		assertEquals(expected.date, actual.date);
		assertEquals(expected.renouvellements, actual.renouvellements);
		assertEquals(expected.nom, actual.nom);
		assertEquals(expected.din, actual.din);
	}
}
