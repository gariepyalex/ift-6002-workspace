package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;

public class RepositoryFactoryTest {

    @Test
    public void givenPersistenceTypeWhenGetDrugRepositoryShouldReturnSameRepositoryType() {
        // TODO review this test
        DrugRepository drugRepository = RepositoryFactory.getDrugRepository();
        assertEquals(DrugInMemoryRepository.class, drugRepository.getClass());
    }
}
