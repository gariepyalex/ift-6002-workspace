package projectH.infrastructure.persistence.inmemory.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;

@RunWith(MockitoJUnitRunner.class)
public class DrugInMemoryRepositoryTest {

	private static final String LESS_THAN_THREE_CHARACTERS_DRUG_NAME = "DR";
	private static final String UNEXISTING_DRUG_NAME = "UNEXISTING_DRUG";
	private static final String EXISTING_DRUG_NAME = "TYLENOL";
	private static final String EXISTING_DESCRIPTOR_NAME = "ACETAMINOPHENE";
	private static final String EXISTING_CASE_DRUG_NAME = "TyLeNoL";
	private static final String SEARCH_PATTERN = "TYL";
	private static final String SEARCH_PATTERN_WILDCARD = "TYL PRI";

	private static final Drug EXISTING_DRUG_TYLENOL = new Drug("111111", "TYLENOL", "ACETAMINOPHENE");
	private static final Drug EXISTING_DRUG_TYLANETOL = new Drug("222222", "TYLANETOL PRIME", "IBUPROPHENE");

	private class MockedDrugRepo extends DrugInMemoryRepository {
		@Override
		protected Collection<Drug> loadData() {
			Collection<Drug> drugs = new ArrayList<Drug>();
			drugs.add(EXISTING_DRUG_TYLENOL);
			drugs.add(EXISTING_DRUG_TYLANETOL);
			return drugs;

		}
	}

	private DrugRepository drugRepository;

	@Before
	public void setup() {
		drugRepository = new MockedDrugRepo();
	}

	@Test(expected = IllegalArgumentException.class)
	public void findByBrandNameOrDescriptorWhenLessThanThreeCharactersShouldThrowException() {
		drugRepository.findByBrandNameOrDescriptor(LESS_THAN_THREE_CHARACTERS_DRUG_NAME);
	}

	@Test(expected = NoSuchElementException.class)
	public void findByBrandNameOrDescriptorWhenUnexistingDrugShouldReturnException() {
		drugRepository.findByBrandNameOrDescriptor(UNEXISTING_DRUG_NAME);
	}

	@Test
	public void findByBrandNameOrDescriptorWhenExistingBrandNameShouldReturnDrug() {

		List<Drug> drugFound = drugRepository.findByBrandNameOrDescriptor(EXISTING_DRUG_NAME);

		for (Drug drug : drugFound) {
			assertTrue(drug.equals(EXISTING_DRUG_TYLENOL));
		}

	}

	@Test
	public void findByBrandNameOrDescriptorWhenExistingDescriptorShouldReturnDrug() {
		List<Drug> drugFound = drugRepository.findByBrandNameOrDescriptor(EXISTING_DESCRIPTOR_NAME);

		for (Drug drug : drugFound) {
			assertTrue(drug.equals(EXISTING_DRUG_TYLENOL));
		}
	}

	@Test
	public void findByBrandNameOrDescriptorWhenAnyCasesShouldNotMatterAndReturnDrug() {
		List<Drug> drugFound = drugRepository.findByBrandNameOrDescriptor(EXISTING_CASE_DRUG_NAME);

		for (Drug drug : drugFound) {
			assertTrue(drug.equals(EXISTING_DRUG_TYLENOL));
		}
	}

	@Test
	public void findByBrandNameOrDescriptorWhenUsingPatternShouldReturnTwoDrug() {

		List<Drug> drugFound = drugRepository.findByBrandNameOrDescriptor(SEARCH_PATTERN);

		assertTrue(drugFound.get(0).equals(EXISTING_DRUG_TYLENOL));
		assertTrue(drugFound.get(1).equals(EXISTING_DRUG_TYLANETOL));
	}

	@Test
	public void findByBrandNameOrDescriptorWhenUsingPatternWithTwoWordShouldReturnDrug() {

		List<Drug> drugFound = drugRepository.findByBrandNameOrDescriptor(SEARCH_PATTERN_WILDCARD);

		for (Drug drug : drugFound) {
			assertTrue(drug.equals(EXISTING_DRUG_TYLANETOL));
		}
	}

}
