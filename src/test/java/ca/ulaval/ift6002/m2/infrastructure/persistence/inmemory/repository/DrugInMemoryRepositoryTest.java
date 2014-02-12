package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.CSVDrugDataAdapter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugDataAdapter;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;

@RunWith(MockitoJUnitRunner.class)
public class DrugInMemoryRepositoryTest {

	private static final Din TYLENOL_DIN = new Din("111111");
	private static final Din TYLANETOL_DIN = new Din("222222");
	private static final Din UNEXISTING_DIN = new Din("abcde");

	private static final String LESS_THAN_THREE_CHARACTERS_DRUG_NAME = "TY";

	private static final String UNEXISTING_BRAND_NAME = "UNEXISTING_BRAND_NAME";
	private static final String UNEXISTING_DESCRIPTOR = "UNEXISTING_DESCRIPTOR";

	private static final String TYLENOL_BRAND_NAME = "TYLENOL";
	private static final String TYLENOL_DESCRIPTOR_NAME = "ACETAMINOPHENE";

	private static Drug TYLENOL = new Drug(TYLENOL_DIN, TYLENOL_BRAND_NAME, TYLENOL_DESCRIPTOR_NAME);
	private static Drug TYLANETOL = new Drug(TYLANETOL_DIN, "TYLANETOL PRIME", "IBUPROPHENE");

	private static Drug[] DRUGS_ARRAY = { TYLENOL, TYLANETOL };

	private static final String CAMEL_CASE_TYLENOL_BRAND_NAME = "TyLeNoL";

	private static final String SIMPLE_SEARCH_PATTERN = "TYL";
	private static final String SEARCH_PATTERN_WILDCARD = "TYL PRI";
	private static final String PATTERN_WITH_MULTIPLE_WILDCARDS = "TY NE OL PRI ME";
	private static final String INVALID_KEYWORD = "123" + SEARCH_PATTERN_WILDCARD + "123";

	private DrugRepository drugRepository;

	@Before
	public void setup() {
		DrugDataAdapter adapter = mock(CSVDrugDataAdapter.class);
		Collection<Drug> drugsCollection = Arrays.asList(DRUGS_ARRAY);
		when(adapter.getDrugsListFromFile()).thenReturn(drugsCollection);
		System.out.println(adapter.getDrugsListFromFile().size());

		drugRepository = new DrugInMemoryRepository(adapter);
	}

	@Test
	public void givenRepositoryWhenGetByDinWithExistingDinShouldReturnCorrespondingDrug() {
		Drug drugFound = drugRepository.get(TYLENOL_DIN);

		assertEquals(TYLENOL, drugFound);
	}

	@Test(expected = NoSuchElementException.class)
	public void givenRepositoryWhenGetByDinWithUnexistingDinShouldThrowException() {
		drugRepository.get(UNEXISTING_DIN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void findByBrandNameOrDescriptorWhenLessThanThreeCharactersShouldThrowException() {
		drugRepository.findByBrandNameOrDescriptor(LESS_THAN_THREE_CHARACTERS_DRUG_NAME);
	}

	@Test
	public void findByBrandNameOrDescriptorWithExistingBrandNameShouldNotBeEmpty() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(TYLENOL_BRAND_NAME);
		boolean result = drugsFound.isEmpty();
		assertFalse(result);
	}

	@Test
	public void findByBrandNameOrDescriptorWithTylenolBrandNameShouldReturnTylenolDrug() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(TYLENOL_BRAND_NAME);
		boolean result = drugsFound.contains(TYLENOL);
		assertTrue(result);
	}

	@Test
	public void findByBrandNameOrDescriptorWithExistingDescriptorShouldNotBeEmpty() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(TYLENOL_DESCRIPTOR_NAME);
		boolean result = drugsFound.isEmpty();
		assertFalse(result);
	}

	@Test
	public void findByBrandNameOrDescriptorWithTylenolDescriptorShouldReturnTylenolDrug() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(TYLENOL_DESCRIPTOR_NAME);
		boolean result = drugsFound.contains(TYLENOL);
		assertTrue(result);
	}

	@Test(expected = NoSuchElementException.class)
	public void findByBrandNameOrDescriptorWithUnexistingBrandNameShouldReturnException() {
		drugRepository.findByBrandNameOrDescriptor(UNEXISTING_BRAND_NAME);
	}

	@Test(expected = NoSuchElementException.class)
	public void findByBrandNameOrDescriptorWithUnexistingDescriptorShouldReturnException() {
		drugRepository.findByBrandNameOrDescriptor(UNEXISTING_DESCRIPTOR);
	}

	@Test
	public void findByBrandNameOrDescriptorWhenUsingValidPatternShouldReturnTwoDrugs() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(SIMPLE_SEARCH_PATTERN);
		int result = drugsFound.size();
		assertEquals(2, result);
	}

	@Test
	public void findByBrandNameOrDescriptorWhenUsingSimpleKeywordShouldContainsAllDrugsWithSameBeginning() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(SIMPLE_SEARCH_PATTERN);

		boolean resultContainsTylenol = drugsFound.contains(TYLENOL);
		boolean resultContainsTylanetol = drugsFound.contains(TYLANETOL);

		assertTrue(resultContainsTylenol);
		assertTrue(resultContainsTylanetol);
	}

	@Test
	public void findByBrandNameOrDescriptorWhenUsingPatternWithTwoWordsShouldReturnDrugs() {
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

	@Test
	public void findByBrandNameOrDescriptorWhenUsingPatternWithMultipleWildcardsShouldContainsSpecificDrug() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(PATTERN_WITH_MULTIPLE_WILDCARDS);
		boolean result = drugsFound.contains(TYLANETOL);
		assertTrue(result);
	}

	@Test(expected = NoSuchElementException.class)
	public void findByBrandNameOrDescriptorWhenUsingInvalidPatternShouldThrowException() {
		drugRepository.findByBrandNameOrDescriptor(INVALID_KEYWORD);
	}

	@Test
	public void findByBrandNameOrDescriptorWithCamelCaseShouldReturnDrugs() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(CAMEL_CASE_TYLENOL_BRAND_NAME);
		boolean result = drugsFound.isEmpty();
		assertFalse(result);
	}

	@Test
	public void findByBrandNameOrDescriptorWithTylenolBrandNameCamelCaseShouldReturnTylenolDrug() {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(CAMEL_CASE_TYLENOL_BRAND_NAME);
		boolean result = drugsFound.contains(TYLENOL);
		assertTrue(result);
	}

}
