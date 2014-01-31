package projectH.infrastructure.persistence.inmemory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ListingRepositoryTest {

	private final static String FIRST_ELEMENT = "1234567890";
	private final static int FIRST_ELEMENT_HASHED_KEY = FIRST_ELEMENT.hashCode();

	private final static String SECOND_ELEMENT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private final static int UNEXISTING_ID = -1;

	// Minimal implementation of the abstract class
	final private class ListingRepositoryImpl extends ListingRepository<String> {

		@Override
		protected Collection<String> loadData() {
			Collection<String> data = new ArrayList<>();

			data.add(FIRST_ELEMENT);
			data.add(SECOND_ELEMENT);

			return data;
		}

		@Override
		protected int hashKeys(String element) {
			return element.hashCode();
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
