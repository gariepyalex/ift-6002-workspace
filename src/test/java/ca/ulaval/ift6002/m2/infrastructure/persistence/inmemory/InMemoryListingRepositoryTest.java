package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.infrastructure.persistence.filler.RepositoryFiller;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryListingRepositoryTest {

    private static final String FIRST_ELEMENT = "1234567890";
    private static final int FIRST_ELEMENT_HASHED_KEY = Objects.hash(FIRST_ELEMENT);

    private static final String SECOND_ELEMENT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int UNEXISTING_ID = -1;

    private static final Collection<String> ALL_ELEMENTS = Arrays.asList(FIRST_ELEMENT, SECOND_ELEMENT);

    // Minimal implementation of the abstract class
    private final class ListingRepositoryImpl extends InMemoryListingRepository<String> {

        public ListingRepositoryImpl(RepositoryFiller<String> filler) {
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

    private ListingRepositoryImpl listingRepository;

    @Before
    public void setup() {
        willReturn(ALL_ELEMENTS).given(filler).fill();

        listingRepository = new ListingRepositoryImpl(filler);
    }

    @Test
    public void givenRepositoryWhenGetCollectionShouldContainsFirstAndSecondElement() {
        Collection<String> elementsInCollection = listingRepository.getCollection();

        boolean resultFirstElement = elementsInCollection.contains(FIRST_ELEMENT);
        boolean resultSecondElement = elementsInCollection.contains(SECOND_ELEMENT);

        assertTrue(resultFirstElement);
        assertTrue(resultSecondElement);
    }

    @Test
    public void givenRepositoryWhenGetElementShouldReturnFirstElement() {
        String elementFound = listingRepository.get(FIRST_ELEMENT_HASHED_KEY);

        assertEquals(FIRST_ELEMENT, elementFound);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenRepositoryWhenGetUnknownElementShouldThrowException() {
        listingRepository.get(UNEXISTING_ID);
    }
}
