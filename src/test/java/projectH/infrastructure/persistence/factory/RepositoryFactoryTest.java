package projectH.infrastructure.persistence.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import projectH.domain.drug.DrugRepository;
import projectH.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;

public class RepositoryFactoryTest {

	@Test
	public void givenPersistenceTypeWhenGetDrugRepositoryShouldReturnSameRepositoryType() {
		// TODO review this test
		DrugRepository drugRepository = RepositoryFactory.getDrugRepository();
		assertEquals(DrugInMemoryRepository.class, drugRepository.getClass());
	}
}
