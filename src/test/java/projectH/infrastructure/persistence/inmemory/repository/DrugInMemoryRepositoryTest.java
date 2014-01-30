package projectH.infrastructure.persistence.inmemory.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;

@RunWith(MockitoJUnitRunner.class)
public class DrugInMemoryRepositoryTest {

	private static final String UNEXISTING_DRUG_NAME = "UNEXISTING_DRUG";
	private static final String EXISTING_DRUG_NAME = "EXISTING_DRUG";
	private static final String DIN = "1234567890";

	private static final String LESS_THAN_THREE_CHARACTERS_DRUG_NAME = "DR";

	@Mock
	private Drug drug;

	private DrugRepository drugRepository;

	@Before
	public void setup() {
		drugRepository = new DrugInMemoryRepository();
	}

	@Test
	public void findByNameExistingDrugShouldReturnADrug() {
		when(drug.getDin()).thenReturn(DIN);
		when(drug.getName()).thenReturn(EXISTING_DRUG_NAME);

		Drug drugFound = drugRepository.findByName(drug.getName());

		assertEquals(drug.getName(), drugFound.getName());
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
