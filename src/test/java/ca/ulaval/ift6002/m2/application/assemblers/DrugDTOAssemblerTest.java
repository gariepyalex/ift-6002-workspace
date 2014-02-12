package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.DrugDTO;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;

public class DrugDTOAssemblerTest {

	private static final Din DIN = new Din("A random din");
	private static final String BRAND_NAME = "A random brand name";
	private static final String DESCRIPTOR = "A random descriptor";

	private static final Drug DRUG = new Drug(DIN, BRAND_NAME, DESCRIPTOR);
	private static final DrugDTO DRUG_DTO = new DrugDTO(DIN.toString(), BRAND_NAME, DESCRIPTOR);

	private static final Collection<Drug> DRUGS = Arrays.asList(DRUG);
	private static final DrugDTO[] DRUG_DTOS = { DRUG_DTO };

	private DrugDTOAssembler drugAssembler;

	@Before
	public void setup() {
		drugAssembler = new DrugDTOAssembler();
	}

	@Test
	public void givenDrugWhenConvertToDTOShouldReturnGivenDrugDTO() {
		DrugDTO dtoBuilt = drugAssembler.toDTO(DRUG);

		assertDrugDTOEquals(DRUG_DTO, dtoBuilt);
	}

	@Test
	public void givenDrugsWhenConvertToDTOsShouldReturnGivenDrugDTOs() {
		DrugDTO[] dtosBuilt = drugAssembler.toDTOs(DRUGS);

		assertDrugDTOsEquals(dtosBuilt, DRUG_DTOS);
	}

	private void assertDrugDTOsEquals(DrugDTO[] expected, DrugDTO[] actual) {
		for (int i = 0; i < expected.length; ++i) {
			assertDrugDTOEquals(expected[i], actual[i]);
		}
	}

	private void assertDrugDTOEquals(DrugDTO expected, DrugDTO actual) {
		assertEquals(expected.din, actual.din);
		assertEquals(expected.brandName, actual.brandName);
		assertEquals(expected.description, actual.description);
	}

}
