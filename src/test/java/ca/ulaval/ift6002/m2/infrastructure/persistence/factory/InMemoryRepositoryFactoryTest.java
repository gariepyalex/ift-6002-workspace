package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;

public class InMemoryRepositoryFactoryTest {

	@Test
	public void givenFactoryWhenCreateDrugRepositoryShouldReturnInMemoryClass() {
		RepositoryFactory repositoryFactory = new InMemoryRepositoryFactory();
		DrugRepository drugRepository = repositoryFactory.createDrugRepository();
		assertEquals(drugRepository.getClass(), DrugInMemoryRepository.class);
	}
}
