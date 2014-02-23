package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.DrugHibernateRepository;

public class HibernateRepositoryFactoryTest {

    private RepositoryFactory repositoryFactory;

    @Before
    public void setup() {
        repositoryFactory = new HibernateRepositoryFactory();
    }

    @Test
    public void givenFactoryWhenCreateDrugRepositoryShouldReturnHibernateClass() {
        DrugRepository drugRepository = repositoryFactory.createDrugRepository();
        assertEquals(drugRepository.getClass(), DrugHibernateRepository.class);
    }
}
