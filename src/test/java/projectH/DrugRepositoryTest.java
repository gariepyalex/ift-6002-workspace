package projectH;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DrugRepositoryTest {

	private static final String UNEXISTING_DRUG_NAME = "UNEXISTING_DRUG";
	private static final String EXISTING_DRUG_NAME = "EXISTING_DRUG";
	private static final String EXISTING_DRUG_DIN = "1234567890";

	private static final Drug EXISTING_DRUG = new Drug(EXISTING_DRUG_DIN, EXISTING_DRUG_NAME, "", "");

	private static final String LESS_THAN_THREE_CHARACTERS_DRUG_NAME = "DR";

	private DrugRepository drugRepository;

	@Before
	public void setup() {
		drugRepository = new DrugRepository();
	}

	@Test
	public void findByNameExistingDrugShouldReturnADrug() {
		Drug drugFound = drugRepository.findByName(EXISTING_DRUG_NAME);
		assertEquals(EXISTING_DRUG, drugFound);
	}

	@Test(expected = NoSuchElementException.class)
	public void findByNameUnexistingDrugShouldReturnException() {
		drugRepository.findByName(UNEXISTING_DRUG_NAME);
	}

	@Test(expected = IllegalArgumentException.class)
	public void findByNameShouldThrowExceptionWhenLessThanThreeCharacters() {
		drugRepository.findByName(LESS_THAN_THREE_CHARACTERS_DRUG_NAME);
	}

}
