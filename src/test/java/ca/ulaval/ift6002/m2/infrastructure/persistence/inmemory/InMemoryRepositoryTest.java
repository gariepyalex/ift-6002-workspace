package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.infrastructure.persistence.filler.RepositoryFiller;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryRepositoryTest {

    private static final String AN_ELEMENT = "1234567890";

    private static final Collection<String> ALL_ELEMENTS = Arrays.asList(AN_ELEMENT);

    // Minimal implementation of the abstract class
    private final class RepositoryImpl extends InMemoryRepository<String> {

        public RepositoryImpl(RepositoryFiller<String> filler) {
            super(filler);
        }

        @Override
        protected Object[] getKeys(String element) {
            Object[] keys = { element };
            return keys;
        }
    }

    @Mock
    private RepositoryFiller<String> filler;

    private RepositoryImpl repository;

    @Before
    public void setup() {
        willReturn(ALL_ELEMENTS).given(filler).fill();

        repository = new RepositoryImpl(filler);
    }

    @Test
    public void givenRepositoryWhenCreateShouldNotBeEmptyWhenFilled() {
        assertFalse(repository.isEmpty());
    }

    @Test
    public void givenRepositoryWhenCreatedShouldBeLoadedAndHaveSizeOfOne() {
        assertEquals(1, repository.size());
    }

    @Test
    public void givenRepositoryWhenGettingDataShouldNotBeEmpty() {
        Map<Integer, String> data = repository.getData();

        assertFalse(data.isEmpty());
    }

    @Test
    public void givenRepositoryWhenGettingDataMapShouldContainsElement() {
        Map<Integer, String> data = repository.getData();
        boolean hasElement = data.containsValue(AN_ELEMENT);
        assertTrue(hasElement);
    }
}
