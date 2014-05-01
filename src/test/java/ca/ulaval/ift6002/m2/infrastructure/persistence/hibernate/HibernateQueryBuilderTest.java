package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.infrastructure.persistence.QueryBuilder;

@RunWith(MockitoJUnitRunner.class)
public class HibernateQueryBuilderTest {

    private static final Class<Integer> CLASS_TYPE = Integer.class;

    private static final String A_QUERY = "aaa";
    private static final String PARAMETER_NAME = "a name";
    private static final Integer PARAMETER_VALUE = 12345;

    @Mock
    private TypedQuery<Integer> typedQuery;

    @Mock
    private EntityManager entityManager;

    private QueryBuilder<Integer> queryBuilder;

    @Before
    public void setUp() {
        queryBuilder = new HibernateQueryBuilder<>(entityManager, CLASS_TYPE);

        willReturn(typedQuery).given(entityManager).createQuery(A_QUERY, CLASS_TYPE);
    }

    @Test
    public void whenPerformingAQueryShouldVerifyExecuteUpdateOfTypedQuery() {
        queryBuilder.query(A_QUERY).perform();

        verify(typedQuery).executeUpdate();
    }

    @Test
    public void whenGettingShouldVerifyGetSingleResultOfTypedQuery() {
        queryBuilder.query(A_QUERY).get();

        verify(typedQuery).getSingleResult();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGettingWithInvalidQueryShouldThrowException() {
        willThrow(new NoResultException()).given(typedQuery).getSingleResult();

        queryBuilder.query(A_QUERY).get();
    }

    @Test
    public void whenListingShouldVerifyGetResultListOfTypedQuery() {
        queryBuilder.query(A_QUERY).list();

        verify(typedQuery).getResultList();
    }

    @Test
    public void givenOneParameterWhenPerformingQueryShouldAssignOneParameter() {
        queryBuilder.query(A_QUERY).parameter(PARAMETER_NAME, PARAMETER_VALUE).perform();

        verify(typedQuery).setParameter(PARAMETER_NAME, PARAMETER_VALUE);
    }

    @Test
    public void givenOneParameterWhenGettingShouldAssignOneParameter() {
        queryBuilder.query(A_QUERY).parameter(PARAMETER_NAME, PARAMETER_VALUE).get();

        verify(typedQuery).setParameter(PARAMETER_NAME, PARAMETER_VALUE);
    }

    @Test
    public void givenOneParameterWhenListingShouldAssignOneParameter() {
        queryBuilder.query(A_QUERY).parameter(PARAMETER_NAME, PARAMETER_VALUE).list();

        verify(typedQuery).setParameter(PARAMETER_NAME, PARAMETER_VALUE);
    }
}
