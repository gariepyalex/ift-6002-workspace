package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class ListingRepositoryTest {

	private static final String FIRST_ELEMENT = "1234567890";
	private static final int FIRST_ELEMENT_HASHED_KEY = Objects.hash(FIRST_ELEMENT);

	private static final String SECOND_ELEMENT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final int UNEXISTING_ID = -1;

	// Minimal implementation of the abstract class
	private final class ListingRepositoryImpl extends ListingRepository<String> {

		public ListingRepositoryImpl() {
			convertLoadedElementsToData();
		}

		@Override
		protected Collection<String> loadData() {
			Collection<String> data = new ArrayList<>();

			data.add(FIRST_ELEMENT);
			data.add(SECOND_ELEMENT);

			return data;
		}

		@Override
		protected Object[] getKeys(String element) {
			Object[] keys = { element };
			return keys;
		}

		// Since getCollection() is protected, it is a work-around to expose it
		public Collection<String> exposeGetCollection() {
			return super.getCollection();
		}

		// Since get(int) is protected, it is a work-around to expose it
		public String exposeGet(int hashedId) {
			return super.get(hashedId);
		}

	}

	private ListingRepositoryImpl listingRepository;
	private Collection<String> elementsInCollection;

	@Before
	public void setup() {
		listingRepository = new ListingRepositoryImpl();

		elementsInCollection = listingRepository.exposeGetCollection();
	}

	@Test
	public void givenRepositoryWhenGetCollectionShouldBeLoadedAndNotEmpty() {
		assertFalse(elementsInCollection.isEmpty());
	}

	@Test
	public void givenRepositoryWhenGetCollectionShouldHaveSizeOfTwo() {
		assertEquals(2, elementsInCollection.size());
	}

	@Test
	public void givenRepositoryWhenGetCollectionShouldContainsFirstAndSecondElement() {
		boolean resultFirstElement = elementsInCollection.contains(FIRST_ELEMENT);
		boolean resultSecondElement = elementsInCollection.contains(SECOND_ELEMENT);

		assertTrue(resultFirstElement);
		assertTrue(resultSecondElement);
	}

	@Test
	public void givenRepositoryWhenGetElementShouldReturnFirstElement() {
		String elementFound = listingRepository.exposeGet(FIRST_ELEMENT_HASHED_KEY);

		assertEquals(FIRST_ELEMENT, elementFound);
	}

	@Test(expected = NoSuchElementException.class)
	public void givenRepositoryWhenGetUnknownElementShouldThrowException() {
		listingRepository.exposeGet(UNEXISTING_ID);
	}
}
