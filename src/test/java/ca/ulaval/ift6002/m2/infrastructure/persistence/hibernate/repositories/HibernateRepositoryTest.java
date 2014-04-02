package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.infrastructure.persistence.QueryBuilder;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.HibernateQueryBuilder;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

@RunWith(MockitoJUnitRunner.class)
public class HibernateRepositoryTest {

    private static final Integer AN_ELEMENT = 54321;
    private static final Integer AN_EXISTING_ELEMENT = 1;
    private static final Integer AN_UNEXISTING_ELEMENT = 2;

    private static final Integer A_VALUE = 12345;
    private static final Integer AN_UNEXISTING_VALUE = 67890;

    private static final Class<Integer> CLASS_TYPE = Integer.class;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityManagerProvider entityManagerProvider;

    @InjectMocks
    private HibernateRepository<Integer> hibernateRepository;

    @Before
    public void setUp() {
        willReturn(entityManager).given(entityManagerProvider).getEntityManager();

        hibernateRepository = new HibernateRepository<>(entityManagerProvider, CLASS_TYPE);
    }

    @Test
    public void whenFindingByValueShouldVerifyEntityManager() {
        willReturn(AN_ELEMENT).given(entityManager).find(CLASS_TYPE, A_VALUE);
        hibernateRepository.find(A_VALUE);
        verify(entityManager).find(CLASS_TYPE, A_VALUE);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenFindingWithUnexistingValueShouldThrowException() {
        willReturn(null).given(entityManager).find(CLASS_TYPE, AN_UNEXISTING_VALUE);

        hibernateRepository.find(AN_UNEXISTING_VALUE);
    }

    @Test
    public void whenStoringAnExistingElementShouldNotPersist() {
        willReturn(true).given(entityManager).contains(AN_EXISTING_ELEMENT);
        hibernateRepository.storeElement(AN_EXISTING_ELEMENT);
        verify(entityManager, never()).persist(AN_EXISTING_ELEMENT);
    }

    @Test
    public void whenStoringAnUnexistingElementShouldPersist() {
        willReturn(false).given(entityManager).contains(AN_UNEXISTING_ELEMENT);
        hibernateRepository.storeElement(AN_UNEXISTING_ELEMENT);
        verify(entityManager).persist(AN_UNEXISTING_ELEMENT);
    }

    @Test
    public void whenGettingQueryBuilderShouldReturnAnHibernateInstance() {
        QueryBuilder<Integer> queryBuilder = hibernateRepository.getQueryBuilder();

        assertEquals(HibernateQueryBuilder.class, queryBuilder.getClass());
    }
}
