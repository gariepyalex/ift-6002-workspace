package projectH.infrastructure.persistence.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import projectH.domain.drug.DrugRepository;
import projectH.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;

public class InMemoryRepositoryFactoryTest {

	@Test
	public void givenFactoryWhenCreateDrugRepositoryShouldReturnInMemoryClass() {
		RepositoryFactory repositoryFactory = new InMemoryRepositoryFactory();
		DrugRepository drugRepository = repositoryFactory.createDrugRepository();
		assertEquals(drugRepository.getClass(), DrugInMemoryRepository.class);
	}
}
