package ca.ulaval.ift6002.m2.infrastructure.persistence;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class QueryBuilderTest {

    private static final List<Integer> RESULTS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private static final List<Integer> FIRST_FIVE_RESULTS = Arrays.asList(1, 2, 3, 4, 5);
    private static final List<Integer> LAST_FIVE_RESULTS = Arrays.asList(6, 7, 8, 9, 10);

    private static final int LIMIT_OF_FIVE = 5;
    private static final int FIFTH_INDEX = 5;

    private QueryBuilder<Integer> queryBuilder;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        queryBuilder = mock(QueryBuilder.class, CALLS_REAL_METHODS);
    }

    @Test
    public void whenListingWithLimitOfFiveShouldReturnFirstFiveResults() {
        willReturn(RESULTS).given(queryBuilder).list();
        List<Integer> actualResults = queryBuilder.list(LIMIT_OF_FIVE);
        assertEquals(FIRST_FIVE_RESULTS, actualResults);
    }

    @Test
    public void whenListingFromIndexFiveWithLimitOfFiveShouldReturnLastFiveResults() {
        willReturn(RESULTS).given(queryBuilder).list();
        List<Integer> actualResults = queryBuilder.list(FIFTH_INDEX, LIMIT_OF_FIVE);
        assertEquals(LAST_FIVE_RESULTS, actualResults);
    }
}
