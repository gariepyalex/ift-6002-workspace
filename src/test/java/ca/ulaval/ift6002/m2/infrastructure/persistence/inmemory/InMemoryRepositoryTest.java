package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class InMemoryRepositoryTest {

    private static final String AN_ELEMENT = "1234567890";

    private static final Collection<String> ALL_ELEMENTS = Arrays.asList(AN_ELEMENT);

    @SuppressWarnings("unchecked")
    private final DataAdapter<String> dataAdapter = mock(DataAdapter.class);

    // Minimal implementation of the abstract class
    private final class RepositoryImpl extends InMemoryRepository<String> {

        public RepositoryImpl(DataAdapter<String> adapter) {
            super(adapter);
        }

        @Override
        protected Collection<String> loadData() {
            return getDataAdapter().retrieveData();
        }

        @Override
        protected Object[] getKeys(String element) {
            Object[] keys = { element };
            return keys;
        }

        // Since getData() is protected, it is a work-around to expose it
        public Map<Integer, String> exposeGetData() {
            return super.getData();
        }
    }

    private RepositoryImpl repository;

    @Before
    public void setup() {
        setupDataAdapter();

        repository = new RepositoryImpl(dataAdapter);
    }

    private void setupDataAdapter() {
        willReturn(ALL_ELEMENTS).given(dataAdapter).retrieveData();
    }

    @Test
    public void givenRepositoryWhenCreatedShouldBeLoadedAndHaveSizeOfOne() {
        assertEquals(1, repository.size());
    }

    @Test
    public void givenRepositoryWhenGettingDataShouldNotBeEmpty() {
        repository.exposeGetData();

        assertFalse(repository.isEmpty());
    }

    @Test
    public void givenRepositoryWhenGettingDataMapShouldContainsElement() {
        Map<Integer, String> data = repository.exposeGetData();
        boolean result = data.containsValue(AN_ELEMENT);
        assertTrue(result);
    }

    @Test
    public void givenRepositoryWhenGettingDataShouldContainsElement() {
        Map<Integer, String> data = repository.exposeGetData();
        boolean result = data.containsValue(AN_ELEMENT);
        assertTrue(result);
    }
}
