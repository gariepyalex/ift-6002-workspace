package projectH.infrastructure.persistence.inmemory.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;

@RunWith(MockitoJUnitRunner.class)
public class DrugInMemoryRepositoryTest {

	private static final String LESS_THAN_THREE_CHARACTERS_DRUG_NAME = "TY";

	private static final String UNEXISTING_BRAND_NAME = "UNEXISTING_BRAND_NAME";
	private static final String UNEXISTING_DESCRIPTOR = "UNEXISTING_DESCRIPTOR";

	private static final String EXISTING_BRAND_NAME = "TYLENOL";
	private static final String EXISTING_DESCRIPTOR_NAME = "ACETAMINOPHENE";

	private static final String CAMEL_CASE_EXISTING_BRAND_NAME = "TyLeNoL";

	private static final String SIMPLE_SEARCH_PATTERN = "TYL";
	private static final String SEARCH_PATTERN_WILDCARD = "TYL PRI";
	private static final String INVALID_KEYWORD = "123" + SEARCH_PATTERN_WILDCARD + "123";

	private static Drug TYLENOL = new Drug("111111", "TYLENOL", "ACETAMINOPHENE");;
	private static Drug TYLANETOL = new Drug("222222", "TYLANETOL PRIME", "IBUPROPHENE");

	private DrugRepository drugRepository;

	final private class MockedDrugInMemoryRepository extends DrugInMemoryRepository {

		@Override
		protected Collection<Drug> loadData() {
			Collection<Drug> drugs = new ArrayList<Drug>();

			drugs.add(TYLENOL);
			drugs.add(TYLANETOL);

			return drugs;
		}
	}

	@Before
	public void setup() {
		drugRepository = new MockedDrugInMemoryRepository();
	}

	@Test(expected = IllegalArgumentException.class)
	public void findByBrandNameOrDescriptorWhenLessThanThreeCharactersShouldThrowException() {
		drugRepository.findByBrandNameOrDescriptor(LESS_THAN_THREE_CHARACTERS_DRUG_NAME);
	}

	@Test
	public void findByBrandNameOrDescriptorWhenExistingBrandNameShouldReturnDrugs() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(EXISTING_BRAND_NAME);
		boolean result = drugsFound.isEmpty();
		assertFalse(result);
	}

	@Test
	public void findByBrandNameOrDescriptorWhenExistingDescriptorShouldReturnDrugs() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(EXISTING_DESCRIPTOR_NAME);
		boolean result = drugsFound.isEmpty();
		assertFalse(result);
	}

	@Test(expected = NoSuchElementException.class)
	public void findByBrandNameOrDescriptorWhenUnexistingBrandNameShouldReturnException() {
		drugRepository.findByBrandNameOrDescriptor(UNEXISTING_BRAND_NAME);
	}

	@Test(expected = NoSuchElementException.class)
	public void findByBrandNameOrDescriptorWhenUnexistingDescriptorShouldReturnException() {
		drugRepository.findByBrandNameOrDescriptor(UNEXISTING_DESCRIPTOR);
	}

	@Test
	public void findByBrandNameOrDescriptorWhenUsingPatternShouldReturnTwoDrugs() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(SIMPLE_SEARCH_PATTERN);
		int result = drugsFound.size();
		assertEquals(2, result);
	}

	@Test
	public void findByBrandNameOrDescriptionWhenUsingSimpleKeywordShouldContainsAllDrugsWithSameBeginning() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(SIMPLE_SEARCH_PATTERN);

		boolean resultContainsTylenol = drugsFound.contains(TYLENOL);
		boolean resultContainsTylanetol = drugsFound.contains(TYLANETOL);

		assertTrue(resultContainsTylenol);
		assertTrue(resultContainsTylanetol);
	}

	@Test
	public void findByBrandNameOrDescriptorWhenUsingPatternWithTwoWordShouldReturnDrugs() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(SEARCH_PATTERN_WILDCARD);
		boolean result = drugsFound.isEmpty();
		assertFalse(result);
	}

	@Test
	public void findByBrandNameOrDescriptorWhenUsingPatternWithTwoWordShouldContainsSpecificDrug() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(SEARCH_PATTERN_WILDCARD);
		boolean result = drugsFound.contains(TYLANETOL);
		assertTrue(result);
	}

	@Test(expected = NoSuchElementException.class)
	public void findByBrandNameOrDescriptorWhenUsingInvalidPatternShouldThrowException() {
		drugRepository.findByBrandNameOrDescriptor(INVALID_KEYWORD);
	}

	@Test
	public void findByBrandNameOrDescriptorWhenDifferentCaseShouldReturnDrugs() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(CAMEL_CASE_EXISTING_BRAND_NAME);
		boolean result = drugsFound.isEmpty();
		assertFalse(result);
	}

}
